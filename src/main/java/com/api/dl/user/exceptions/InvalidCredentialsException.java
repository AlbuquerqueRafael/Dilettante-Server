package com.api.dl.user.exceptions;

public class InvalidCredentialsException extends NullPointerException{
	private static final long serialVersionUID = -123;

	public InvalidCredentialsException (String message) {
	  super(message);
	}
}