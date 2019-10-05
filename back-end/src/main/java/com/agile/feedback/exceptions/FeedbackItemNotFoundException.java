package com.agile.feedback.exceptions;

public class FeedbackItemNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FeedbackItemNotFoundException(String message) {
		super(message);
	}

	public FeedbackItemNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
