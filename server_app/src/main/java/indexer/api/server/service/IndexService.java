package indexer.api.server.service;

import indexer.api.server.model.Word;

public interface IndexService {
	
	public void createOrUpdateIndex(String word, String url, String pageTitle, int matches);
	
	public Word search(String query);
	
	public void clearDatabase() throws Exception;

}
