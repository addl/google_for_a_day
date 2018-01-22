package indexer.api.server.crawler.queue;

import indexer.api.server.crawler.QueueEmptyExcpetion;

import java.util.LinkedList;
import java.util.List;


public abstract class Queue<E> implements BaseQueue<E> {
	
	protected List<E> queue;
	
	public Queue() {
		queue = new LinkedList<E>();
	}

	@Override
	public void add(E element) {
		queue.add(element);
	}

	@Override
	public E pop() throws QueueEmptyExcpetion {
		if(queue.isEmpty()){
			throw new QueueEmptyExcpetion("The queue is empty");
		}
		E res = queue.get(0);
		queue.remove(0);
		return res;
	}

	@Override
	public E top() throws QueueEmptyExcpetion{
		if(queue.isEmpty()){
			throw new QueueEmptyExcpetion("The queue is empty");
		}
		return queue.get(0);
	}

	@Override
	public E bottom() throws QueueEmptyExcpetion{
		if(queue.isEmpty()){
			throw new QueueEmptyExcpetion("The queue is empty");
		}
		int lasPos = queue.size()-1;
		return queue.get(lasPos);
	}

	@Override
	public boolean isEmpty() {
		return queue.isEmpty();
	}

}
