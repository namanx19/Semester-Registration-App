/**
 * 
 */
package com.crs.dto;

import java.util.HashMap;

import java.util.Map;

/**
 * Data Transfer Object for GradeCardResponse
 */
public class GradeCardResponseDTO {
	private int studentId;
	Map<String,Map<String,String>>gradeDetails=new HashMap<>();
	
	/**
	 * Adds grade card details
	 * 
	 * @param courseId
	 * @param courseName
	 * @param grades
	 */
	public void addGradeDetails(String courseId,String courseName,String grades)
	{
		Map<String,String>innerMap=new HashMap<>();
		innerMap.put(courseName, grades);
		gradeDetails.put(courseId, innerMap);
	}

	/**
	 * @return the gradeDetails
	 */
	public Map<String, Map<String, String>> getGradeDetails() {
		return gradeDetails;
	}

	/**
	 * @param gradeDetails the gradeDetails to set
	 */
	public void setGradeDetails(Map<String, Map<String, String>> gradeDetails) {
		this.gradeDetails = gradeDetails;
	}

	/**
	 * @return the studentId
	 */
	public int getStudentId() {
		return studentId;
	}

	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

}