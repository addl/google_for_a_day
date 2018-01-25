/*package indexer.api.server.index;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import indexer.api.server.exception.IndexerNotMatchesException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatabaseIndexerTests {

	private String htmlContent = "<html>"
			+ "<head><title>Example</title><style></style></head>" + "<body>"
			+ "<h2>This is an h2 element</h2>" + "<p>This is a paragraph</p>"
			+ "<div>One div<label>Label inside a div element</label></div>"
			+ "<a href=\"http://www.example.com\">Example</a>" + "</body>"
			+ "</html>";

	private String url = "http://www.example.com";

	@Autowired
	private DatabaseIndexer indexer;
	
	@Before
	public void setUp() throws Exception {
		indexer.clearIndex();
	}

	@Test
	public void processTextFillWordsDictionary() {
		String text = "Note that the text within the element is not returned, as it is not a direct child of the element.";
		Map<String, Integer> words = new HashMap<String, Integer>();
		indexer.processText(text, words);
		int expectedWordsSize = 16;
		Set<String> keySet = words.keySet();
		for (String string : keySet) {
			System.out.println("***" + string);
		}
		assertEquals(expectedWordsSize, words.size());
	}

	@Test
	public void testWordsDitionaryContains3TimeTheWord() {
		String text = "Note that the text within the element is not returned, as it is not a direct child of the element.";
		Map<String, Integer> words = new HashMap<String, Integer>();
		indexer.processText(text, words);
		int expectedTheMatches = 3;
		assertEquals(expectedTheMatches, Integer.parseInt(words.get("the").toString()));
	}

	@Test
	public void testIndexContentValuesInsertIntoDB() throws IndexerNotMatchesException {
		indexer.addToIndex(htmlContent, url);
		assertNotNull(indexer.search("the", new PageRequest(0, 5)));
	}

}
*/