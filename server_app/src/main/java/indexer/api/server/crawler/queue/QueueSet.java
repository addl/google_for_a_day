package indexer.api.server.crawler.queue;

import indexer.api.server.crawler.Link;


/**
 * @author lion
 *
 * Specific Queue that use {@link Link} as object type, override some methods from {@link Queue}
 */
public class QueueSet extends Queue<Link> {
	
	/**
	 * Maximum Level allowed, this att is take into account at add time
	 */
	private int maximumDeepLevel;
	
	public QueueSet(int maximumDeepLevel) {
		this.maximumDeepLevel = maximumDeepLevel;
	}
	
	/* (non-Javadoc)
	 * @see indexer.api.server.crawler.queue.Queue#add(java.lang.Object)
	 * 
	 * Add or Update a link in the queue, if the URL is found 
	 * and the maximumDeepLevel is lower it will update the link object
	 */
	@Override
	public void add(Link element) {
		boolean found = false;
		for (int i = 0; i < queue.size() & !found; i++) {
			Link link = queue.get(i);
			/*If is the same URL*/
			if(link.equals(element)){				
				/*If is the same URL but the deep level is higher*/
				if(link.compareTo(element) == 1){
					/*Update the deep level to that url*/
					link.setDeepLevel(element.getDeepLevel());
				}
				found = true;
			}
		}
		/*If the Link is not found then add as new linkto be processed*/
		if(!found & element.getDeepLevel() <= maximumDeepLevel){
			super.add(element);
		}
	}
	
	/**
	 * @return maximumDeepLevel
	 */
	public int getMaximumDeepLevel() {
		return maximumDeepLevel;
	}
	
	/**
	 * @param maximumDeepLevel set the new maximumDeepLevel
	 */
	public void setMaximumDeepLevel(int maximumDeepLevel) {
		this.maximumDeepLevel = maximumDeepLevel;
	}

}
