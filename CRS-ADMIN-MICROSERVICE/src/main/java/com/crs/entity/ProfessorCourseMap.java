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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Mapping class for professor and Courses
 */
@Entity
@Table(name = "professorcoursemapping")
public class ProfessorCourseMap implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "userId")
	private Professor professor;

	@ManyToOne
	@JoinColumn(name = "courseId")
	private CourseCatalog courseCatalog;

	@Column
	int isApproved;

	/**
	 * returns the id
	 * 
	 * @return id
	 */
	public long getId() {
		return id;
	}

	/**
	 * sets the id
	 * 
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * returns the professor object
	 * 
	 * @return professor
	 */
	public Professor getProfessor() {
		return professor;
	}

	/**
	 * sets the professor
	 * 
	 * @param professor
	 */
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	/**
	 * returns the course catalog
	 * 
	 * @return courseCatalog
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}

	/**
	 * sets the courseCatalog
	 * 
	 * @param courseCatalog
	 */
	public void setCourseCatalog(CourseCatalog courseCatalog) {
		this.courseCatalog = courseCatalog;
	}

	/**
	 * returns the approval status
	 * 
	 * @return isApproved
	 */
	public int getIsApproved() {
		return isApproved;
	}

	/**
	 * sets the approval status
	 * 
	 * @param isApproved
	 */
	public void setIsApproved(int isApproved) {
		this.isApproved = isApproved;
	}

}
