package com.api.dl.thread.exceptions;

public class InvalidThreadException extends NullPointerException{
	private static final long serialVersionUID = -45321234;

	public InvalidThreadException (String message) {
	  super(message);
	}
}