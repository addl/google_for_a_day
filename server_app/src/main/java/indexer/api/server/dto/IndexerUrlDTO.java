package indexer.api.server.dto;

/**
 * @author lion
 *
 * Class used to convert or serialize into objects 
 * the links coming from clients apps
 */
public class IndexerUrlDTO {
	
	private String url;
	
	public IndexerUrlDTO() {
		
	}

	public IndexerUrlDTO(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

}
