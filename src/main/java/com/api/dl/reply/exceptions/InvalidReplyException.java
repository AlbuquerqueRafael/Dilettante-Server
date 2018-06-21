package com.api.dl.reply.exceptions;

public class InvalidReplyException extends NullPointerException{
	private static final long serialVersionUID = -45321234;

	public InvalidReplyException (String message) {
	  super(message);
	}
}