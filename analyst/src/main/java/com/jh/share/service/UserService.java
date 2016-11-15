package com.jh.share.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Transactional;

import com.jh.share.exception.EmailExistsException;

 //https://hellokoding.com/registration-and-login-example-with-spring-security-spring-boot-spring-data-jpa-hsql-jsp/
public interface UserService{

	void save(com.jh.share.model.User user);

	com.jh.share.model.User findByUsername(String username);

		
	}
