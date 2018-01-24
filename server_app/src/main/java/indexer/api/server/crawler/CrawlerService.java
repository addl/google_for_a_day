package indexer.api.server.crawler;

import indexer.api.server.exception.UrlBadFormatException;

public interface CrawlerService {
	
	/**
	 * Add a new linkk to Crawler, but it will wait until Crawler 
	 * process the link and a subsequent link inside that URL
	 * 
	 * @param link The new URL
	 * @throws UrlBadFormatException Exception throw if the URL format is incorrect
	 */
	public void addLinks(String link) throws UrlBadFormatException;
	
	/**
	 * Add a link to process in Crawler, the action is ThreadSafe
	 * to ensure that clients get a response as quickly as posible
	 * 
	 * @param link
	 */
	public void addLinkAsynchronously(String link);
	
	/**
	 * Reset the crawler, remove all processed links 
	 * and set queue to empty
	 */
	public void resetCrawler();

}
