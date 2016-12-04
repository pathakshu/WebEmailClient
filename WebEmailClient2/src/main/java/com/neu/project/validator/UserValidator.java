package com.neu.project.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.project.model.User;

public class UserValidator implements Validator{

	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		User user = (User)target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "validate.userName", "Your UserName Is Incorrect");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "validate.password", "Your Password Is Incorrect");
	}
	
	
}
