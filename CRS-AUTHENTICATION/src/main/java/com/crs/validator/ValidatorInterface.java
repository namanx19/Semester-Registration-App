/**
 * 
 */
package com.crs.validator;

import java.util.List;
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


}
