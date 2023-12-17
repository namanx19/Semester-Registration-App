/**
 * 
 */
package com.crs.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class to represent Notifications
 */
@Entity
@Table(name = "notification")
public class Notification implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notificationId")
	private int id;
	@Column
	private String notificationMessage;

	public Notification() {
	}

	/**
	 * parameterized constructor
	 * 
	 * @param id
	 * @param notificationMessage
	 */
	public Notification(int id, String notificationMessage) {
		this.id = id;
		this.notificationMessage = notificationMessage;
	}

	/**
	 * Sets the parameterized
	 * 
	 * @param notification
	 */
	public Notification(Notification notification) {
		this.id = notification.getId();
		this.notificationMessage = notification.notificationMessage;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return Notification ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param Set the Notification ID
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return Notification Message
	 */
	public String getNotificationMessage() {
		return notificationMessage;
	}

	/**
	 * @param Set Notification Message
	 */
	public void setNotificationMessage(String notificationMessage) {
		this.notificationMessage = notificationMessage;
	}

}
