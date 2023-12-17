/**
 * 
 */
package com.crs.exception;

import org.springframework.stereotype.Component;

/**
 * Exception in case number of courses exceed the permissible number of courses
 */

@Component
public class CourseLimitExceededException extends Exception {

	String courseId;
	public CourseLimitExceededException  () {
		
	}
	
	public CourseLimitExceededException  (String courseId) {
		this.courseId = courseId;
	}
	
	public String getCourseId()  {
		return courseId;
	}
}
