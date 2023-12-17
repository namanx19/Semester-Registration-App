/**
 * 
 */
package com.crs.constant;

/**
 * 
 */
public class SQLConstants {
	public static final String SET_GRADES = "UPDATE gradecard"
			+ " SET grade=?1 WHERE userId=?2 AND courseId=?3";
	
	public static final String FIND_STUDENT_BY_COURSE_ID="SELECT userId,userName, userEmail, userPhonenumber FROM student"
			+ " WHERE userId IN (SELECT userId FROM studentcoursemapping WHERE courseId=?1)";
	
	public static final String SEARCH_PROFESSOR = "SELECT COUNT(*) FROM professor WHERE userEmail=?1";

	public static final String SELECT_PROFESSOR_BY_EMAIL = "SELECT userId FROM professor WHERE userEmail=?";

	public static final String LIST_OF_APPROVED_COURSES = "SELECT pcm.courseid, cc.courseName FROM professorcoursemapping pcm INNER JOIN coursecatalog cc ON pcm.courseid=cc.courseId WHERE pcm.userId=?1 AND pcm.isApproved=1";
}
