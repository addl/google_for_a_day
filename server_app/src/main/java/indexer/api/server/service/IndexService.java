package indexer.api.server.service;

import indexer.api.server.exception.IndexerNotMatchesException;
import indexer.api.server.model.WebPage;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IndexService {
	
	public void createOrUpdateIndex(String word, String url, String pageTitle, int matches);
	
	public Page<WebPage> search(String query, Pageable page) throws IndexerNotMatchesException;
	
	public void clearDatabase() throws Exception;
	
	public int getTotalPages();
	
	public int getTotalWords();

}
