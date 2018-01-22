package indexer.api.server.index;

import indexer.api.server.exception.IndexerNotMatchesException;


public interface BaseIndexer {

	public void addToIndex(String html, String url);
	
	public Object search(String query) throws IndexerNotMatchesException;
	
	public void clearIndex() throws Exception;

}