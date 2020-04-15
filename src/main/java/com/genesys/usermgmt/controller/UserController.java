package com.genesys.usermgmt.controller;

import java.lang.invoke.MethodHandles;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.genesys.usermgmt.repository.entity.Users;
import com.genesys.usermgmt.service.LoginService;
import com.genesys.usermgmt.service.UserService;

@RestController
public class UserController implements IUserController {
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired
	private UserService userService;

	@Autowired
	private LoginService loginService;

	@Override
	public ResponseEntity<List<Users>> getUserList() {
		return new ResponseEntity<List<Users>>(userService.getUserList(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Users> createUser(Users user) throws Exception {
		logger.info("Create User : {}", user.getUserName());

		return new ResponseEntity<Users>(userService.createUser(user), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Users> updateUser(Long userId, Users user) {
		logger.info("Update User : {}", user.getUserName());
		return new ResponseEntity<Users>(userService.updateUser(userId, user), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Users> getUser(Long userId) throws Exception {
		logger.info("Fetch User : {}", userId);
		return new ResponseEntity<Users>(userService.getUser(userId), HttpStatus.OK);
	}

	@Override
	public void deleteUser(Long userId) throws Exception {
		logger.info("Delete User : {}", userId);
		userService.deleteUser(userId);
	}

	@Override
	public ResponseEntity<Users> login(String emailId, String password) {
		logger.info("Login User : {}", emailId);
		Users user = new Users();
		user.setEmailId(emailId);
		user.setPassword(password);
		// Login service
		return new ResponseEntity<Users>(loginService.login(user), HttpStatus.OK);

	}

}
