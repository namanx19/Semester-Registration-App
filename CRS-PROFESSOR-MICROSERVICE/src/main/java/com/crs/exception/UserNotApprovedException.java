/**
 * 
 */
package com.crs.exception;

import org.springframework.stereotype.Component;

/**
 * Exception in case User has not been registered/approved
 */
@Component
public class UserNotApprovedException extends Exception {
	int userId;

	public UserNotApprovedException() {

	}

	/**
	 * sets the userId
	 * 
	 * @param userId
	 */
	public UserNotApprovedException(int userId) {
		this.userId = userId;
	}

	/**
	 * returns the userId
	 * 
	 * @return userId
	 */
	public int getUserId() {
		return userId;
	}
}
