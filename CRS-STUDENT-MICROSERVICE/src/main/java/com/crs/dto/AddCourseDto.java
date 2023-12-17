/**
 * 
 */
package com.crs.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * Data transfer Object for Course addition
 */
public class AddCourseDto {
	
	
	Map<String,Integer> courses= new HashMap<>();

	public Map<String, Integer> getCourses() {
		return courses;
	}

	public void setCourses(Map<String, Integer> courses) {
		this.courses = courses;
	}

}
