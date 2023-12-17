/**
 * 
 */
package com.crs.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

import com.crs.entity.CourseCatalog;
import com.crs.entity.Notification;
import com.crs.constant.NotificationConstants;
import com.crs.dto.AddCourseDto;
import com.crs.dto.DropCourseDTO;
import com.crs.dto.GradeCardResponseDTO;
import com.crs.exception.CourseLimitExceededException;
import com.crs.exception.CourseNotFoundException;
import com.crs.exception.StudentAlreadyRegisteredException;
import com.crs.exception.UserNotApprovedException;
import com.crs.exception.UserNotFoundException;
import com.crs.service.NotificationOperation;
import com.crs.service.PaymentOperation;
import com.crs.service.StudentOperation;

/**
 * 
 */
@RestController
@RequestMapping(value="/api/student")
@CrossOrigin
public class CRSStudentController {

	@Autowired
	public StudentOperation studentOp;
	
	@Autowired
	private PaymentOperation payment;
	
	@Autowired
	private NotificationOperation notificationOp;
	/**
	 * To add course preferences for the registration.
	 * 
	 * @param userId
	 * @param addCourseDto
	 * @return A message if the courses are added successfully or not.
	 */
	@RequestMapping(value = "/{id}/addCourse", method = RequestMethod.POST)
	public ResponseEntity<String> addCourse(@PathVariable(value = "id") int userId,
			@RequestBody AddCourseDto addCourseDto) {
		int isRegistered = studentOp.isStudentRegistered(userId);
		if (isRegistered >= 1)
			return new ResponseEntity<String>("\"Your Registration is completed. You can't add courses\";",
					HttpStatus.CONFLICT);
		try {
			studentOp.addCourses(userId, addCourseDto);
			return new ResponseEntity<String>("Course Added Successfully", HttpStatus.OK);

		} catch (CourseNotFoundException e) {
			return new ResponseEntity<String>("No Course added", HttpStatus.NOT_FOUND);

		} catch (UserNotFoundException e) {
			return new ResponseEntity<String>("User Not Found", HttpStatus.NOT_FOUND);

		} catch (CourseLimitExceededException e) {
			return new ResponseEntity<String>("Course Limit Exceeded", HttpStatus.CONFLICT);
		}

	}

