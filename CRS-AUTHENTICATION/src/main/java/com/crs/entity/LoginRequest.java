/**
 * 
 */
package com.crs.entity;

import java.io.Serializable;

/**
 *  Class for handling Login requests
 */

public class LoginRequest implements Serializable {

	private String userEmail;
	private String userPassword;

	/**
	 * returns the user email
	 * 
	 * @return userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * sets the user Email
	 * 
	 * @param userEmail
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	/**
	 * return the user password
	 * 
	 * @return userPassword
	 */
	public String getUserPassword() {
		return userPassword;
	}

	/**
	 * sets the user password
	 * 
	 * @param userPassword
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

}
