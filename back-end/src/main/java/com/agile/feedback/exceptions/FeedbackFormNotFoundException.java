package com.agile.feedback.exceptions;

public class FeedbackFormNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FeedbackFormNotFoundException(String message) {
		super(message);
	}

	public FeedbackFormNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
