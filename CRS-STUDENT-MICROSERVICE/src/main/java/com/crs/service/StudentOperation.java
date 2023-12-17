/**
 * 
 */
package com.crs.service;

import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.crs.dto.AddCourseDto;
import com.crs.dto.GradeCardResponseDTO;
import com.crs.dto.RegisterUserDto;
import com.crs.entity.CourseCatalog;
import com.crs.entity.User;
import com.crs.exception.*;

/**
 * Student Operation Interface
 */
@Service
@Transactional
public interface StudentOperation {
	
	/**
	 * Student Register for course
	 * @param studentId
	 */
	public void registerCourses(int studentId) throws UserNotFoundException;
	
	/**
	 * To add new course for registration
	 * @param studCoMap
	 * @throws DuplicateCourseEntryException
	 */
	@Transactional
	public void addCourses(int userId,AddCourseDto addCourseDto) throws CourseNotFoundException,CourseLimitExceededException,UserNotFoundException ;
	
	/**
	 * To drop course from registration list
	 * @param studentId
	 * @param courseId
	 * @return
	 * @throws CourseNotFoundException
	 * @throws UserNotFoundException
	 */
	public int dropCourses(int studentId, String courseId) throws CourseNotFoundException,UserNotFoundException;
	
	
	/**
	 * To get a list of registered course.
	 * @param studentId
	 * @return a map of registered courseId and their name.
	 * @throws UserNotApprovedException
	 */
	public Map<Integer,String> listCourse(int studentId) throws UserNotApprovedException;
	
	/**
	 * To view the list of offered courses
	 */
	public List<CourseCatalog> viewCourseCatalog();

	/**
	 * To view the Grade card of all the courses of a student.
	 * @param studId
	 * @throws UserNotFoundException
	 */
	public GradeCardResponseDTO viewReportCard(int studId) throws UserNotApprovedException;

	/**
	 * To check if a student is registered of not.
	 * @param userId
	 * @return True if student is registered else returns False.
	 */
	public int isApproved(int userId) ;

	/**
	 * To get the Student by Email
	 * @param userEmail
	 * @return count of student with given email
	 */
	public int getStudentByEmail(String userEmail);

	/**
	 * To get the list of courses
	 * @param userId
	 * @return Map<Integer,String> contains courseId and courseName with userId
	 */
	public Map<Integer, String> getAddedCourses(int userId);
	
	/**
	 * Check whether student is registered or not
	 * 
	 * @param userId
	 * @return 1 if student is registered
	 */
	public Integer isStudentRegistered(int userId);
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public boolean isRegistered(int userId) ;
	
}
		