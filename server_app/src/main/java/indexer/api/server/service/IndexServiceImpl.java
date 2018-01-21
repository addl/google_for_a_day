package indexer.api.server.service;

import indexer.api.server.model.WebPage;
import indexer.api.server.model.Word;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexServiceImpl implements IndexService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WordService wordService;
	
	@Autowired
	private WebPageService webPageService;
	
	@Override
	public void createOrUpdateIndex(String word, String url, String pageTitle, int matches) {
		Word found = wordService.findByLexeme(word);
		if(found != null){
			updateWordIndex(url, pageTitle, matches, found);
		}else{
			Word newEntity = new Word();
			newEntity.addWebPage(new WebPage(matches, url, pageTitle));
			wordService.create(newEntity);
		}
	}

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

	@Override
	public Word search(String query) {
		logger.debug("Executing search by query: {}", query);
		return wordService.findByLexeme(query);
	}

	@Override
	public void clearDatabase() throws Exception {
		logger.debug("Removing all entries on index");
		wordService.removeAll();		
	}

}
