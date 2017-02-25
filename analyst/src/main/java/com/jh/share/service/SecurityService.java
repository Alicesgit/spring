package com.jh.share.service;

import org.springframework.beans.factory.annotation.Autowired;

public interface SecurityService {

	String findLoggedInUsername();

	//void autologin(String username, String password);

}
