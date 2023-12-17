/**
 * 
 */
package com.crs.exception;

import org.springframework.stereotype.Component;

/**
 * Exception in case user is already present/registered/approved.
 */

@Component
public class UserAlreadyExistsException extends Exception {

	private String userEmail;

	public UserAlreadyExistsException() {

	}

	/**
	 * sets the userEmail
	 * 
	 * @param userEmail
	 */
	public UserAlreadyExistsException(String userEmail) {
		this.userEmail = userEmail;
	}

	/**
	 * returns the userEmail
	 * 
	 * @return userEmail
	 */
	public String getUserId() {
		return userEmail;
	}
}
