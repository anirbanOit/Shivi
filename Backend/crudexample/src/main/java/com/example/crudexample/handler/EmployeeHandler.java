package com.example.crudexample.handler;

import java.util.List;

import com.example.crudexample.dto.EmployeeDTO;
import com.example.crudexample.dto.StatusDTO;
import com.example.crudexample.feature.employee.api.message.request.AddUserRequest;

public interface EmployeeHandler {
	
	/**
	 * 
	 * @param errorCodePrefix
	 * @return EmployeeDTO
	 */
	List<EmployeeDTO> performGetUsersProfileDetail(String errorCodePrefix);
	
	/**
	 * 
	 * @param addUserRequest
	 * @param errorCodePrefix
	 * @return StatusDTO
	 */
	StatusDTO performAddUser(AddUserRequest addUserRequest, String errorCodePrefix);
	
	/**
	 * 
	 * @param id
	 * @param errorCodePrefix
	 * @return EmployeeDTO
	 */
	EmployeeDTO performGetUserById(String id, String errorCodePrefix);
	
	/**
	 * 
	 * @param id
	 * @param errorCodePrefix
	 * @return StatusDTO
	 */
	StatusDTO performDeleteUserById(String id, String errorCodePrefix);
}
