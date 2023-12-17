/**
 * 
 */
package com.crs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *  Class for mapping notification with student
 */
@Entity
@Table(name = "notificationstudentmapping")
public class NotificationStudentMapping {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;

	@ManyToOne
	@JoinColumn(name = "userId")
	private Student student;

	@ManyToOne
	@JoinColumn(name = "notificationId")
	private Notification notification;

	public NotificationStudentMapping() {
	}

	/**
	 * parameterized constructor
	 * 
	 * @param student
	 * @param notification
	 */
	public NotificationStudentMapping(Student student, Notification notification) {
		this.student = student;
		this.notification = notification;
	}

	/**
	 * returns the id
	 * 
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * sets the id
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * returns the student object
	 * 
	 * @return student
	 */
	public Student getStudent() {
		return student;
	}

	/**
	 * sets the student object
	 * 
	 * @param student
	 */
	public void setStudent(Student student) {
		this.student = student;
	}

	/**
	 * returns the notification
	 * 
	 * @return notification
	 */
	public Notification getNotification() {
		return notification;
	}

	/**
	 * sets the notification
	 * 
	 * @param notification
	 */
	public void setNotification(Notification notification) {
		this.notification = notification;
	}

}
