package com.agile.feedback.exceptions;

public class CompanyNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CompanyNotFoundException(String message) {
		super(message);
	}

	public CompanyNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
