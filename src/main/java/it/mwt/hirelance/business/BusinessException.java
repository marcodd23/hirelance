package it.mwt.hirelance.business;

public class BusinessException extends Exception {
	private static final long serialVersionUID = 1L;

	private ExceptionCause exceptionCause;

	public BusinessException(ExceptionCause exceptionCause) {
		this.exceptionCause = exceptionCause;
	}

	public BusinessException(ExceptionCause exceptionCause, Throwable cause) {
		super(cause);
		this.exceptionCause = exceptionCause;
	}

	public BusinessException() {
		super();
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	
	public ExceptionCause getExceptionCause() {
		return exceptionCause;
	}

	public void setExceptionCause(ExceptionCause exceptionCause) {
		this.exceptionCause = exceptionCause;
	}


	public enum ExceptionCause {

		NOT_FOUND("Page not found"), 
		NOT_AUTHORIZED("Access Denied !!");

		private final String cause;

		private ExceptionCause(final String cause) {
			this.cause = cause;
		}

		@Override
		public String toString() {
			return cause;
		}

	}

}
