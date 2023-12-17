/**
 * 
 */
package com.crs.exception;

import org.springframework.stereotype.Component;

/**
 * Exception in case specific course is not found
 */
@Component
public class CourseNotFoundException extends Exception {

	String courseId;
	
	
	public CourseNotFoundException() {

	}
	
	public CourseNotFoundException(String courseId) {
		this.courseId = courseId;
	}
	
	public String getCourseId() {
		return courseId;
	}

}
