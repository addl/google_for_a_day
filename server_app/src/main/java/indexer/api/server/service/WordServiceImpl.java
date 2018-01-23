package indexer.api.server.service;

import indexer.api.server.exception.IndexerEntityNotFoundException;
import indexer.api.server.model.Word;
import indexer.api.server.repository.WordRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class WordServiceImpl implements WordService {
	
	@Autowired
	private WordRepository wordRepository;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Word create(Word newEntity) {
		logger.debug("Creating new Word");
		Word created = wordRepository.save(newEntity);
		logger.debug("Word entity created successfully");
		return created;
	}

	@Override
	public Word update(Word updatedEntity) {
		logger.debug("Updating Word with ID: " + updatedEntity.getId());
		Word updated = wordRepository.save(updatedEntity);
		logger.debug("Word entity updated successfully");
		return updated;
	}

	@Override
	public void delete(Long id) throws IndexerEntityNotFoundException {
		logger.debug("Deleting Word with ID: " + id);
		Word found = wordRepository.findOne(id);
		if(found == null){
			throw new IndexerEntityNotFoundException("The word with ID: " + id + " was not found");
		}
		wordRepository.delete(id);
		logger.debug("Word with ID: " + id + " was removed successfully");
	}

	@Override
	public Word findOne(Long id) throws IndexerEntityNotFoundException {
		logger.debug("Finding Word with ID: " + id);
		Word found = wordRepository.findOne(id);
		if(found == null){
			throw new IndexerEntityNotFoundException("The word with ID: " + id + " was not found");
		}
		return found;
	}
	
	@Override
	public int count() {
		logger.info("Counting Words");
		return Integer.parseInt(String.valueOf(wordRepository.count()));
	}

	@Override
	public Page<Word> findAll(Pageable page) {
		logger.debug("Paging Word entity Pageable is: " + page.toString());
		return wordRepository.findAll(page);
	}

	@Override
	public Word findByLexeme(String lexeme) {
		logger.debug("Executing search by lexeme: " + lexeme);
		return wordRepository.findByLexeme(lexeme);
	}

	@Override
	public void removeAll() throws Exception {
		logger.debug("Clearing all indexed words");
		wordRepository.deleteAll();
	}

}
