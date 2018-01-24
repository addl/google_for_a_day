package indexer.api.server.service;

import indexer.api.server.exception.IndexerNotMatchesException;
import indexer.api.server.model.WebPage;
import indexer.api.server.model.Word;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author lion
 *
 * A custom implementation of {@link IndexService}
 */
@Service
public class IndexServiceImpl implements IndexService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WordService wordService;
	
	@Autowired
	private WebPageService webPageService;
	
	/* (non-Javadoc)
	 * @see indexer.api.server.service.IndexService#createOrUpdateIndex(java.lang.String, java.lang.String, java.lang.String, int)
	 */
	@Override
	public void createOrUpdateIndex(String word, String url, String pageTitle, int matches) {
		word = word.toLowerCase();
		Word found = wordService.findByLexeme(word);
		if(found != null){
			updateWordIndex(url, pageTitle, matches, found);
		}else{
			Word newEntity = new Word(word);
			newEntity.addWebPage(new WebPage(matches, url, pageTitle));
			wordService.create(newEntity);
		}
	}

	/**
	 * A parameterized method to get cleaner code
	 * @param url
	 * @param pageTitle
	 * @param matches
	 * @param found
	 */
	private void updateWordIndex(String url, String pageTitle, int matches, Word found) {
		WebPage webPage = webPageService.findByUrlAndWord(url, found);
		if(webPage != null){
			webPage.setMatches(matches);
		}else{
			webPage = new WebPage();
			webPage.setMatches(matches);
			webPage.setTitle(pageTitle);
			webPage.setUrl(url);
			webPage.setWord(found);
		}
		webPageService.create(webPage);
	}

	/* (non-Javadoc)
	 * @see indexer.api.server.service.IndexService#search(java.lang.String, org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<WebPage> search(String query, Pageable page) throws IndexerNotMatchesException{
		logger.debug("Executing search by query: {}", query.toLowerCase());
		Word found = wordService.findByLexeme(query);
		if(found == null){
			throw new IndexerNotMatchesException("0 results for query " + query);
		}
		return webPageService.findByWordOrderByMatches(found, page);
	}

	/* (non-Javadoc)
	 * @see indexer.api.server.service.IndexService#clearDatabase()
	 */
	@Override
	public void clearDatabase() throws Exception {
		logger.debug("Removing all entries on index");
		wordService.removeAll();		
	}

	/* (non-Javadoc)
	 * @see indexer.api.server.service.IndexService#getTotalPages()
	 */
	@Override
	public int getTotalPages() {
		logger.debug("Retrieving total Pages");
		return webPageService.findDistinctURLs().size();
	}

	/* (non-Javadoc)
	 * @see indexer.api.server.service.IndexService#getTotalWords()
	 */
	@Override
	public int getTotalWords() {
		logger.debug("Retrieving total words");
		return wordService.count();
	}

}
