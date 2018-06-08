package com.api.dl.exceptions;

public class MessageInvalidException extends NullPointerException{
	private static final long serialVersionUID = -2378608363404438L;

	public MessageInvalidException (String message) {
	  super(message);
	}
}