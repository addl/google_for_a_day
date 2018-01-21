package indexer.api.server.crawler;

import indexer.api.server.config.CrawlerProperties;
import indexer.api.server.index.BaseIndexer;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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
	
	private Map<String, Integer> links;
	
	public Crawler() {
		logger.debug("Initializing Crawler");
		links = new HashMap<String, Integer>();
		processNextLink();
	}
	
	public void addLink(String link, int deepLevel){
		if(deepLevel < crawlerProperties.getMaximumDeepLevel()){
			if(!links.containsKey(link)){
				links.put(link, deepLevel);
			}else{
				int currentLevel = links.get(link);
				if(currentLevel > deepLevel){
					links.put(link, deepLevel);
				}
			}
		}		
	}
	
	public void processNextLink(){
		while (!links.isEmpty()) {
			String nextURL = links.keySet().iterator().next();
			crawlURL(nextURL);
		}
	}

	public void crawlURL(String url) {
		int currentDeepLevel = links.get(url);		
		String content = getContent(url);
		indexer.addToIndex(content, url);
		Set<String> allLinks = getAllLinksFrom(content);
		addNewLinksToProcess(allLinks, currentDeepLevel);
	}

	public void addNewLinksToProcess(Set<String> subUrls, int currentDeepLevel) {
		for (String newlink : subUrls) {
			/*If the link is already in the map, update the deepLeve, if necessary*/
			if(links.containsKey(newlink)){
				int linkDeepLevel = links.get(newlink);
				if(linkDeepLevel > currentDeepLevel+1){
					links.put(newlink, currentDeepLevel+1);
				}
			}else{
				/*Add the new link with level increased in one*/
				links.put(newlink, currentDeepLevel+1);
			}
		}
	}
	
	public String getContent(String url){
		String text = null;
		try {
			URLConnection connection = new URL("http://java.net").openConnection();
			text = new Scanner(connection.getInputStream()).useDelimiter("\\Z").next();
		} catch (MalformedURLException e) {
			logger.debug("Can't process link: " + url);
			logger.error("URL Malformed: " + url);
		} catch (IOException e) {
			logger.debug("Can't process link: " + url);
			logger.error("Message" + e.getMessage());
		}
		return text;
		
	}
	
	public Set<String> getAllLinksFrom(String htmlContent){
		Set<String> result = new LinkedHashSet<>();
		Document doc = Jsoup.parse(htmlContent);
		Elements links = doc.getElementsByTag("a");
		Object[] array = links.toArray();
		for (int i = 0; i < array.length; i++) {
			result.add((String) array[i]);
		}
		return result;
	}
	
	public Map<String, Integer> getLinks() {
		return links;
	}
}
