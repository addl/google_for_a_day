package indexer.api.server.crawler;

public interface CrawlerService {
	
	public void addLinks(String link);
	
	public void addLinkAsynchronously(String link);

}
