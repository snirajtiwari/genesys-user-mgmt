package com.genesys.usermgmt.service.impl;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.genesys.usermgmt.constant.GenesysConstants;
import com.genesys.usermgmt.constant.GenesysMessages;
import com.genesys.usermgmt.repository.UserRepository;
import com.genesys.usermgmt.repository.entity.Users;
import com.genesys.usermgmt.service.UserService;
import com.genesys.usermgmt.util.SequenceGenerator;
import com.google.common.collect.Lists;

@Component
@Transactional
public class UserServiceImpl implements UserService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Users createUser(Users user) {

		// Check Email Exists
		checkUserNameExistsInDB(user.getUserName());

		// Check User Name Exists
		checkEmailExistsInDB(user.getEmailId());

		// Generate Sequence
		user.setUserId(SequenceGenerator.getPrimaryKey(Users.class));

		if (!Pattern.compile(GenesysConstants.PASSWORD_VALIDATION_REGEX).matcher(user.getPassword()).matches()) {
			throw new ValidationException(GenesysMessages.PASSWORD_CRITERIA_FAILURE);
		}

		// Set system dates
		user.setCreatedDate(new Date());
		user.setModifiedDate(new Date());

		// Encrypt Password
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

		// save and return user
		return userRepository.save(user);
	}

	@Override
	public Users updateUser(Long userId, Users user) {
		Users userinDB = userRepository.findById(userId).orElseThrow(
				() -> new ValidationException(String.format(GenesysMessages.FIELD_DOES_NOT_EXIST_IN_DB, userId)));

		// Check User Exists
		if (!userinDB.getUserName().equalsIgnoreCase(user.getUserName())) {
			checkUserNameExistsInDB(user.getUserName());

		}

		// Check Email Exists
		if (!userinDB.getEmailId().equalsIgnoreCase(user.getEmailId())) {
			checkEmailExistsInDB(user.getEmailId());
		}

		user.setUserId(userinDB.getUserId());
		user.setPassword(userinDB.getPassword());
		user.setModifiedDate(new Date());
		return userRepository.save(user);
	}

	@Override
	public Users getUser(Long userId) {
		return userRepository.findById(userId).orElseThrow(
				() -> new ValidationException(String.format(GenesysMessages.FIELD_DOES_NOT_EXIST_IN_DB, userId)));

	}

	@Override
	public List<Users> getUserList() {
		return Lists.newArrayList(userRepository.findAll());
	}

	@Override
	public void deleteUser(Long userId) {
		userRepository.deleteById(userId);
	}

	private void checkUserNameExistsInDB(String userName) {
		List<Users> userList = userRepository.findByUserName(userName);
		if (!userList.isEmpty()) {
			throw new ValidationException(GenesysMessages.USERNAME_EXISTS);
		}
	}

	private void checkEmailExistsInDB(String emailId) {
		List<Users> userList = userRepository.findByEmailId(emailId);
		if (!userList.isEmpty()) {
			throw new ValidationException(GenesysMessages.EMAIL_ID_EXISTS);
		}
	}

}
