package indexer.api.server.index;

import java.util.HashMap;

import org.springframework.data.domain.Pageable;

import indexer.api.server.exception.IndexerNotMatchesException;


/**
 * @author lion
 *
 * Suitable interface to implement Indexers
 */
public interface BaseIndexer {

	/**
	 * Add a content to the index, whatever mechanism used for implementation, Ex, Database, Files, etc
	 * 
	 * @param html the content to be indexed
	 * @param url the url where was found that content
	 */
	public void addToIndex(String html, String url);
	
	/**
	 * Execute a search query on the index
	 * 
	 * @param query the query 
	 * @param page A convenient way to get pagination on client request
	 * @return It returns an object, to allow classes 
	 * implement this interface design response based on specific requirements
	 * 
	 * @throws IndexerNotMatchesException if not results were founds
	 */
	public Object search(String query, Pageable page) throws IndexerNotMatchesException;
	
	/**
	 * Clear the index entirely
	 * @throws Exception if something went wrong
	 */
	public void clearIndex() throws Exception;
	
	/**
	 * @return Indexer statistics, in map format, <key>:<value>, Ej, totalWords: 45
	 */
	public HashMap<String, Object> getStats();

}