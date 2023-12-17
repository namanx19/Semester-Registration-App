/**
 * 
 */
package com.crs.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.crs.entity.PaymentStudentMapper;
import com.crs.entity.Student;

/**
 * 
 */
public interface PaymentStudentMapperRepository extends CrudRepository<PaymentStudentMapper, Integer>{

	public List<PaymentStudentMapper> findByStudent(Student student);
}
