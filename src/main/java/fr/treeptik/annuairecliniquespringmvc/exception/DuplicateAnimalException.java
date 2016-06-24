package fr.treeptik.annuairecliniquespringmvc.exception;

public class DuplicateAnimalException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateAnimalException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicateAnimalException(String message) {
		super(message);
	}
	
	

}
