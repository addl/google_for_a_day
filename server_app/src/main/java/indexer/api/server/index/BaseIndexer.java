package indexer.api.server.index;


public interface BaseIndexer {

	public void addToIndex(String html, String url);
	
	public Object search(String query);
	
	public void clearIndex() throws Exception;

}