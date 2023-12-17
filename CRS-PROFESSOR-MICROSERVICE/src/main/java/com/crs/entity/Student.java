/**
 * 
 */
package com.crs.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Bean class for Student entity
 */
@Entity
@Table(name = "student")
public class Student extends User {

	@Column(name = "isApproved")
	private int isapproved;
	@Column(name="courseRegistrationStatus")
	private int courseRegistrationStatus;
	
	
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private SemesterRegistration semesterRegistration;
    
    
    
	public Student() {

	}

	/**
	 * parameterized constructor which sets the student object properties
	 * @param student
	 */
	public Student(Student student) {
		this.setUserId(student.getUserId());
		this.setUserName(student.getUserName());
		this.setUserEmail(student.getUserEmail());
		this.setUserPassword(student.getUserPassword());
		this.setUserPhonenumber(student.getUserPhonenumber());
		this.isapproved = student.isapproved;
	}

	/**
	 * @return the semesterRegistration
	 */
	public SemesterRegistration getSemesterRegistration() {
		return semesterRegistration;
	}




	/**
	 * @param semesterRegistration the semesterRegistration to set
	 */
	public void setSemesterRegistration(SemesterRegistration semesterRegistration) {
		this.semesterRegistration = semesterRegistration;
	}




	/**
	 * @return the courseRegistrationStatus
	 */
	public int getCourseRegistrationStatus() {
		return courseRegistrationStatus;
	}




	/**
	 * @param courseRegistrationStatus the courseRegistrationStatus to set
	 */
	public void setCourseRegistrationStatus(int courseRegistrationStatus) {
		this.courseRegistrationStatus = courseRegistrationStatus;
	}



	


	/**
	 * returns the approval status
	 * 
	 * @return isApproved
	 */
	public int getIsapproved() {
		return isapproved;
	}

	/**
	 * sets the approval status
	 * 
	 * @param isapproved
	 */
	public void setIsapproved(int isapproved) {
		this.isapproved = isapproved;
	}

}
