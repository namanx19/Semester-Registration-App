/**
 * 
 */
package com.crs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.crs.constant.SQLConstants;
import com.crs.entity.Professor;

/**
 * 
 */
@Repository
public interface ProfessorRepository extends CrudRepository<Professor,Integer> {
	/**
	 * sets the grade in gradecard table related to particular student and subject
	 * 
	 * @param grade
	 * @param studentId
	 * @param courseId
	 */
	@Modifying
	@Query(value = SQLConstants.SET_GRADES, nativeQuery = true)
	public void setGrades(@Param("grade") String grade, @Param("userId") int studentId,
			@Param("courseId") int courseId);

	/**
	 * returns list of object where each object is having student details
	 * 
	 * @param courseId
	 * @return list of custom objects
	 */
	@Query(value = SQLConstants.FIND_STUDENT_BY_COURSE_ID, nativeQuery = true)
	public List<Object[]> findStudentByCourseId(@Param("courseId") int courseId);

	/**
	 * returns number of professor by emailId entered
	 * 
	 * @param userEmail
	 * @return professorId
	 */
	@Query(value = SQLConstants.SEARCH_PROFESSOR, nativeQuery = true)
	public int findProfessorByEmail(@Param("userEmail") String userEmail);

	/**
	 * returns professorId by emailId entered
	 * 
	 * @param userEmail
	 * @return professor
	 */
	@Query(value = SQLConstants.SELECT_PROFESSOR_BY_EMAIL, nativeQuery = true)
	public int getProfessorById(@Param("userEmail") String userEmail);

	/**
	 * returns list of object where each object contains details about courses
	 * 
	 * @param userId
	 * @return list of custom Objects
	 */
	@Query(value = SQLConstants.LIST_OF_APPROVED_COURSES, nativeQuery = true)
	public List<Object[]> listOfApprovedCourses(@Param("professorId") int userId);

}
