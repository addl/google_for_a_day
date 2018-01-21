package indexer.api.server.service;

import indexer.api.server.exception.IndexerEntityNotFoundException;
import indexer.api.server.model.Word;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WordService {

	public Word create(Word newEntity);
	
	public Word update(Word updatedEntity);
	
	public void delete(Long id) throws IndexerEntityNotFoundException;
	
	public Word findOne(Long id) throws IndexerEntityNotFoundException;
	
	public Page<Word> findAll(Pageable page);
	
	public Word findByLexeme(String lexeme);
	
	public void removeAll() throws Exception;
	
}
