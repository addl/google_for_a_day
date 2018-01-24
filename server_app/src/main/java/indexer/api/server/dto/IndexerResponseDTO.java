package indexer.api.server.dto;

import org.springframework.http.HttpStatus;

/**
 * @author lion
 *
 * This object will be used to server each request, 
 * with the goal of serve a common response
 */
public class IndexerResponseDTO {
	
	/**
	 * The real data returned by server,
	 * it can be a list or any object
	 */
	private Object data;
    private HttpStatus status;
    private boolean error;
    private String msg;
    private String explanation;
    
	public IndexerResponseDTO(Object data, HttpStatus status, boolean error,
			String msg, String explanation) {
		this.data = data;
		this.status = status;
		this.error = error;
		this.msg = msg;
		this.explanation = explanation;
	}
	
	public IndexerResponseDTO(Object data, HttpStatus status, boolean error) {
		this.data = data;
		this.status = status;
		this.error = error;
	}

	public Object getData() {
		return data;
	}


	public void setData(Object data) {
		this.data = data;
	}


	public HttpStatus getStatus() {
		return status;
	}


	public void setStatus(HttpStatus status) {
		this.status = status;
	}


	public boolean isError() {
		return error;
	}


	public void setError(boolean error) {
		this.error = error;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public String getExplanation() {
		return explanation;
	}


	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

}
