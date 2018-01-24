package indexer.api.server.repository;

import java.util.List;

import indexer.api.server.model.WebPage;
import indexer.api.server.model.Word;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author lion
 * 
 * The repository to {@link WebPage} entity
 *
 */
public interface WebPageRepository extends PagingAndSortingRepository<WebPage, Long> {
	
	public Page<WebPage> findByUrl(String url, Pageable page);
	
	public long countByUrl(String url);
	
	public WebPage findByUrlAndWord(String url, Word word);
	
	public Page<WebPage> findByWordOrderByMatchesDesc(Word word, Pageable page);
	
	@Query("SELECT DISTINCT wp.url FROM WebPage wp")
	public List<WebPage> findDistinctURLs();

}
