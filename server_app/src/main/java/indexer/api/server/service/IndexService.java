package indexer.api.server.service;

import indexer.api.server.exception.IndexerNotMatchesException;
import indexer.api.server.model.WebPage;

import org.springframework.data.domain.Page;

public interface IndexService {
	
	public void createOrUpdateIndex(String word, String url, String pageTitle, int matches);
	
	public Page<WebPage> search(String query) throws IndexerNotMatchesException;
	
	public void clearDatabase() throws Exception;

}
