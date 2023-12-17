/**
 * 
 */
package com.crs.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.crs.dto.RegisterUserDto;
import com.crs.exception.StudentAlreadyRegisteredException;
import com.crs.exception.UserAlreadyExistsException;

/**
 * Authenticating Users Interface
 */
@Service
@Transactional
public interface AuthenticationOperation {

	/*
	 * Check whether User with details can log into system or not
	 * 
	 * @param User Email
	 * 
	 * @param User Password
	 * 
	 * @param User Role
	 * 
	 * @return true if user details matched otherwise return false
	 */
	public boolean loggedin(String userEmail, String userPassword, String role, StringBuilder msg);

	/*
	 * Update User Password
	 * 
	 * @param User Email
	 * 
	 * @param User Password
	 * 
	 * @param User Role
	 */
	public void updatePassword(String userEmail, String userPassword, String role);
	
	
	/**
	 * To register a new Student user.
	 * @param user
	 * @throws StudentAlreadyRegisteredException
	 */
	@Transactional
	public void registerStudent(RegisterUserDto user) throws StudentAlreadyRegisteredException;
	
	/**
	 * For signing up a new professor
	 * 
	 * @param user
	 * @throws UserAlreadyExistsException
	 */ 
	public void registerProfessor(RegisterUserDto user) throws UserAlreadyExistsException;


	/**
	 * To approve registration of a new Admin
	 * 
	 * @param User contains admin details
	 */
	public void adminRegistration(RegisterUserDto user) throws UserAlreadyExistsException;

}
