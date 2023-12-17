package com.crs.service;

import org.apache.logging.log4j.LogManager;


import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.crs.entity.*;
import com.crs.repository.*;
import com.crs.exception.UserNotFoundException;
import com.crs.validator.*;

/**
 * Admin Operations Implementation
 */
@Service
public class AdminOperationImpl implements AdminOperation {

	private Logger log = LogManager.getLogger();

	Scanner scan = new Scanner(System.in);

	@Autowired
	public StudentRepository studentRepository;
	@Autowired
	public AdminRepository adminRepository;



	@Autowired
	public ProfessorRepository professorRepository;

	@Autowired
	public ProfessorCourseMappingRepository professorCourseMappingRepository;

	@Autowired
	public ValidatorInterface validate;
//	@Autowired
//	public NotificationOperation notification;

	@Autowired
	public CourseRepository courseRepository;

	/**
	 * To Approve Student Registration
	 */
	@Override
	public void approveStudent() {
		// TODO Auto-generated method stub
		adminRepository.approveStudentRegistration();
	}

	/**
	 * To Add a new Admin
	 * 
	 * @param User contains Admin details
	 */
	@Override
	public void approveAdmin(int userId) {
		adminRepository.setAdminApproval(userId);
		
	}

	/**
	 * To assign which course will be taught by which professor
	 */
	@Override
	public void assignCoursesProf() {
		List<Integer> professorsIds = professorCourseMappingRepository.listProfessorIds();
		Set<String> set = new HashSet<>();
		for (int professorId : professorsIds) {
			Professor professor = professorRepository.findById(professorId).get();
			List<String> coursesIds = professorCourseMappingRepository.getProfessorCourses(professorId);
			for (String courseId : coursesIds) {
				CourseCatalog course = courseRepository.findByCourseId(courseId);
				if (!set.contains(courseId)) {
					set.add(courseId);
					ProfessorCourseMap professorCourseMap = professorCourseMappingRepository
							.findByProfessorAndCourseCatalog(professor, course);
					professorCourseMap.setIsApproved(1);
					professorCourseMappingRepository.save(professorCourseMap);
				} else
					System.out.println("Course already assigned to another professor");
			}
		}
	}

	/**
	 * To Approve Student Course Registration
	 * 
	 * @return List<Boolean> contains true if courses are assigned successfully to
	 *         student otherwise false
	 */
	@Override
	public Map<Integer,Boolean> approveCourseRegistration() {
		// TODO Auto-generated method stub
		Map<Integer,Boolean> isSuccess = validate.courseRegistrationValidator();
		return isSuccess;
	}

	/**
	 * To approve a specific student
	 * 
	 * @param studentId
	 */
	@Override
	public void approveStudentById(int id) throws UserNotFoundException {

		if (studentRepository.findById(id).isEmpty() == true) {
			throw new UserNotFoundException(id);
		}
		studentRepository.setApprovedStudentById(id);
	}

	/**
	 * To get Admin by email
	 * 
	 * @param userEmail
	 * @return Admin
	 */
	@Override
	public int getAdminByEmail(String userEmail) {
		// TODO Auto-generated method stub
		return adminRepository.getAdminById(userEmail);
	}

}
