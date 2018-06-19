package com.api.dl.publication.exceptions;

public class PublicationOwnerException extends NullPointerException{
	private static final long serialVersionUID = -45321234;

	public PublicationOwnerException (String message) {
	  super(message);
	}
}