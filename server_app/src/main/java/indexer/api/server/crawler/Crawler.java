package indexer.api.server.crawler;

import indexer.api.server.config.CrawlerProperties;
import indexer.api.server.crawler.queue.Queue;
import indexer.api.server.crawler.queue.QueueSet;
import indexer.api.server.exception.UrlBadFormatException;
import indexer.api.server.index.BaseIndexer;
import indexer.api.server.index.DatabaseIndexer;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.apache.commons.validator.routines.UrlValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A Crawler provides a convenient component to fetch content from the web, and navigate thru its contained URLs.
 * <p>
 * Request configuration can be made using either the shortcut methods in Connection (e.g. {@link #userAgent(String)}),
 * or by methods in the Connection.Request object directly. All request configuration must be made before the request is
 * executed.
 * </p>
 */
/**
 * @author lion
 *
 */
@Component
public class Crawler {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private boolean processing;

	/**
	 * {@link CrawlerProperties} is a bean auto bind by Spring, 
	 * to configure some properties thru application.properties file
	 */
	@Autowired
	private CrawlerProperties crawlerProperties;

	/**
	 * The indexer to use in order to index content
	 */
	@Autowired
	private BaseIndexer indexer;

	/**
	 * The queue of {@link Link}
	 */
	private Queue<Link> linksQueue;

	/**
	 * A Set data structure to store the processed links, it won't repeat any link as set indicates
	 */
	private Set<String> processedLinks;

	public Crawler() {
		logger.debug("Initializing Crawler");
		processedLinks = new HashSet<String>();
	}
	
	/**
	 * Add a new link to process it
	 * 
	 * @param link The link to process
	 * @param deepLevel deepLevel corresponding to that link
	 * @throws UrlBadFormatException throw in case the link is malformed
	 */
	public void addLink(String link, int deepLevel) throws UrlBadFormatException{
		if(validateLink(link)){
			if (linksQueue == null || linksQueue.isEmpty()) {
				/*Initialize the queue if null or with no links(empty)*/
				linksQueue = new QueueSet(crawlerProperties.getMaximumDeepLevel());
				linksQueue.add(new Link(link, deepLevel));
				processNextLink();
			} else {
				linksQueue.add(new Link(link, deepLevel));
			}
		}else{
			throw new UrlBadFormatException("The URL: " + link + " has incorrect format");
		}
	}

	/**
	 * Take the next link in the queue, if there exists and crawl it
	 * 
	 * @throws UrlBadFormatException 
	 * 
	 */
	public void processNextLink() throws UrlBadFormatException {
		try {
			while (!linksQueue.isEmpty()) {
				Link link = linksQueue.pop();
				this.processing = true;
				crawlURL(link);
				this.processing = false;
				processedLinks.add(link.getUrl());
			}
		} catch (QueueEmptyExcpetion e) {
			logger.error("Queue is Empty, Error is: {}", e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Take an URL and process it, take the content and send it to index thru {@link DatabaseIndexer},
	 * also get all links in that page and add each to the queue, if not already are there.
	 * 
	 * @param url
	 * @throws UrlBadFormatException 
	 */
	public void crawlURL(Link url) throws UrlBadFormatException {
		logger.debug("Processing URL: {}", url);	
		logger.info("Processing URL: {}", url);
		try {
			int currentDeepLevel = url.getDeepLevel();
			String content = getContent(url.getUrl());
			if(content != null){
				indexer.addToIndex(content, url.getUrl());
				Set<String> allLinks = getAllLinksFrom(url.getUrl());
				addNewLinksToProcess(allLinks, currentDeepLevel+1);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * Take a iterable of Link as String with the corresponding deep level 
	 * and add each one to the queue. It verify also if the link has not been processed yet
	 * 
	 * @param subUrls
	 * @param currentDeepLevel
	 * @throws UrlBadFormatException 
	 */
	public void addNewLinksToProcess(Set<String> subUrls, int currentDeepLevel) throws UrlBadFormatException {
		for (String newlink : subUrls) {
			if (!processedLinks.contains(newlink)) {
				logger.debug("Adding new link in order to process it: {}", newlink);				
				this.addLink(newlink, currentDeepLevel);
			}else{
				logger.debug("Ignoring URL: {}, it was already processed", newlink);
			}
		}
	}

	/**
	 * Get the HTML content in the url passed as parameter
	 * 
	 * @param url the URL to find the html content
	 * @return the full html found in the url parameter
	 * @throws MalformedURLException
	 */
	public String getContent(String url) throws MalformedURLException {
		String text = null;
		URLConnection connection;
		try {
			connection = new URL(url).openConnection();
			text = new Scanner(connection.getInputStream()).useDelimiter("\\Z").next();		
		} catch (IOException e) {
			logger.info("Can't connect to: {}, Eception message is: {}", url, e.getMessage());
		}		
		return text;
	}

	/**
	 * 
	 * Builds a DOM thru the content found in the url, it use {@link Jsoup} to accomplish that 
	 * @param url to get all links
	 * @return All links found, without repetitions
	 * @throws IOException trown if the url is not accessible
	 */
	public Set<String> getAllLinksFrom(String url) throws IOException {
		logger.info("Getting all links on: " + url);
		Set<String> result = new HashSet<>();
		Document doc = Jsoup.connect(url).get();
		Elements links = doc.getElementsByTag("a");
		for (Element element : links) {
			result.add(element.absUrl("href"));
		}
		return result;
	}
	
	public void resetCrawler(){
		if(processing){
			while (!processing) {
				this.processedLinks.clear();
				this.linksQueue = new QueueSet(crawlerProperties.getMaximumDeepLevel());
			}
		}else{
			this.processedLinks.clear();
			this.linksQueue = new QueueSet(crawlerProperties.getMaximumDeepLevel());
		}
	}
	
	/** Verify if an url is correctly formatted
	 * @param url to verify
	 * @return True if url is correct, false abother case
	 */
	public boolean validateLink(String url){
		UrlValidator validator = new UrlValidator(UrlValidator.ALLOW_LOCAL_URLS);
		return validator.isValid(url);
	}

	public Queue<Link> getLinksQueue() {
		return linksQueue;
	}
	
}
