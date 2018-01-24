package indexer.api.server.repository;

import indexer.api.server.model.Word;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author lion
 *
 * The repository to {@link Word} entity
 * 
 */
public interface WordRepository extends PagingAndSortingRepository<Word, Long> {
	
	public Word findByLexeme(String lexeme);

}
