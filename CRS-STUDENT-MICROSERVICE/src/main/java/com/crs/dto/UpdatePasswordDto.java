/**
 * 
 */
package com.crs.dto;

import java.io.Serializable;

/**
 * Data Transfer Object for updating passwords
 */
public class UpdatePasswordDto implements Serializable {
	private String userEmail;
	private String userPassword;
	private String userPasswordNew;

	/**
	 * returns user Email
	 * 
	 * @return userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * Sets user Email
	 * 
	 * @param userEmail
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	/**
	 * returns user Password
	 * 
	 * @return userPassword
	 */
	public String getUserPassword() {
		return userPassword;
	}

	/**
	 * Sets user Password
	 * 
	 * @param userPassword
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	/**
	 * returns New user Password
	 * 
	 * @return userPasswordNew
	 */
	public String getUserPasswordNew() {
		return userPasswordNew;
	}

	/**
	 * Sets new user password
	 * 
	 * @param userPasswordNew
	 */
	public void setUserPasswordNew(String userPasswordNew) {
		this.userPasswordNew = userPasswordNew;
	}

}
