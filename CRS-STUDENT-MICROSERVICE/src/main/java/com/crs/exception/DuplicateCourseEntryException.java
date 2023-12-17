/**
 * 
 */
package com.crs.exception;

import org.springframework.stereotype.Component;

/**
 * Exception in case a course is already registered.
 */
@Component
public class DuplicateCourseEntryException extends Exception {
	private String courseId;
	
	public DuplicateCourseEntryException() {

	}
	
	public DuplicateCourseEntryException(String courseId) {
		this.courseId = courseId;
	}
	
	public String getCourseid() {
		return courseId;
	}
}
