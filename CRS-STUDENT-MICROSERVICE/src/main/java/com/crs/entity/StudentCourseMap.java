/**
* 
*/
package com.crs.entity;

import java.io.Serializable;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Class representing mapping between Students and Courses.
 */
@Entity
@Table(name = "studentcoursemapping")
public class StudentCourseMap implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;

	@ManyToOne
	@JoinColumn(name = "userId")
	private Student student;

	@ManyToOne
	@JoinColumn(name = "courseId")
	private CourseCatalog course;

	@Column(name = "coursecategory")
	private int coursePref;

	@Column(name = "isRegister")
	private int isRegister;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the coursePref
	 */
	public int getCoursePref() {
		return coursePref;
	}

	/**
	 * @param coursePref the coursePref to set
	 */
	public void setCoursePref(int coursePref) {
		this.coursePref = coursePref;
	}

	/**
	 * @param isRegister the isRegister to set
	 */
	public void setIsRegister(int isRegister) {
		this.isRegister = isRegister;
	}

	/**
	 * returns the registration status
	 * 
	 * @return isRegister
	 */
	public int getIsRegister() {
		return isRegister;
	}

	/**
	 * returns the student status
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
	 * returns the courses
	 * 
	 * @return course
	 */
	public CourseCatalog getCourse() {
		return course;
	}

	/**
	 * sets the course
	 * 
	 * @param course
	 */
	public void setCourse(CourseCatalog course) {
		this.course = course;
	}
}