/**
 * 
 */
package com.crs.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crs.dto.RegisterUserDto;
import com.crs.entity.CourseCatalog;
import com.crs.entity.GradeCard;
import com.crs.entity.Professor;
import com.crs.entity.ProfessorCourseMap;
import com.crs.entity.Student;
import com.crs.entity.StudentCourseMap;
import com.crs.exception.CourseNotAssignedException;
import com.crs.exception.CourseNotFoundException;
import com.crs.exception.UserAlreadyExistsException;
import com.crs.exception.UserNotFoundException;
import com.crs.repository.CourseRepository;
import com.crs.repository.GradeCardRepository;
import com.crs.repository.ProfessorCourseMappingRepository;
import com.crs.repository.ProfessorRepository;
import com.crs.repository.StudentCourseMappingRepository;
import com.crs.repository.StudentRepository;

/**
 * Professor Service Implementation
 */
@Service
public class ProfessorOperationImpl implements ProfessorOperation {
	@Autowired
	ProfessorRepository professorDao;
	@Autowired
	StudentRepository studentDao;
	@Autowired
	CourseRepository courseDao;
	@Autowired
	GradeCardRepository gradeCardRepository;

	@Autowired
	private StudentCourseMappingRepository studentCourseRepo;

	@Autowired
	private ProfessorCourseMappingRepository professorCoMapRepo;

	/**
	 * To set the grades of a student
	 * 
	 * @param studentId
	 * @param courseId
	 * @param grade
	 * @throws UserNotFoundException
	 * @throws CourseNotFoundException
	 */
	@Override
	@Transactional
	public void setGrades(int professorId, int studentId, String courseId, String grade)
			throws UserNotFoundException, CourseNotFoundException, CourseNotAssignedException {

		if (courseDao.findByCourseId(courseId) == null) {
			throw new CourseNotFoundException(courseId);
		} else if (studentDao.findById(studentId) == null) {
			throw new UserNotFoundException(studentId);
		}
		ProfessorCourseMap professorCourseMap = professorCoMapRepo.findByProfessorAndCourseCatalog(
				professorDao.findById(professorId).get(), courseDao.findByCourseId(courseId));
		if ((professorCourseMap == null)
				|| ((professorCourseMap != null) && (professorCourseMap.getIsApproved() == 0))) {
			throw new CourseNotAssignedException(courseId);
		}
		GradeCard gradeCard = gradeCardRepository.findByStudentAndCatalog(studentDao.findById(studentId).get(),
				courseDao.findByCourseId(courseId));
		gradeCard.setGrade(grade);
		gradeCardRepository.save(gradeCard);
	}

	/**
	 * To request to teach a specific course
	 * 
	 * @param professorid
	 * @param courseIdList
	 * @throws CourseNotFoundException
	 */
	@Override
	@Transactional
	public void requestCourseOffering(int professorid, List<String> courseIdList)
			throws CourseNotFoundException, UserNotFoundException {

		// TODO Auto-generated method stub
		if (professorDao.findById(professorid).isEmpty() == true) {
			throw new UserNotFoundException(professorid);
		}
		for (String courseId : courseIdList) {
			if (courseDao.findByCourseId(courseId) == null)
				throw new CourseNotFoundException(courseId);
		}

		courseIdList.forEach((courseId) -> {
			ProfessorCourseMap profCoMap = new ProfessorCourseMap();
			profCoMap.setProfessor(professorDao.findById(professorid).get());
			profCoMap.setCourseCatalog(courseDao.findByCourseId(courseId));
			profCoMap.setIsApproved(0);
			professorCoMapRepo.save(profCoMap);
			System.out.println("Done");
		});
		return;
	}

	/**
	 * To view list of registered student in a specific course.
	 * 
	 * @param courseId
	 * @throws CourseNotFoundException
	 * @return List<Student> for particular course with courseId under professor
	 *         with professorId
	 */
	@Override
	public List<Student> viewStudentList(int professorId, String courseId)
			throws CourseNotFoundException, CourseNotAssignedException {

		if (courseDao.findByCourseId(courseId) == null) {
			throw new CourseNotFoundException(courseId);
		}
		ProfessorCourseMap professorCourseMap = professorCoMapRepo.findByProfessorAndCourseCatalog(
				professorDao.findById(professorId).get(), courseDao.findByCourseId(courseId));
		if ((professorCourseMap == null)
				|| ((professorCourseMap != null) && (professorCourseMap.getIsApproved() == 0))) {
			throw new CourseNotAssignedException(courseId);
		}
		List<StudentCourseMap> studentCo = studentCourseRepo.findByCourse(courseDao.findByCourseId(courseId));

		List<Student> students = studentCo.stream().map(studentMap -> new Student(studentMap.getStudent()))
				.collect(Collectors.toList());
		return students;
	}

	/**
	 * To view the list of all the courses
	 * 
	 * @return List<CourseCatalog> containing list of courses
	 */
	@Override
	public List<CourseCatalog> viewCourseCatalog() {
		Iterable<CourseCatalog> courses = courseDao.findAll();
		List<CourseCatalog> list = new ArrayList<>();
		courses.forEach(list::add);
		return list;
	}

	/**
	 * Find professor using Email
	 * 
	 * @param userEmail
	 * @return count with get professor by id
	 */
	@Override
	public int getProfessorById(String userEmail) {
		// TODO Auto-generated method stub
		return professorDao.getProfessorById(userEmail);

	}

	/**
	 * Get the list of Assigned courses to professor
	 * 
	 * @param userId
	 * @return Map<Integer,String> containing CourseId and CourseName
	 * @throws UserNotFoundException
	 */
	@Override
	public Map<Integer, String> listOfApprovedCourses(int userId) throws UserNotFoundException {
		// TODO Auto-generated method stub
		if (professorDao.findById(userId).isEmpty() == true) {
			throw new UserNotFoundException(userId);
		}
		List<Object[]> list = professorDao.listOfApprovedCourses(userId);
		Map<Integer, String> courses = new HashMap<>();
		for (Object[] result : list)
			courses.put((Integer) result[0], (String) result[1]);
		return courses;
	}

/**
 * returns list of professors
 * 	@return list of professors
 */
	@Override
	public List<Professor> viewProfessor() {
		Iterable<Professor> professors = professorDao.findAll();
		List<Professor> list = new ArrayList<>();
		professors.forEach(professor->
			list.add(professor));
		return list;
	}

}
