package indexer.api.server.crawler.queue;

import indexer.api.server.crawler.QueueEmptyExcpetion;

public interface BaseQueue<T> {
	
	public void add(T element);
	
	public T pop() throws QueueEmptyExcpetion;
	
	public T top() throws QueueEmptyExcpetion;
	
	public T bottom() throws QueueEmptyExcpetion;
	
	public boolean isEmpty();

}
