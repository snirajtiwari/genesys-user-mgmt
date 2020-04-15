package com.genesys.usermgmt.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.genesys.usermgmt.constant.GenesysPath;
import com.genesys.usermgmt.constant.SwaggerConstants;
import com.genesys.usermgmt.repository.entity.Users;

import io.swagger.annotations.Api;

@Api(tags = { SwaggerConstants.USER_TAG })
@RequestMapping(value = GenesysPath.SLASH)
public interface IUserController {

	@RequestMapping(value = GenesysPath.USER
			+ GenesysPath.LOGIN, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	ResponseEntity<Users> login(@RequestParam(name = GenesysPath.PH_EMAILID) String emailId,
			@RequestParam(name = GenesysPath.PH_PASSWORD) String password);

	@RequestMapping(value = GenesysPath.USER, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	ResponseEntity<List<Users>> getUserList();

	@RequestMapping(value = GenesysPath.USER, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	ResponseEntity<Users> createUser(@Valid @RequestBody Users user) throws Exception;

	@RequestMapping(value = GenesysPath.USER_EXT, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	ResponseEntity<Users> getUser(@PathVariable(name = GenesysPath.PH_USER_ID) Long userId) throws Exception;

	@RequestMapping(value = GenesysPath.USER_EXT, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	ResponseEntity<Users> updateUser(@PathVariable(name = GenesysPath.PH_USER_ID) Long userId,
			@Valid @RequestBody Users user);

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = GenesysPath.USER_EXT, method = RequestMethod.DELETE)
	void deleteUser(@PathVariable(name = GenesysPath.PH_USER_ID) Long userId) throws Exception;
}
