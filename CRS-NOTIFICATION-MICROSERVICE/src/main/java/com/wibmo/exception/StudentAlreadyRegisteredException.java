/**
 * 
 */
package com.crs.exception;

import org.springframework.stereotype.Component;

/**
 * Exception in case student is already registered
 */

@Component
public class StudentAlreadyRegisteredException extends Exception {

	String userEmail;

	public StudentAlreadyRegisteredException() {

	}

	/**
	 * sets the userEmail
	 * 
	 * @param userEmail
	 */
	public StudentAlreadyRegisteredException(String userEmail) {
		this.userEmail = userEmail;
	}

	/**
	 * returns the userEmail
	 * 
	 * @return userEmail
	 */

	public String getStudentEmail() {
		return userEmail;
	}

}
