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

/**
 * @author lion
 * 
 * A custom implementation of {@link WordService}
 */
@Service
public class WordServiceImpl implements WordService {
	
	@Autowired
	private WordRepository wordRepository;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/* (non-Javadoc)
	 * @see indexer.api.server.service.WordService#create(indexer.api.server.model.Word)
	 */
	@Override
	public Word create(Word newEntity) {
		logger.debug("Creating new Word");
		Word created = wordRepository.save(newEntity);
		logger.debug("Word entity created successfully");
		return created;
	}

	/* (non-Javadoc)
	 * @see indexer.api.server.service.WordService#update(indexer.api.server.model.Word)
	 */
	@Override
	public Word update(Word updatedEntity) {
		logger.debug("Updating Word with ID: " + updatedEntity.getId());
		Word updated = wordRepository.save(updatedEntity);
		logger.debug("Word entity updated successfully");
		return updated;
	}

	/* (non-Javadoc)
	 * @see indexer.api.server.service.WordService#delete(java.lang.Long)
	 */
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

	/* (non-Javadoc)
	 * @see indexer.api.server.service.WordService#findOne(java.lang.Long)
	 */
	@Override
	public Word findOne(Long id) throws IndexerEntityNotFoundException {
		logger.debug("Finding Word with ID: " + id);
		Word found = wordRepository.findOne(id);
		if(found == null){
			throw new IndexerEntityNotFoundException("The word with ID: " + id + " was not found");
		}
		return found;
	}
	
	/* (non-Javadoc)
	 * @see indexer.api.server.service.WordService#count()
	 */
	@Override
	public int count() {
		logger.info("Counting Words");
		return Integer.parseInt(String.valueOf(wordRepository.count()));
	}

	/* (non-Javadoc)
	 * @see indexer.api.server.service.WordService#findAll(org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<Word> findAll(Pageable page) {
		logger.debug("Paging Word entity Pageable is: " + page.toString());
		return wordRepository.findAll(page);
	}

	/* (non-Javadoc)
	 * @see indexer.api.server.service.WordService#findByLexeme(java.lang.String)
	 */
	@Override
	public Word findByLexeme(String lexeme) {
		logger.debug("Executing search by lexeme: " + lexeme);
		return wordRepository.findByLexeme(lexeme);
	}

	/* (non-Javadoc)
	 * @see indexer.api.server.service.WordService#removeAll()
	 */
	@Override
	public void removeAll() throws Exception {
		logger.debug("Clearing all indexed words");
		wordRepository.deleteAll();
	}

}
