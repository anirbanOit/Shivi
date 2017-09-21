package com.example.crudexample.feature.employee.api.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.apache.log4j.Level;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.crudexample.api.message.response.AbstractResponse;
import com.example.crudexample.feature.employee.api.message.request.AddUserRequest;
import com.example.crudexample.feature.employee.bean.EmployeeBean;
import com.example.crudexample.utils.AppUtil;
import com.example.crudexample.utils.constant.AppConstant;
import com.example.crudexample.utils.log.AppLog;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	private final EmployeeBean employeeBean;
	private final AppLog appLog;

	@Inject
	public EmployeeController(final EmployeeBean employeeBean) {
		this.appLog = AppLog.getInstance(EmployeeController.class);
		this.employeeBean = employeeBean;
	}

	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> getUser() {
		return ResponseEntity.status(HttpStatus.OK).body("User Information");
	}

	@CrossOrigin
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<AbstractResponse> getAllUser(@Valid @RequestParam("admin_id") final String accessorIdStr,
			@RequestHeader(AppConstant.REQUEST_HEADER_TOKEN) final String sessionToken) {
		String methodName = "getAllUser";

		appLog.printLog(Level.DEBUG, methodName, "Role: " + accessorIdStr);

		AbstractResponse getAllUserResponse = employeeBean.processGetAllUserProfile(accessorIdStr, sessionToken);

		appLog.printLog(Level.DEBUG, methodName, "Response: " + AppUtil.convertObjectToJson(getAllUserResponse));

		return ResponseEntity.status(HttpStatus.OK).body(getAllUserResponse);
	}

	@CrossOrigin
	@RequestMapping(value = "/addUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<AbstractResponse> addUser(
			@RequestHeader(AppConstant.REQUEST_HEADER_TOKEN) final String sessionToken,
			@Valid @RequestBody final AddUserRequest addUserRequest) {
		String methodName = "addUser";

		AbstractResponse addUserResponse = employeeBean.processAddUser(addUserRequest, sessionToken);

		appLog.printLog(Level.DEBUG, methodName, "Response: " + AppUtil.convertObjectToJson(addUserResponse));

		return ResponseEntity.status(HttpStatus.OK).body(addUserResponse);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/search/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<AbstractResponse> getUserById(@Valid @RequestParam("admin_id") final String accessorIdStr,
			@RequestHeader(AppConstant.REQUEST_HEADER_TOKEN) final String sessionToken, @Valid @PathVariable final String id) {
		String methodName = "getUserById";

		appLog.printLog(Level.DEBUG, methodName, "Accessor ID: " + accessorIdStr);

		AbstractResponse getUserById = employeeBean.processGetUserById(accessorIdStr, id, sessionToken);

		appLog.printLog(Level.DEBUG, methodName, "Response: " + AppUtil.convertObjectToJson(getUserById));

		return ResponseEntity.status(HttpStatus.OK).body(getUserById);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<AbstractResponse> UserById(@Valid @RequestParam("admin_id") final String accessorIdStr,
			@RequestHeader(AppConstant.REQUEST_HEADER_TOKEN) final String sessionToken, @Valid @PathVariable final String id) {
		String methodName = "deleteUserById";

		appLog.printLog(Level.DEBUG, methodName, "Accessor ID: " + accessorIdStr);

		AbstractResponse deleteUserById = employeeBean.processDeleteUserById(accessorIdStr, id, sessionToken);

		appLog.printLog(Level.DEBUG, methodName, "Response: " + AppUtil.convertObjectToJson(deleteUserById));

		return ResponseEntity.status(HttpStatus.OK).body(deleteUserById);
	}
}
