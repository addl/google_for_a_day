package indexer.api.server.dto;

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
