/**

 * 
 */
package com.crs.constant;

import org.springframework.stereotype.Component;

/**
 * Constants to store various SQL Queries.
 */
@Component
public class SQLConstants {

	/*
	 * Authentication Queries
	 */
	public static final String VERIFY_STUDENT = " SELECT * FROM student WHERE userEmail=?1";
	public static final String UPDATE_PASSWORD_STUDENT = "UPDATE student SET userPassword=?1 WHERE userEmail=?2";

	public static final String VERIFY_PROFESSOR = " SELECT * FROM professor WHERE userEmail=?1";
	public static final String UPDATE_PASSWORD_PROFESSOR = "UPDATE professor SET userPassword=?1 WHERE userEmail=?2";

	public static final String VERIFY_ADMIN = " SELECT * FROM admin WHERE userEmail=?1";
	public static final String UPDATE_PASSWORD_ADMIN = "UPDATE admin SET userPassword=?1 WHERE userEmail=?2";

	/*
	 * Student Queries
	 */
	public static final String SELECT_REGISTER = "SELECT isRegister FROM studentcoursemapper WHERE userId=?1";
	public static final String ADD_COURSES = "INSERT INTO studentcoursemapper VALUES(?1,?2,?3,0)";
	public static final String COURSE_PREFERENCE = "SELECT coursecategory FROM studentcoursemapper WHERE userId=?1 AND courseId=?2";
	public static final String DELETE_COURSE = "DELETE FROM studentcoursemapper WHERE userId=? && courseId=?";

	public static final String UPDATE_REGISTER = " UPDATE studentcoursemapper SET isRegister=1 WHERE userId=?1";
	public static final String COUNT_COURSES = "SELECT COUNT(courseid) as courseCount FROM studentcoursemapper WHERE userId=?1";
	public static final String IS_APPROVED = "SELECT COUNT(*) FROM gradecard where userId=?1";
	public static final String SELECT_userId = "SELECT DISTINCT(userId) as uniqueStudent FROM studentcoursemapper";
	public static final String COUNT_STUDENT_COURSES = "SELECT COUNT(courseId) as courseCount FROM studentcoursemapper WHERE courseId=?";

	public static final String SELECT_COURSEID = "SELECT courseid FROM studentcoursemapper WHERE userId=?";
	public static final String COURSE_CATALOG = "SELECT * FROM coursecatalog";
	public static final String INSERT_STUDENT = "INSERT INTO student (studentname,studentemail,password,phonenumber) VALUES(?,?,?,?)";
	public static final String GRADE_CARD = "SELECT courseId, grade FROM gradecard where userId=?1";

	public static final String SELECT_STUDENT_BY_EMAIL = "SELECT userId FROM student WHERE studentemail=?";
	public static final String SEARCH_STUDENT_BY_ID = "SELECT * FROM student WHERE userId=?";
	public static final String STUDENT_BY_EMAIL = "SELECT COUNT(*) FROM student WHERE userEmail=?1";
	public static final String SELECT_ADDED_COURSE = "SELECT c.courseId,c.courseName FROM coursecatalog c INNER JOIN crs.studentcoursemapper scm ON c.courseId=scm.courseId WHERE scm.userId=?1";

	/*
	 * Professor Queries
	 */
	public static final String SELECT_PROFESSOR_BY_EMAIL = "SELECT userId FROM professor WHERE userEmail=?";

	public static final String SET_GRADES = "UPDATE gradecard SET grade=?1 WHERE userId=?2 && courseId=?3";
	public static final String REQUEST_COURSE = "INSERT INTO professorcoursemapper VALUES(?1,?2,0)";
	public static final String STUDENT_LIST = "SELECT userId,userName, userEmail, userPhonenumber FROM student"
			+ " WHERE userId IN (SELECT userId FROM studentcoursemapper WHERE courseId=?1)";

	// JOIN CUSTOM QUERY
	// static final String LIST_APPROVED_COURSES = "SELECT pcm.courseId,
	// cc.courseName FROM professorcoursemapper pcm INNER JOIN coursecatalog cc ON
	// pcm.courseId=cc.courseId WHERE pcm.userId=?1 && pcm.isApproved=1";
	public static final String SEARCH_PROFESSOR = "SELECT COUNT(*) FROM professor WHERE userEmail=?1";

	public static final String RECORD_PAYMENT = "INSERT INTO payment VALUES(?,?,?,?)";

	/*
	 * Admin Queries
	 */
	public static final String APPROVE_STUDENT = "UPDATE student SET isApproved=1";
	public static final String APPROVE_STUDENT_BY_ID = "UPDATE student SET isApproved=1 WHERE userId=?";
	public static final String SELECT_PROFESSORS_BY_ID = "SELECT DISTINCT userId FROM professorcoursemapper";
	public static final String SELECT_PROFESSOR_COURSES = "SELECT courseId FROM professorcoursemapper WHERE userId=?1";
	public static final String APPROVE_PROFESSOR_COURSE = "UPDATE professorcoursemapper SET isApproved=1 WHERE userId=?1 && courseId=?2";
	public static final String SEARCH_ADMIN = "SELECT userId FROM admin WHERE userEmail=?";
	public static final String INSERT_GRADECARD = "INSERT INTO gradecard VALUES(?,?,NA)";
	public static final String SELECT_COURSEMAPPING = "SELECT courseId, coursecategory FROM studentcoursemapper WHERE userId=?";

	public static final String DROP_COURSE = "DELETE FROM coursecatalog WHERE courseid=?";
	public static final String PROFESSOR_COURSE_DATA = "SELECT userId FROM professorcoursemapper WHERE courseid=?";
	public static final String LIST_COURSES = "SELECT courseId from coursecatalog";
	public static final String PROF_COURSE_ASSIGNMENT = "UPDATE professorcoursemapper SET isassigned=1 WHERE userId=? && courseId=?";
	public static final String PENDING_REGISTRATION = "SELECT userId FROM student where isapproved=0";

	public static final String SEND_NOTIFICATION = "INSERT INTO notificationstudentmapping VALUES(?1,?2)";
	public static final String SELECT_STUDENT_APPROVED = "SELECT userId FROM gradecard WHERE userId=?";
	public static final String SELECT_ADMIN_BY_EMAIL = "SELECT userId FROM admin WHERE adminEmail=?";
	public static final String STUDENT_REGISTRATION_REJECTION = "DELETE FROM studentcoursemapper WHERE userId=?";

	
	/**
	 * Common for student And Professor
	 */
	public static final String GET_NOTIFICATION = "SELECT * FROM notification"
			+ " WHERE notificationId IN (SELECT notifId FROM notificationstudentmapping WHERE userId=?1)";

	public static final String SEARCH_COURSE = "SELECT * FROM coursecatalog WHERE courseId=?";

	/**
	 * // Join Queries //
	 */
	
	// For listing registered courses for a student
	public static final String LIST_STUDENT_REG_COURSES = "SELECT gradecard.courseId, courseCatalog.courseName FROM gradecard as gradecard INNER JOIN coursecatalog as courseCatalog ON gradecard.courseId=courseCatalog.courseId WHERE gradecard.userId=?1";

	// For listing approved courses of professor
	public static final String LIST_APPROVED_COURSES = "SELECT pcm.courseid, cc.courseName FROM professorcoursemapper pcm INNER JOIN coursecatalog cc ON pcm.courseid=cc.courseId WHERE pcm.userId=?1 AND pcm.isApproved=1";

}