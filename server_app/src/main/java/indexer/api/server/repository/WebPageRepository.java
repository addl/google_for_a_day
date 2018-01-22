package indexer.api.server.repository;

import indexer.api.server.model.WebPage;
import indexer.api.server.model.Word;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface WebPageRepository extends PagingAndSortingRepository<WebPage, Long> {
	
	public Page<WebPage> findByUrl(String url, Pageable page);
	
	public WebPage findByUrlAndWord(String url, Word word);
	
	public Page<WebPage> findByWordOrderByMatches(Word word, Pageable page);

}
