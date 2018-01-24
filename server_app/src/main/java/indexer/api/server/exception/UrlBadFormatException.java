package indexer.api.server.exception;

/**
 * @author lion
 * 
 * Thrown when the system found a links that it is malformed
 */
public class UrlBadFormatException extends Exception {

	private static final long serialVersionUID = 1L;

	public UrlBadFormatException(String msg) {
		super(msg);
	}

}
