/**
 * 
 */
package com.crs.repository;

import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.crs.entity.Student;


/**
 * Student repository related to student CRUD operations
 */
@Repository
public interface StudentRepository extends CrudRepository<Student,Integer> {
/**
 * marks student as registered according to the studentId
 * @param studentId
 */
	@Modifying
	@Transactional
	@Query(value=" UPDATE studentcoursemapping SET isRegister=1 WHERE userId=?1", nativeQuery = true)
	public void registerCourses(@Param("studentId")int studentId);
	

/**
 * returns the count of courses from mapping table according to given studentId
 * @param studentId
 * @return courseCount
 */
	@Query(value="SELECT COUNT(courseId) as courseCount FROM studentcoursemapping WHERE userId=?1", nativeQuery=true)
	public int getCourseCount(@Param("userId")int studentId);
	
/**
 * delete the entry of student according to studentId and courseId specified
 * @param studentId
 * @param courseId
 */
	@Modifying
	@Query(value="DELETE FROM studentcoursemapping WHERE userId=? && courseId=?", nativeQuery =  true)
	public void dropCourses(@Param("userId")int studentId,@Param("courseId")String courseId);

/**
 * return the status that is student registered or not for a particular course
 * @param studentId
 * @param courseId
 * @return coursePreference
 */
	@Query(value="SELECT isRegister FROM studentcoursemapping WHERE userId=:studentId AND courseId=:courseId", nativeQuery = true )
	public Integer findCoursePreference(@Param("studentId")int studentId,@Param("courseId") String courseId);

/**
 * returns the status of approval
 * @param studentId
 * @return isApproved
 */
	@Query(value="SELECT COUNT(*) FROM gradecard where userId=?1", nativeQuery = true)
	public int isApproved(@Param("userId")int studentId);
/**
 * returns the count of student matching with given mail
 * @param userEmail
 * @return findByEmail
 */
	@Query(value="SELECT COUNT(*) FROM student WHERE userEmail=?1", nativeQuery =  true)
	public int findByEmail(@Param("userEmail")String userEmail);
	
/**returns the list student Ids.
 * @return list of studentIds
 */
	@Query(value="SELECT DISTINCT(userId) as uniqueStudent FROM studentcoursemapping", nativeQuery =  true)
	public List<Integer> getStudentIds();
/**
 * returns the count of registered students 
 * @param studentId
 * @return count of registered students
 */
	@Query(value="SELECT COUNT(*) FROM studentcoursemapping WHERE userId=:studentId AND isRegister=1", nativeQuery = true)
	public Integer isStudentRegistered(@Param("studentId")int studentId);
/**
 * returns the status of approval of student registration
 * @param studentId
 * @return course registration approval
 */
	@Query(value="SELECT COUNT(*) FROM gradecard where userId=?1",nativeQuery =  true)
	public int isCourseRegistrationApproved(@Param("userId")int studentId);
/**
 * returns the list of custom objects containing details related to course ID and category
 * @param studentId
 * @return list of custom objects
 */
	@Query(value="SELECT courseId, coursecategory FROM studentcoursemapping WHERE userId=?", nativeQuery =  true)
	public List<Object[]> getStudentCourseData(@Param("studentId")int studentId);
/**
 * return the count of courses
 * @param courseId
 * @return coursecount
 */
	@Query(value="SELECT COUNT(courseId) as courseCount FROM studentcoursemapping WHERE courseId=?", nativeQuery =  true)
	public int getStudentCourseCount(@Param("courseId")String courseId);
/**
 * returns the list of custom object containing details related to listing of course
 * @param studentId
 * @return list of custom object
 */
	@Query(value="SELECT gradecard.courseId, courseCatalog.courseName FROM "
			+ "gradecard as gradecard INNER JOIN coursecatalog as courseCatalog ON"
			+ " gradecard.courseId=courseCatalog.courseId WHERE gradecard.userId=?1", nativeQuery = true)
	public List<Object[]> listCourse(@Param("studentId")int studentId);
/**
 * returns the list of object which contain details related to course and student mapping details
 * @param userId
 * @return list of custom object
 */
	@Query(value="SELECT c.courseId,c.courseName FROM coursecatalog c INNER JOIN crs.studentcoursemapping scm ON c.courseId=scm.courseId WHERE scm.userId=?1", nativeQuery =  true)
	public List<Object[]> getAddedCourses(@Param("userId")int userId);

/**
 * sets approval of student related to given studentID
 * @param id
 */
	@Modifying
	@Transactional
	@Query(value="UPDATE student SET isApproved=1 WHERE userId=?1",nativeQuery =  true)
	public void setApprovedStudentById(@Param("userId")int id);
	
	
}
