package indexer.api.server.index;

import org.springframework.data.domain.Pageable;

import indexer.api.server.exception.IndexerNotMatchesException;


public interface BaseIndexer {

	public void addToIndex(String html, String url);
	
	public Object search(String query, Pageable page) throws IndexerNotMatchesException;
	
	public void clearIndex() throws Exception;

}