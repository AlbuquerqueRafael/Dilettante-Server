package com.api.dl.user.exceptions;

public class UserInvalidException extends NullPointerException{
	private static final long serialVersionUID = -4554;

	public UserInvalidException (String message) {
	  super(message);
	}
}