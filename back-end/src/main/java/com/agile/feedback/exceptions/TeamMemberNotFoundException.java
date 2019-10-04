package com.agile.feedback.exceptions;

public class TeamMemberNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TeamMemberNotFoundException(String message) {
		super(message);
	}

	public TeamMemberNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
