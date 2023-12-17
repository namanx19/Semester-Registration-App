/**
 * 
 */
package com.crs.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crs.entity.CourseCatalog;

/**
 * Repository related to Course CRUD operations
 */
@Repository
public interface CourseRepository extends CrudRepository<CourseCatalog, Integer> {

	public CourseCatalog findByCourseId(String CourseId);
}
