package com.api.dl.search.exceptions;

public class InvalidPublicationSearch extends NullPointerException{
	private static final long serialVersionUID = -123;

	public InvalidPublicationSearch (String message) {
	  super(message);
	}
}