package indexer.api.server.index;

import indexer.api.server.service.IndexService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseIndexer implements BaseIndexer {

	@Autowired
	private IndexService indexService;
		
	private Map<String, Integer> words;
	
	public DatabaseIndexer() {		
		words = new HashMap<String, Integer>();
	}	
	
	@Override
	public void addToIndex(String htmlContent, String url) {
		Document document = Jsoup.parse(htmlContent);
		processOneElement(document.body());
		String title = document.title();
		for(String word : words.keySet()){
			indexService.createOrUpdateIndex(word, url, title, words.get(word));
		}
	}
	
	private void processOneElement(Element element){
		/*First of all, we process the node's text, if has text*/
		if(!element.ownText().equals("")){
			String text = element.ownText();
			processText(text);
		}		
		if(element.childNodeSize() > 0){
			for(Node child: element.childNodes()){
				if(child instanceof Element){
					processOneElement((Element) child);
				}
			}
		}
	}
	
	public void processText(String text){
		StringTokenizer st = new StringTokenizer(text, " ");
		while (st.hasMoreElements()) {
			String token = (String) st.nextElement();
			updateMatchesToWord(token);
		}
	}

	private void updateMatchesToWord(String token) {
		int matches = 1;
		if(words.containsKey(token)){
			 matches = words.get(token) + 1;
		}
		words.put(token, matches);
	}
	
	public Map<String, Integer> getWords() {
		return words;
	}

	@Override
	public Object search(String query) {
		return indexService.search(query);
	}

	@Override
	public void clearIndex() throws Exception {
		indexService.clearDatabase();		
	}

}
