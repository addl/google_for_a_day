package indexer.api.server.crawler;

import indexer.api.server.crawler.queue.Queue;

/**
 * @author lion
 * Exception to be used with {@link Queue}
 *
 */
public class QueueEmptyExcpetion extends Exception {

	private static final long serialVersionUID = 1L;

	public QueueEmptyExcpetion(String msg) {
		super(msg);
	}

}
