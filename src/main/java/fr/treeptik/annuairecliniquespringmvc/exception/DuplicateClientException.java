package fr.treeptik.annuairecliniquespringmvc.exception;

public class DuplicateClientException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateClientException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicateClientException(String message) {
		super(message);
	}
	
	

}
