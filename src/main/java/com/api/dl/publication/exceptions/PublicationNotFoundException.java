package com.api.dl.publication.exceptions;

public class PublicationNotFoundException extends NullPointerException{
	private static final long serialVersionUID = -45321234;

	public PublicationNotFoundException (String message) {
	  super(message);
	}
}