package com.crs.validator;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crs.entity.GradeCard;
import com.crs.repository.AdminRepository;
import com.crs.repository.CourseRepository;
import com.crs.repository.GradeCardRepository;
import com.crs.repository.StudentRepository;

/**
 * To validate admin details
 */
@Component
public class AdminValidatorImpl implements ValidatorInterface {

	@Autowired
	public StudentRepository studentRepository;
	@Autowired
	public AdminRepository adminRepository;
//	@Autowired
//	public ProfessorRepository professorRepository;
//
//	@Autowired
//	public ProfessorCourseMappingRepository professorCourseMappingRepository;


	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private GradeCardRepository gradeRepository;


	@Override
	/**
	 * @param email
	 * @return true if Email is in correct format otherwise false
	 */
	public boolean emailValidator(String email) {
		// TODO Auto-generated method stub
		String regex = "^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	/**
	 * Sort the list in order of coure preference
	 * 
	 * @param List<List<Integer>> contains courses with courseId and
	 *                            coursePreference
	 * @return sorted List<List<Integer>>
	 */


	/**
	 * Algorithm for assigning courses to students
	 * @param studentId 
	 * 
	 * @return Map<Integer,Boolean> which contains studentId and their respective
	 *         status of registration
	 */
	public Map<Integer, Boolean> courseRegistrationValidator() {

		List<Integer> studentIds = studentRepository.getStudentIds();

		Map<String, Integer> courseCount = new HashMap<>();
		Map<Integer, Boolean> isSuccess = new HashMap<>();

		for(int studentId:studentIds)
		{
			int isRegistered = studentRepository.isStudentRegistered(studentId);
			int isApproved = studentRepository.isCourseRegistrationApproved(studentId);
			int feePaymentStatus=studentRepository.findById(studentId).get().getCourseRegistrationStatus();
			if (isApproved > 0) {
				continue;
			}
			if(feePaymentStatus==0)
			{
				continue;
			}
			
			if (isRegistered == 0) {
				System.out.println("Student has not Registered till now");
				continue;
			} else
				System.out.println("Student has Registered Successfully");

			List<Object[]> data = studentRepository.getStudentCourseData(studentId);
			Map<String,Integer> studentData = new HashMap<>();
			for (Object[] result : data) 
				studentData.put((String)result[0], (Integer)result[1]);
			studentData = studentData.entrySet()
					  .stream()
					  .sorted(Map.Entry.comparingByValue())
					  .collect(Collectors.toMap(
					    Map.Entry::getKey, 
					    Map.Entry::getValue, 
					    (oldValue, newValue) -> oldValue, LinkedHashMap::new));
			
			int count = 0;

			for (Entry<String,Integer>course : studentData.entrySet()) {
				
				int studentPerCourseCount = studentRepository.getStudentCourseCount(course.getKey());
				if (count == 4)
					break;
				if (studentPerCourseCount >= 3 && studentPerCourseCount <= 10) {
					count++;
					courseCount.getOrDefault(course.getKey(), courseCount.getOrDefault(course.getKey(), 0) + 1);
					GradeCard gradeCard = new GradeCard();
					gradeCard.setStudent(studentRepository.findById(studentId).get());
					gradeCard.setCatalog(courseRepository.findByCourseId(course.getKey()));
					gradeCard.setGrade("NA");
					gradeRepository.save(gradeCard);
				} else if (studentPerCourseCount < 3) {
					continue;
				} else if (studentPerCourseCount > 10) {
					if (courseCount.getOrDefault(course.getKey(), 0) < 10) {
						count++;
						courseCount.getOrDefault(course.getKey(), courseCount.getOrDefault(course.getKey(), 0) + 1);
						courseCount.getOrDefault(course.getKey(), courseCount.getOrDefault(course.getKey(), 0) + 1);
						GradeCard gradeCard = new GradeCard();
						gradeCard.setStudent(studentRepository.findById(studentId).get());
						gradeCard.setCatalog(courseRepository.findByCourseId(course.getKey()));
						gradeCard.setGrade("NA");
						gradeRepository.save(gradeCard);
					} else
						continue;
				}
			}
			if (count == 4) {

//				notificationStudentMappingRepository.save(new NotificationStudentMapping(
//						studentRepository.findById(studentId).get(), notificationRepository
//								.findById(NotificationConstants.APPROVE_REGISTRATION_NOTIFICATION).get()));
//
//				notificationStudentMappingRepository
//						.save(new NotificationStudentMapping(studentRepository.findById(studentId).get(),
//								notificationRepository.findById(NotificationConstants.FEE_PAYMENT_NOTIFICATION).get()));

				isSuccess.put(studentId, true);
			} else {

//				notificationStudentMappingRepository.save(new NotificationStudentMapping(
//						studentRepository.findById(studentId).get(),
//						notificationRepository.findById(NotificationConstants.REJECT_REGISTRATION_NOTIFICATION).get()));
				isSuccess.put(studentId, false);
			}

		}
		return isSuccess;
	}

}
