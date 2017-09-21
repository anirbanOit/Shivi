package com.example.crudexample.feature.employee.bean;

import com.example.crudexample.api.message.response.AbstractResponse;
import com.example.crudexample.feature.employee.api.message.request.AddUserRequest;

public interface EmployeeBean {
	
	/**
	 * 
	 * @param accessorId
	 * @param sessionToken
	 * @return AbstractResponse - List of all Users
	 */
	AbstractResponse processGetAllUserProfile(String accessorId, String sessionToken);
	
	/**
	 * 
	 * @param addUserRequest
	 * @param sessionToken
	 * @return AbstractResponse - AddUser Success/Response
	 */
	AbstractResponse processAddUser(AddUserRequest addUserRequest, String sessionToken);
	
	/**
	 * 
	 * @param accessorIdStr
	 * @param id
	 * @param sessionToken
	 * @return AbstractResponse - Return Single User
	 */
	AbstractResponse processGetUserById(String accessorIdStr, String id, String sessionToken);
	/**
	 * 
	 * @param accessorIdStr
	 * @param id
	 * @param sessionToken
	 * @return AbstractResponse - Delete Single User
	 */
	AbstractResponse processDeleteUserById(String accessorIdStr, String id, String sessionToken);
}
