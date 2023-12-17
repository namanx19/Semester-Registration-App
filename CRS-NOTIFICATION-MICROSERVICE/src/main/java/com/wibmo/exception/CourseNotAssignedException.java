/**
 * 
 */
package com.crs.exception;

/**
 * Exception to handle courseAssignment
 */
public class CourseNotAssignedException extends Exception {
	
	String courseId;
	public CourseNotAssignedException() {

	}
	
	public CourseNotAssignedException(String courseId) {
		this.courseId = courseId;
	}
	
	public String getCourseId() {
		return courseId;
	}
}
