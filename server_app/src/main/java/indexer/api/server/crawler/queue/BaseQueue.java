package indexer.api.server.crawler.queue;

import indexer.api.server.crawler.QueueEmptyExcpetion;

/**
 * @author lion
 *
 * {@link BaseQueue} is a convenient interface to implement subsequent Queues
 *
 * @param <T> A generic type object to make the queue as generic as posible
 */
public interface BaseQueue<T> {
	
	/**
	 * Add a new element to queue
	 * 
	 * @param element
	 */
	public void add(T element);
	
	/**
	 * @return the first element in queue and remove the element from the queue
	 * @throws QueueEmptyExcpetion
	 */
	public T pop() throws QueueEmptyExcpetion;
	
	/**
	 * @return The reference to the first element in the queue
	 * @throws QueueEmptyExcpetion
	 */
	public T top() throws QueueEmptyExcpetion;
	
	/**
	 * @return The reference to the last element
	 * @throws QueueEmptyExcpetion
	 */
	public T bottom() throws QueueEmptyExcpetion;
	
	/**
	 * @return True if the queue is empty, false another case
	 */
	public boolean isEmpty();

}
