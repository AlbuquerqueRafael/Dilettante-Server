package com.api.dl.publication.exceptions;

public class InvalidPublicationException extends NullPointerException{
	private static final long serialVersionUID = -45321234;

	public InvalidPublicationException (String message) {
	  super(message);
	}
}