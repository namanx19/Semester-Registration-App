/**
 * 
 */
package com.crs.dto;

import java.io.Serializable;

/**
 * Data transfer Object for Course Deletion
 */
public class DropCourseDTO implements Serializable {
	private int studentId;
	private String courseId;
	public int getStudentId() {
		return studentId;
	}

	/**
	 * @param sets studentId
	 */
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
}
