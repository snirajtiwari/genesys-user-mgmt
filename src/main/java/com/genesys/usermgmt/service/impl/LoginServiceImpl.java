package com.genesys.usermgmt.service.impl;

import java.util.Date;
import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.genesys.usermgmt.constant.GenesysMessages;
import com.genesys.usermgmt.repository.UserRepository;
import com.genesys.usermgmt.repository.entity.Users;
import com.genesys.usermgmt.service.LoginService;

@Component
@Transactional
public class LoginServiceImpl implements LoginService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Users login(Users user) {
		List<Users> usersinDB = userRepository.findByEmailId(user.getEmailId());
		if (usersinDB.isEmpty()) {
			throw new ValidationException(
					String.format(GenesysMessages.FIELD_DOES_NOT_EXIST_IN_DB, user.getUserName()));
		}
		
		Users userinDB = usersinDB.get(0);
		
		Boolean match = new BCryptPasswordEncoder().matches(user.getPassword(), userinDB.getPassword());
		if (match) {
			userinDB.setLastLoginDate(new Date());
			return userRepository.save(userinDB);
		} else {
			throw new ValidationException(GenesysMessages.INCORRECT_PASSWORD);
		}
	}

}
