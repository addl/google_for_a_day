package indexer.api.server.crawler.queue;

import indexer.api.server.crawler.QueueEmptyExcpetion;

import java.util.LinkedList;
import java.util.List;


/**
 * @author lion
 *
 *{@link Queue} is an model that implement {@link BaseQueue} with a {@link List}
 *
 * @param <E> the generic type
 */
public abstract class Queue<E> implements BaseQueue<E> {
	
	protected List<E> queue;
	
	public Queue() {
		queue = new LinkedList<E>();
	}

	/* (non-Javadoc)
	 * @see indexer.api.server.crawler.queue.BaseQueue#add(java.lang.Object)
	 */
	@Override
	public void add(E element) {
		queue.add(element);
	}

	/* (non-Javadoc)
	 * @see indexer.api.server.crawler.queue.BaseQueue#pop()
	 */
	@Override
	public E pop() throws QueueEmptyExcpetion {
		if(queue.isEmpty()){
			throw new QueueEmptyExcpetion("The queue is empty");
		}
		E res = queue.get(0);
		queue.remove(0);
		return res;
	}

	/* (non-Javadoc)
	 * @see indexer.api.server.crawler.queue.BaseQueue#top()
	 */
	@Override
	public E top() throws QueueEmptyExcpetion{
		if(queue.isEmpty()){
			throw new QueueEmptyExcpetion("The queue is empty");
		}
		return queue.get(0);
	}

	/* (non-Javadoc)
	 * @see indexer.api.server.crawler.queue.BaseQueue#bottom()
	 */
	@Override
	public E bottom() throws QueueEmptyExcpetion{
		if(queue.isEmpty()){
			throw new QueueEmptyExcpetion("The queue is empty");
		}
		int lasPos = queue.size()-1;
		return queue.get(lasPos);
	}

	/* (non-Javadoc)
	 * @see indexer.api.server.crawler.queue.BaseQueue#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return queue.isEmpty();
	}

}
