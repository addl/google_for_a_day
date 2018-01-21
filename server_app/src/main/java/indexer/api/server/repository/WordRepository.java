package indexer.api.server.repository;

import indexer.api.server.model.Word;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface WordRepository extends PagingAndSortingRepository<Word, Long> {
	
	public Word findByLexeme(String lexeme);

}
