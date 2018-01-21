package indexer.api.server.parser;

import java.util.List;


public interface Parser {
	
	public void parse(String content);
	
	public List<String> getLinks();
	
}
