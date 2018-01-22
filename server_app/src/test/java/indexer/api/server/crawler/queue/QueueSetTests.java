package indexer.api.server.crawler.queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import indexer.api.server.crawler.Link;
import indexer.api.server.crawler.QueueEmptyExcpetion;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class QueueSetTests {
	
	private QueueSet queue;
	
	private static final int MAXIMUM_DEEP_LEVEL = 3; 
	
	@Before
	public void setUpQueue(){
		queue = new QueueSet(MAXIMUM_DEEP_LEVEL);
		Link one = new Link("http://www.example.com", 3);
		Link two = new Link("http://www.example.com", 2);
		Link three = new Link("http://www.junit.com", 4);
		queue.add(one);
		queue.add(two);
		queue.add(three);
	}
	
	@Test
	public void afterAddTwoLinksWithTheSameURLExpectedSize1() throws QueueEmptyExcpetion{
		int cont = 0;
		while (!queue.isEmpty()) {
			queue.pop();
			cont++;
		}
		assertEquals(1, cont);
	}
	
	@Test
	public void theFirstLinkHasDeepLevelEqual2() throws QueueEmptyExcpetion{
		assertEquals(2, queue.pop().getDeepLevel());
	}
	
	@Test
	public void referenceTopNotNullAndPopNotThrowsException() throws QueueEmptyExcpetion{
		Link first = queue.top();
		assertNotNull(first);
		assertFalse(queue.isEmpty());
	}
	
	@Test
	public void topAndPopReferenceSameObject() throws QueueEmptyExcpetion{
		Link first = queue.top();
		Link another = queue.pop();
		assertEquals(first, another);
		assertTrue(queue.isEmpty());
	}

}
