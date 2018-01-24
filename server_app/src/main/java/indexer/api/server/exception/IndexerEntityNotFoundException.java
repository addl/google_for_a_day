package indexer.api.server.exception;

import javax.persistence.EntityNotFoundException;

/**
 * @author lion
 * 
 * Thrown when an entity id is not found
 */
public class IndexerEntityNotFoundException extends EntityNotFoundException{
	
	private static final long serialVersionUID = 1L;

	public IndexerEntityNotFoundException(String message) {
		super(message);
	}

}
