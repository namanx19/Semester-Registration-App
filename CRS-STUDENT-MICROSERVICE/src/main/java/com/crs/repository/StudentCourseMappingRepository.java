/**
 * 
 */
package com.crs.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crs.entity.CourseCatalog;
import com.crs.entity.Student;
import com.crs.entity.StudentCourseMap;

/**
 * Repository related to student course mapping
 */
@Repository
public interface StudentCourseMappingRepository extends CrudRepository<StudentCourseMap, Integer>  {

/**
 * to get list of studentcoursemapping by course catalog details
 * @param course
 * @return List<StudentCourseMap>
 */
	public List<StudentCourseMap> findByCourse(CourseCatalog course); 
	
	public int countByStudentAndIsRegister(Student student, int isRegister);
}
