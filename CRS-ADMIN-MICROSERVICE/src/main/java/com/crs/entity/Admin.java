/**
 * 
 */
package com.crs.entity;

import javax.persistence.Access;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Table;

/**
 * Bean class for User entity
 */

@Entity
@Table(name = "admin")
public class Admin extends User {

	@Column
	private int isApproved;

	/**
	 * returns the status of approval
	 * 
	 * @return isApproved
	 */
	public int getIsApproved() {
		return isApproved;
	}

	/**
	 * sets the status of approval
	 * 
	 * @param isApproved
	 */
	public void setIsApproved(int isApproved) {
		this.isApproved = isApproved;
	}
}
