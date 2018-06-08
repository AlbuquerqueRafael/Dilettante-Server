package com.api.dl.exceptions;

public class InvalidCredentialsException extends NullPointerException{
	private static final long serialVersionUID = -3123213;

	public InvalidCredentialsException (String message) {
	  super(message);
	}
}