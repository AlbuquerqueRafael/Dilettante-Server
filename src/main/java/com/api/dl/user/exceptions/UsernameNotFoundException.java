package com.api.dl.user.exceptions;

public class UsernameNotFoundException extends NullPointerException{
	private static final long serialVersionUID = -44;

	public UsernameNotFoundException (String message) {
	  super(message);
	}
}