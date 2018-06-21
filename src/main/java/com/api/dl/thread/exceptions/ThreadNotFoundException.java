package com.api.dl.thread.exceptions;

public class ThreadNotFoundException extends NullPointerException{
	private static final long serialVersionUID = -45321234;

	public ThreadNotFoundException (String message) {
	  super(message);
	}
}