	/**
	 * To drop an already added course
	 * 
	 * @param dropCourseDto
	 * @return A message if the courses are added successfully or not.
	 */
	@RequestMapping(value = "/{id}/dropCourse", method = RequestMethod.POST)
	public ResponseEntity<String> dropCourse(@RequestBody DropCourseDTO dropCourseDto) {
		int isRegistered = studentOp.isStudentRegistered(dropCourseDto.getStudentId());
		if (isRegistered >= 1) {
			return new ResponseEntity<String>("\"Your Registration is completed. You can't drop courses\";",
					HttpStatus.CONFLICT);
		}
		try {
			int coursePref = studentOp.dropCourses(dropCourseDto.getStudentId(), dropCourseDto.getCourseId());

			if (coursePref == 0) {
				return new ResponseEntity<String>("You have to add Complusory course", HttpStatus.CONFLICT);

			}
			if (coursePref == 1) {
				return new ResponseEntity<String>("You have to add Alternative course", HttpStatus.CONFLICT);
			}
		} catch (UserNotFoundException e) {

			return new ResponseEntity<String>("User with id " + e.getUserId() + " is not found", HttpStatus.NOT_FOUND);

		} catch (CourseNotFoundException e) {
			return new ResponseEntity<String>("Course with id " + e.getCourseId() + " is not found",
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("Drop is Successful!! ", HttpStatus.OK);
	}

	/**
	 * To apply for the course registration after the addition of courses has been
	 * completed.
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/{id}/registerCourse", method = RequestMethod.POST)
	public ResponseEntity<?> registerCourses(@PathVariable(value = "id") int userId) {
		try {
			studentOp.registerCourses(userId);
			return ResponseEntity.ok("Applied For Course Registration Successfully. Now go to billing Section");
		} catch (UserNotFoundException e) {

			return new ResponseEntity<String>("User with id " + e.getUserId() + " is not found", HttpStatus.NOT_FOUND);

		}
	}

	/**
	 * To return list of registered courses
	 * 
	 * @param userId
	 * @return a map of registered courses and their course names.
	 */
	@RequestMapping(value = "/{id}/listCourse", method = RequestMethod.POST)
	public ResponseEntity<Map<Integer, String>> listCourse(@PathVariable(value = "id") int userId) {
		try {
			Map<Integer, String> courses = studentOp.listCourse(userId);
			if (courses.size() == 0) {

				return new ResponseEntity("Course Registration pending", HttpStatus.TOO_EARLY);

			}
			return ResponseEntity.ok(courses);

		} catch (UserNotApprovedException e) {
			return new ResponseEntity("User with id " + e.getUserId() + " is not approved by admin",
					HttpStatus.NOT_FOUND);
		}

	}

	/**
	 * To view the grade card
	 * 
	 * @param userId
	 * @return a list of grades.
	 */
	@RequestMapping(value = "/{id}/viewReportCard", method = RequestMethod.GET)
	public ResponseEntity<GradeCardResponseDTO> viewReportCard(@PathVariable(value = "id") int userId) {
		try {
			return ResponseEntity.ok(studentOp.viewReportCard(userId));
		} catch (UserNotApprovedException e) {
			return new ResponseEntity("Courses of student with id " + e.getUserId() + " is not approved by admin",
					HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/viewCourseCatalog", method = RequestMethod.GET)
	public ResponseEntity<List<CourseCatalog>> viewCourseCatalog() {
		return ResponseEntity.ok(studentOp.viewCourseCatalog());
	}

	/**
	 * To view the notifications
	 * 
	 * @param studentId
	 * @return
	 */
	@RequestMapping(value = "/{id}/viewNotifications", method = RequestMethod.GET)
	public ResponseEntity viewNotifications(@PathVariable(name = "id") int studentId) {
		try {
			List<Notification> notifications = notificationOp.getNotificationMessage(studentId);
			if (notifications != null) {
				return ResponseEntity.ok(notifications);

			} else {
				return new ResponseEntity("No Notifications", HttpStatus.NOT_FOUND);
			}
		}

		catch (UserNotFoundException e) {
			return new ResponseEntity<String>("User with id " + e.getUserId() + " is not found", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * payFee
	 * 
	 * @param userId
	 * @param paymentMethod
	 * @param onlineMethod
	 * @return message if payment is successful or not
	 */
	@RequestMapping(value = "/{id}/payfee/{paymentMethod}", method = RequestMethod.POST)
	public ResponseEntity<String> payFee(@PathVariable(value = "id") int userId,
			@PathVariable(value = "paymentMethod") String paymentMethod,
			@RequestParam(required = false) String onlineMethod) 
	{
		boolean status = false;
		String transactionId = null;
try {
		if (studentOp.isRegistered(userId)) {

			if (paymentMethod.equals("offline")) {
				status = payment.offline(userId);
				transactionId=payment.recordPayment(userId,paymentMethod, status);
			}
			else if (paymentMethod.equals("online")) {
				if (onlineMethod.equals("UPI")) {
					status = payment.UPI(userId);
					transactionId=payment.recordPayment(userId,paymentMethod, status);
				} else if (onlineMethod.equals("cards")) {
					status = payment.cards(userId);
					transactionId=payment.recordPayment(userId,paymentMethod, status);
				} else if (onlineMethod.equals("Wallet")) {
					status = payment.wallet(userId);
					transactionId=payment.recordPayment(userId,paymentMethod, status);
				}

				transactionId=payment.recordPayment(userId,paymentMethod, status);
			}
			
			if (status == true) {
//				notificationOp.sendNotification(NotificationConstants.PAYMENT_SUCCESS_NOTIFICATION, userId);
				return new ResponseEntity<String>("Payment Successful with TransactionId:"+transactionId, HttpStatus.OK);
			}

			else {
//				notificationOp.sendNotification(NotificationConstants.PAYMENT_REJECTED_NOTIFICATION, userId);
				return new ResponseEntity<String>("Payment Failed", HttpStatus.NOT_ACCEPTABLE);
			}
		} else {
			return new ResponseEntity<String>("Student courses are not approved by admin", HttpStatus.NOT_ACCEPTABLE);
		}

	}catch(StudentAlreadyRegisteredException e)
	{
			return new ResponseEntity<String>("Student as already registered", HttpStatus.NOT_ACCEPTABLE);
	}
	}

}
