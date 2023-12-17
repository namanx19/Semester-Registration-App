/**
 * 
 */
package com.crs.exception;

import org.springframework.stereotype.Component;

/**
 * Exception in case user does not exist.
 */
@Component
public class UserNotFoundException extends Exception {

	long userId;

	public UserNotFoundException() {

	}

	/**
	 * sets the userId
	 * 
	 * @param userId
	 */
	public UserNotFoundException(long userId) {
		this.userId = userId;
	}

	/**
	 * returns the userId
	 * 
	 * @return userId
	 */
	public long getUserId() {
		return userId;
	}

}
