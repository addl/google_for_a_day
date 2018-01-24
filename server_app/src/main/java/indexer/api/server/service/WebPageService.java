package indexer.api.server.service;

import indexer.api.server.exception.IndexerEntityNotFoundException;
import indexer.api.server.model.WebPage;
import indexer.api.server.model.Word;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author lion
 *
 * A {@link WebPage} service that will use repository and expose only the needed functions
 */
public interface WebPageService {
	
	public WebPage create(WebPage newEntity);
	
	public WebPage update(WebPage updatedEntity);
	
	public void delete(Long id) throws IndexerEntityNotFoundException;
	
	public WebPage findOne(Long id) throws IndexerEntityNotFoundException;
	
	public int countByUrl(String url);
	
	public List<WebPage> findDistinctURLs();
	
	public Page<WebPage> findAll(Pageable page);
	
	public Page<WebPage> findByURL(String url, Pageable page);
	
	public WebPage findByUrlAndWord(String url, Word word);
	
	public Page<WebPage> findByWordOrderByMatches(Word word, Pageable page);

}
