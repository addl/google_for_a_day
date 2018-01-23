package indexer.api.server.crawler;

import indexer.api.server.config.CrawlerProperties;
import indexer.api.server.crawler.queue.Queue;
import indexer.api.server.crawler.queue.QueueSet;
import indexer.api.server.index.BaseIndexer;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Crawler {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CrawlerProperties crawlerProperties;

	@Autowired
	private BaseIndexer indexer;

	private Queue<Link> linksQueue;

	private Set<String> processedLinks;

	public Crawler() {
		logger.debug("Initializing Crawler");
		processedLinks = new HashSet<String>();
	}

	public void addLink(String link, int deepLevel) {
		if (linksQueue == null || linksQueue.isEmpty()) {
			linksQueue = new QueueSet(crawlerProperties.getMaximumDeepLevel());
			linksQueue.add(new Link(link, deepLevel));
			processNextLink();
		} else {
			linksQueue.add(new Link(link, deepLevel));
		}
	}

	public void processNextLink() {
		try {
			while (!linksQueue.isEmpty()) {
				Link link = linksQueue.pop();
				crawlURL(link);
				processedLinks.add(link.getUrl());
			}
		} catch (QueueEmptyExcpetion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void crawlURL(Link url) {
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

	public void addNewLinksToProcess(Set<String> subUrls, int currentDeepLevel) {
		for (String newlink : subUrls) {
			if (!processedLinks.contains(newlink)) {
				Link newLinkObject = new Link(newlink, currentDeepLevel);
				logger.debug("Adding new link in order to process it: {}", newLinkObject);				
				linksQueue.add(newLinkObject);
			}else{
				logger.debug("Ignoring URL: {}, it was already processed", newlink);
			}
		}
	}

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

	public Queue<Link> getLinksQueue() {
		return linksQueue;
	}
	
}
