package indexer.api.server.exception;

import javax.persistence.EntityNotFoundException;

public class IndexerEntityNotFoundException extends EntityNotFoundException{
	
	private static final long serialVersionUID = 1L;

	public IndexerEntityNotFoundException(String message) {
		super(message);
	}

}
