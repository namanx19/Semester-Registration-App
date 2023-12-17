/**

 * 
 */
package com.crs.service;


import java.util.Map;

import org.springframework.stereotype.Service;

import com.crs.exception.UserNotFoundException;

/**
 * Admin Operations Interface
 */

@Service
public interface AdminOperation {

	/**
	 * To Approve Student Registration
	 */
	public void approveStudent();

	/**
	 * To Add a new Admin
	 * 
	 * @param User contains admin details
	 */
	public void approveAdmin(int userId);

	/**
	 * To assign which course will be taught by which professor
	 */
	public void assignCoursesProf();

	/**
	 * To Approve Student Course Registration
	 * 
	 * @return List<Boolean> contains true if courses are assigned successfully to
	 *         student otherwise false
	 */
	public Map<Integer,Boolean> approveCourseRegistration();

	/**
	 * To approve a specific student
	 * 
	 * @param studentId
	 */
	public void approveStudentById(int id) throws UserNotFoundException;

	/**
	 * To get admin by Email
	 * 
	 * @param userEmail
	 * @return
	 */
	public int getAdminByEmail(String userEmail);

}
