package indexer.api.server.exception;

/**
 * @author lion
 *
 * Thrown when a query not found results
 */
public class IndexerNotMatchesException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public IndexerNotMatchesException(String msg) {
		super(msg);
	}

}
