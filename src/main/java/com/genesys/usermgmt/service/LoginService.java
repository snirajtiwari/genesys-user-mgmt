package com.genesys.usermgmt.service;

import java.io.Serializable;

import com.genesys.usermgmt.repository.entity.Users;

public interface LoginService extends Serializable {

	Users login(Users user);

}
