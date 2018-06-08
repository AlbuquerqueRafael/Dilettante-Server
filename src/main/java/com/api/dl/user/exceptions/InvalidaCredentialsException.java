package com.api.dl.user.exceptions;

public class InvalidaCredentialsException extends NullPointerException{
	private static final long serialVersionUID = -123;

	public InvalidaCredentialsException (String message) {
	  super(message);
	}
}