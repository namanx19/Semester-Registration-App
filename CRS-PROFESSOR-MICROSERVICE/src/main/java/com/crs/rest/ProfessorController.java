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
import org.springframework.web.bind.annotation.RestController;

import com.crs.dto.GradeCardDto;
import com.crs.entity.CourseCatalog;
import com.crs.entity.Professor;
import com.crs.exception.CourseNotAssignedException;
import com.crs.exception.CourseNotFoundException;
import com.crs.exception.UserNotFoundException;
import com.crs.service.ProfessorOperation;

/**
 * 
 */
@RestController
@RequestMapping(value="/api/professor")
@CrossOrigin
public class ProfessorController {
	 @Autowired
		private ProfessorOperation professorOp;
		
		/**
		 * To request the courses.  
		 * @param userId
		 * @param courseIdList
		 * @return message if the courses requested by professor  are successfully sent to admin for Approval or not
		 */
		
		@RequestMapping(value="/{userId}/requestcourse",method = RequestMethod.POST)
		public ResponseEntity<String> freezeList(@PathVariable(value="userId") int userId,@RequestBody List<String> courseIdList) {
			 try {
	         	
					professorOp.requestCourseOffering(userId,courseIdList);
					return new ResponseEntity<String>("Courses request sent for approval to Admin",HttpStatus.OK);
				}
			 catch (UserNotFoundException e) {
					return new ResponseEntity<String>("User with UserId:"+userId+" Not Found",HttpStatus.NOT_FOUND);
				}
			 
			 catch (CourseNotFoundException e) {
					return new ResponseEntity<String>("Course with course id:"+e.getCourseId()+" Not Found",HttpStatus.NOT_FOUND);
				}
		}

		/**
		 * To view the list of approved courses
		 * @param userId
		 * @return list of approved courses
		 */
		@RequestMapping(value="/{userId}/approvedcourses",method = RequestMethod.GET)
		public ResponseEntity<Map<Integer,String>> approvedCourses(@PathVariable(value="userId") int userId)
		{try {
			 return new ResponseEntity<Map<Integer, String>>(professorOp.listOfApprovedCourses(userId),HttpStatus.OK);
		}
			 catch (UserNotFoundException e) {
					return new ResponseEntity("User with UserId:"+userId+" Not Found",HttpStatus.NOT_FOUND);
				}
		}	
		
		
		
		
		
		/**
		 * To view list of students registered for particular course.
		 * @param userId
		 * @param courseId
		 * @return list of students registered for particular courseId.
		 */
		@RequestMapping(value="/{userId}/{courseId}/studentlist",method = RequestMethod.POST)
	    public ResponseEntity studentList(@PathVariable(value="userId") int professorId,@PathVariable(value="courseId") String courseId) {

	        try {
	     	    return new ResponseEntity(professorOp.viewStudentList(professorId,courseId),HttpStatus.OK);
			} catch (CourseNotFoundException e) {
				return new ResponseEntity("Course with course id:"+e.getCourseId()+" Not Found",HttpStatus.NOT_FOUND);

			}catch(CourseNotAssignedException e)
	    	{
				return new ResponseEntity<String>("Course with id:"+e.getCourseId()+" Not Assigned",HttpStatus.NOT_FOUND);
			}
		}
		
		/**
		 * To view course catalog
		 * @return list of courses in course catalog
		 */
		@RequestMapping(value="/coursecatalog",method = RequestMethod.GET)
		public ResponseEntity<List<CourseCatalog>> courseCatalog() {
			
			return ResponseEntity.ok(professorOp.viewCourseCatalog());
			
		}
		
		/**
		 * To set the grades of students.
		 * @param gradecard
		 * @return message if professor has set the grades successfully or not .
		 */
		
		@RequestMapping(value="/{id}/setgrades",method = RequestMethod.POST)
		public ResponseEntity<String> setGrades(@PathVariable(name = "id") int professorID,@RequestBody List<GradeCardDto> gradecard ) {
		try {
			for(GradeCardDto gradeCard: gradecard) {
				professorOp.setGrades(professorID,gradeCard.getStudentId(),gradeCard.getCourseId(),gradeCard.getGrades());
			}
			return new ResponseEntity<String>("Added grades",HttpStatus.OK);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<String>("Student with id:"+e.getUserId()+" Not Found",HttpStatus.NOT_FOUND);
			
		}catch(CourseNotFoundException e)
		{
			return new ResponseEntity<String>("Course with id:"+e.getCourseId()+" Not Found",HttpStatus.NOT_FOUND);
		}
		catch(CourseNotAssignedException e)
		{
			return new ResponseEntity<String>("Course with id:"+e.getCourseId()+" Not Assigned",HttpStatus.NOT_FOUND);
		}
			
		}
		
		@RequestMapping(value="/viewProfessor",method = RequestMethod.GET)
		public ResponseEntity<List<Professor>> viewProfessor(){
			return new ResponseEntity<List<Professor>>(professorOp.viewProfessor(),HttpStatus.OK);
		}

}
