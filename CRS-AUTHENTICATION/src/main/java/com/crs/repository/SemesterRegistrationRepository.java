/**
 * 
 */
package com.crs.repository;

import org.springframework.data.repository.CrudRepository;

import com.crs.entity.Admin;
import com.crs.entity.SemesterRegistration;

/**
 * 
 */
public interface SemesterRegistrationRepository extends CrudRepository<SemesterRegistration, Integer>
{
	
}
