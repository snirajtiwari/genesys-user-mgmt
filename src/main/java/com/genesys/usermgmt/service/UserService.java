package com.genesys.usermgmt.service;

import java.io.Serializable;
import java.util.List;

import com.genesys.usermgmt.repository.entity.Users;

public interface UserService extends Serializable {

	Users createUser(Users user);

	Users updateUser(Long userId, Users user);

	Users getUser(Long userId);

	List<Users> getUserList();

	void deleteUser(Long userId);

}
