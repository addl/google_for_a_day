package indexer.api.server.index;

import indexer.api.server.exception.IndexerNotMatchesException;
import indexer.api.server.service.IndexService;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author lion
 * A custom implementation of {@link BaseIndexer} based on DBRMS to accomplish index content
 */
@Service
public class DatabaseIndexer implements BaseIndexer {
	
	public static final String STATS_TOTAL_PAGES_KEY = "totalPages";
	public static final String STATS_TOTAL_WORDS_KEY = "totalWords";

	/**
	 * Service used to store data in database
	 */
	@Autowired
	private IndexService indexService;	
	
	/* (non-Javadoc)
	 * @see indexer.api.server.index.BaseIndexer#addToIndex(java.lang.String, java.lang.String)
	 * 
	 * Add content to index, extracting only the text found in the html content
	 * Create a DOM document with the help of JSoup library.
	 */
	@Override
	public void addToIndex(String htmlContent, String url) {
		Document document = Jsoup.parse(htmlContent);
		Map<String, Integer> words = new HashMap<String, Integer>();
		processOneElement(document.body(), words);
		String title = document.title();
		String [] titleWords = title.split(" ");
		for (String word : titleWords) {
			indexService.createOrUpdateIndex(word.trim().toLowerCase(), url, title, 1);
		}
		for(String word : words.keySet()){
			indexService.createOrUpdateIndex(word, url, title, words.get(word));
		}
	}
	
	/** It takes a DOM element, extract the text contained and 
	 * look recursively for the other elements in the DOM
	 * To parse a full document is a good idea pass de body element
	 * 
	 * @param element the DOM elemnt to process
	 * @param words a map with words already processed
	 */
	private void processOneElement(Element element, Map<String, Integer> words){
		/*First of all, we process the node's text, if has text*/
		if(!element.ownText().equals("")){
			String text = element.ownText();
			processText(text, words);
		}		
		if(element.childNodeSize() > 0){
			for(Node child: element.childNodes()){
				if(child instanceof Element){
					processOneElement((Element) child, words);
				}
			}
		}
	}
	
	/**
	 * Process a text passed as parameter looking for token (words)
	 * 
	 * @param text to process
	 * @param words a dictionary to keep the processed words
	 */
	public void processText(String text, Map<String, Integer> words){
		StringTokenizer st = new StringTokenizer(text, " ");
		while (st.hasMoreElements()) {
			String token = (String) st.nextElement();
			String cleanToken = cleanToken(token);
			updateMatchesToWord(cleanToken, words);
		}
	}
	
	/**
	 * Clean a token, to keep only letters
	 * 
	 * @param token the token to clean
	 * @return a substring with the letters only
	 */
	public String cleanToken(String token){
		StringBuilder result = new StringBuilder();
		if(token.length() > 0){
			for (int i = 0; i < token.length(); i++) {
				char character = token.charAt(i);
				if(Character.isLetter(character)){
					result.append(character);
				}
			}			
		}
		/*To prevent SQL Constraints violations*/
		if(result.length() > 254){
			return result.substring(254);
		}
		return result.toString();
	}

	/**
	 * Update the word matches in the map words
	 * 
	 * @param token the word
	 * @param words a map containing all words
	 */
	private void updateMatchesToWord(String token, Map<String, Integer> words) {
		if(token.length() > 0){
			int matches = 1;
			if(words.containsKey(token)){
				 matches = words.get(token) + 1;
			}
			words.put(token, matches);
		}
	}
	
	/* (non-Javadoc)
	 * @see indexer.api.server.index.BaseIndexer#search(java.lang.String, org.springframework.data.domain.Pageable)
	 */
	@Override
	public Object search(String query, Pageable page) throws IndexerNotMatchesException {
		return indexService.search(query.toLowerCase(), page);
	}

	/* (non-Javadoc)
	 * @see indexer.api.server.index.BaseIndexer#clearIndex()
	 */
	@Override
	public void clearIndex() throws Exception {
		indexService.clearDatabase();		
	}
	
	/* (non-Javadoc)
	 * @see indexer.api.server.index.BaseIndexer#getStats()
	 */
	@Override
	public HashMap<String, Object> getStats() {
		HashMap<String, Object> stats = new HashMap<String, Object>();
		stats.put(STATS_TOTAL_PAGES_KEY, indexService.getTotalPages());
		stats.put(STATS_TOTAL_WORDS_KEY, indexService.getTotalWords());
		return stats;
	}

}
