package com.crs.validator;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;
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


import com.crs.repository.AdminRepository;

import com.crs.repository.ProfessorRepository;
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
	@Autowired
	public ProfessorRepository professorRepository;



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
}
