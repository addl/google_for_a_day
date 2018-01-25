/*package indexer.api.server.crawler;

import static org.junit.Assert.*;
import indexer.api.server.exception.UrlBadFormatException;
import indexer.api.server.index.BaseIndexer;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrawlerTests {
	
	private static final String rootUrl = "http://localhost:5000/";
	
	@Autowired
	private Crawler crawler;
	
	@Autowired
	private BaseIndexer indexer;
	
	@Before
	public void setUp(){
		try {
			//indexer.clearIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void queueGetInitialized() throws UrlBadFormatException{
		crawler.addLink(rootUrl, 0);
		assertNotNull(crawler.getLinksQueue());
	}
	
	@Test
	public void getAllLinksIsNotNullOrEmpty() throws MalformedURLException, IOException{
		Set<String> links = crawler.getAllLinksFrom(rootUrl);
		assertNotNull(links);
		assertNotEquals(0, links.size());
	}	
	
	@Test
	public void getContentFromUrlIsNotNollOrEmpty() throws MalformedURLException, IOException{
		String text = crawler.getContent(rootUrl);
		assertNotNull(text);
		assertNotEquals(0, text.length());
	}
	
	

}
*/