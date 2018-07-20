package com.api.dl.exceptions;

public class UploadException extends NullPointerException{
	private static final long serialVersionUID = -235033835404438L;

	public UploadException (String message) {
	  super(message);
	}
}