package com.api.dl.search.exceptions;

public class InvalidSearchException extends NullPointerException{
	private static final long serialVersionUID = -123;

	public InvalidSearchException (String message) {
	  super(message);
	}
}