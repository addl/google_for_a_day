package indexer.api.server.service;

import indexer.api.server.exception.IndexerNotMatchesException;
import indexer.api.server.index.DatabaseIndexer;
import indexer.api.server.model.WebPage;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author lion
 * 
 * A suitable interface to get access at database and store data
 * it is used by {@link DatabaseIndexer}
 * 
 */
public interface IndexService {
	
	/**
	 * Create or Update a indexed word on database
	 * 
	 * @param word to be updated or create
	 * @param url the url where was found
	 * @param pageTitle the title of that webpage
	 * @param matches the amount of coincidences founds
	 */
	public void createOrUpdateIndex(String word, String url, String pageTitle, int matches);
	
	/**
	 * Execute a search against Database
	 * 
	 * @param query the word to be find
	 * @param page A convenient object provided by Spring JPA to make pagination on models
	 * @return a Pagination object, with pageNumber, totalPages, totalElements, etc
	 * @throws IndexerNotMatchesException throw if no matches were found
	 */
	public Page<WebPage> search(String query, Pageable page) throws IndexerNotMatchesException;
	
	/**
	 * Clear the index, the database
	 * @throws Exception
	 */
	public void clearDatabase() throws Exception;
	
	/**
	 * @return the total of Web Pages indexed
	 */
	public int getTotalPages();
	
	/**
	 * @return the total amount of word indexed
	 */
	public int getTotalWords();

}
