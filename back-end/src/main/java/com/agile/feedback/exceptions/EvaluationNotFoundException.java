package com.agile.feedback.exceptions;

public class EvaluationNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EvaluationNotFoundException(String message) {
		super(message);
	}

	public EvaluationNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
