/**
 * 
 */
package com.crs.validator;

import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * Interface to validate a user
 */
@Component
public interface ValidatorInterface {

	/**
	 * To validate a user using email
	 * 
	 * @param email
	 * @return
	 */
	public boolean emailValidator(String email);

	/**
	 * Sort the list in order of coure preference
	 * 
	 * @param List<List<Integer>> contains courses with courseId and
	 *                            coursePreference
	 * @return sorted List<List<Integer>>
	 */

	/**
	 * Algorithm for assigning courses to students
	 * 
	 * @return List<Boolean> which contains true if student's course registration is
	 *         success otherwise false
	 */
	public Map<Integer,Boolean> courseRegistrationValidator();
}
