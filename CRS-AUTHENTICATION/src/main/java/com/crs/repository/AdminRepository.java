/**
 * 
 */
package com.crs.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.crs.constant.SQLConstants;
import com.crs.entity.Admin;

/**
 * Repository related to Admin CRUD operations
 */
@Repository
public interface AdminRepository extends CrudRepository<Admin, Integer> {

	/**
	 * Approves the student course Registration
	 */
	@Modifying
	@Transactional
	@Query(value = "UPDATE student SET isApproved=1", nativeQuery = true)
	public void approveStudentRegistration();

	/**
	 * Approve student course registration by student Id
	 * 
	 * @param userId
	 */
	@Transactional
	@Modifying
	@Query(value = SQLConstants.APPROVE_STUDENT_BY_ID, nativeQuery = true)
	public void approveStudentRegistrationById(@Param("userId") int userId);

	/**
	 * returns list of distinct professor Ids related to particular course.
	 * 
	 * @return list of professorId
	 */
	@Query(value = SQLConstants.SELECT_PROFESSORS_BY_ID, nativeQuery = true)
	public List<Integer> getProfessorsIds();

	/**
	 * returns list of courses related to particular professor
	 * 
	 * @param professorId
	 * @return list of courses
	 */
	@Query(value = SQLConstants.SELECT_PROFESSOR_COURSES, nativeQuery = true)
	public List<Integer> getProfessorCourses(@Param("professorId") int professorId);

	/**
	 * returns userId of admin by entered email
	 * 
	 * @param userEmail
	 * @return adminId
	 */
	@Query(value = SQLConstants.SEARCH_ADMIN, nativeQuery = true)
	public int getAdminById(@Param("userEmail") String userEmail);

	/**
	 * sets the rejection status of studentId
	 * 
	 * @param studentId
	 */
	@Modifying
	@Query(value = SQLConstants.STUDENT_REGISTRATION_REJECTION, nativeQuery = true)
	public void setRejectionStatus(@Param("studentId") int studentId);

	/**
	 * returns the count of user where email id is matching
	 * 
	 * @param userEmail
	 * @return count
	 */
	@Query(value = "SELECT COUNT(*) FROM admin WHERE userEmail=?1", nativeQuery = true)
	public int findByEmail(@Param("userEmail") String userEmail);

	@Modifying
	@Transactional
	@Query(value="UPDATE admin SET isApproved=1 WHERE userId=?1",nativeQuery=true)
	public void setAdminApproval(@Param("userId")int userId);

	
	public Admin findByUserEmail(String email); 
}
