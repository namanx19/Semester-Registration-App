/**
 * 
 */
package com.crs.entity;

import javax.persistence.Column;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import java.io.Serializable;

/**
 * Bean class for User entity
 */

@MappedSuperclass
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId")
	private int userId;
	@Column(name = "userName")
	private String userName;

	@Column(name = "userEmail")
	private String userEmail;

	@Column(name = "userPhonenumber")
	private long userPhonenumber;

	@Column(name = "userPassword")
	private String userPassword;

	/**
	 * @return userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * sets the userId
	 * 
	 * @param userId
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the userPassword
	 */
	public String getUserPassword() {
		return userPassword;
	}

	/**
	 * @param userPassword the userPassword to set
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * @param userEmail the userEmail to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	/**
	 * @return the userPhonenumber
	 */
	public long getUserPhonenumber() {
		return userPhonenumber;
	}

	/**
	 * @param userPhonenumber the userPhonenumber to set
	 */
	public void setUserPhonenumber(long userPhonenumber) {
		this.userPhonenumber = userPhonenumber;
	}

}
