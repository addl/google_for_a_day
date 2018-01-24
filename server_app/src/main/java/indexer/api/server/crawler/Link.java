package indexer.api.server.crawler;


/**
 * @author lion
 *
 * A suitable class to create {@link Link} objects
 */
public class Link implements Comparable<Link> {

	
	/**
	 * The link, starting with http(s)
	 */
	private String url;

	/**
	 * the deep level of that link, 
	 * as the system establish taking account othe links
	 */
	private int deepLevel;

	public Link(String url, int deepLevel) {
		this.url = url;
		this.deepLevel = deepLevel;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getDeepLevel() {
		return deepLevel;
	}

	public void setDeepLevel(int deepLevel) {
		this.deepLevel = deepLevel;
	}

	@Override
	public boolean equals(Object arg0) {
		String outLink = ((Link) arg0).getUrl();
		return this.url.equalsIgnoreCase(outLink);
	}

	@Override
	public int compareTo(Link arg0) {
		if (this.deepLevel < arg0.getDeepLevel()) {
			return -1;
		}
		if (this.deepLevel > arg0.getDeepLevel()) {
			return 1;
		}
		return 0;
	}

	@Override
	public String toString() {
		return "URL: " + this.getUrl() + " - DeepLevel: " + this.getDeepLevel();
	}
}
