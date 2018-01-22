package indexer.api.server.crawler.queue;

import indexer.api.server.crawler.Link;


public class QueueSet extends Queue<Link> {
	
	private int maximumDeepLevel;
	
	public QueueSet(int maximumDeepLevel) {
		this.maximumDeepLevel = maximumDeepLevel;
	}
	
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
		if(!found & element.getDeepLevel() <= maximumDeepLevel){
			super.add(element);
		}
	}
	
	public int getMaximumDeepLevel() {
		return maximumDeepLevel;
	}
	
	public void setMaximumDeepLevel(int maximumDeepLevel) {
		this.maximumDeepLevel = maximumDeepLevel;
	}

}
