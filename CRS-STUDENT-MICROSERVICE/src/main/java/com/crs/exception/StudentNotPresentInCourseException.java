/**
 * 
 */
package com.crs.exception;

/**
 * 
 */
public class StudentNotPresentInCourseException extends Exception {
	
	
	private int userId;

	public StudentNotPresentInCourseException() {

	}

	/**
	 * sets the userEmail
	 * 
	 * @param userEmail
	 */
	public StudentNotPresentInCourseException(int userEmail) {
		this.userId = userEmail;
	}

	/**
	 * returns the userEmail
	 * 
	 * @return userEmail
	 */
	public int getUserId() {
		return userId;
	}

}
