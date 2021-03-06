package indexer.api.server.service;

import java.util.List;

import indexer.api.server.exception.IndexerEntityNotFoundException;
import indexer.api.server.model.WebPage;
import indexer.api.server.model.Word;
import indexer.api.server.repository.WebPageRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author lion
 * 
 * A custom implementation of {@link WebPageService}
 */
@Service
public class WebPageServiceImpl implements WebPageService {
	
	@Autowired
	private WebPageRepository webPageRepository;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/* (non-Javadoc)
	 * @see indexer.api.server.service.WebPageService#create(indexer.api.server.model.WebPage)
	 */
	@Override
	public WebPage create(WebPage newEntity) {
		logger.debug("Creating new WebPage");
		WebPage created = webPageRepository.save(newEntity);
		logger.debug("WebPage entity created successfully");
		return created;
	}

	/* (non-Javadoc)
	 * @see indexer.api.server.service.WebPageService#update(indexer.api.server.model.WebPage)
	 */
	@Override
	public WebPage update(WebPage updatedEntity) {
		logger.debug("Updating WebPage with ID: " + updatedEntity.getId());
		WebPage updated = webPageRepository.save(updatedEntity);
		logger.debug("WebPage entity updated successfully");
		return updated;
	}

	/* (non-Javadoc)
	 * @see indexer.api.server.service.WebPageService#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) throws IndexerEntityNotFoundException {
		logger.debug("Deleting WebPage with ID: " + id);
		WebPage found = webPageRepository.findOne(id);
		if(found == null){
			throw new IndexerEntityNotFoundException("The WebPage with ID: " + id + " was not found");
		}
		webPageRepository.delete(id);
		logger.debug("WebPage with ID: " + id + " was removed successfully");
	}

	/* (non-Javadoc)
	 * @see indexer.api.server.service.WebPageService#findOne(java.lang.Long)
	 */
	@Override
	public WebPage findOne(Long id) throws IndexerEntityNotFoundException {
		logger.debug("Finding WebPage with ID: " + id);
		WebPage found = webPageRepository.findOne(id);
		if(found == null){
			throw new IndexerEntityNotFoundException("The WebPage with ID: " + id + " was not found");
		}
		return found;
	}
	
	/* (non-Javadoc)
	 * @see indexer.api.server.service.WebPageService#countByUrl(java.lang.String)
	 */
	@Override
	public int countByUrl(String url) {
		logger.debug("Counting WebPages by URL: {}", url);
		return Integer.parseInt(String.valueOf(webPageRepository.countByUrl(url)));
	}

	/* (non-Javadoc)
	 * @see indexer.api.server.service.WebPageService#findAll(org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<WebPage> findAll(Pageable page) {
		logger.debug("Paging WebPage entity Pageable is: " + page.toString());
		return webPageRepository.findAll(page);
	}

	/* (non-Javadoc)
	 * @see indexer.api.server.service.WebPageService#findByURL(java.lang.String, org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<WebPage> findByURL(String url, Pageable page) {
		logger.debug("Finding WebPage with URL: " + url);
		return webPageRepository.findByUrl(url, page);
	}

	/* (non-Javadoc)
	 * @see indexer.api.server.service.WebPageService#findByUrlAndWord(java.lang.String, indexer.api.server.model.Word)
	 */
	@Override
	public WebPage findByUrlAndWord(String url, Word word) {
		logger.debug("Finding WebPage by URL: {} and Word: {}", url, word);
		return webPageRepository.findByUrlAndWord(url, word);
	}

	/* (non-Javadoc)
	 * @see indexer.api.server.service.WebPageService#findByWordOrderByMatches(indexer.api.server.model.Word, org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<WebPage> findByWordOrderByMatches(Word word, Pageable page) {
		logger.debug("Finding WebPage by Word: {}", word);
		return webPageRepository.findByWordOrderByMatchesDesc(word, page);
	}

	/* (non-Javadoc)
	 * @see indexer.api.server.service.WebPageService#findDistinctURLs()
	 */
	@Override
	public List<WebPage> findDistinctURLs() {
		logger.debug("Looking for distinct URLs");
		return webPageRepository.findDistinctURLs();
	}


}
