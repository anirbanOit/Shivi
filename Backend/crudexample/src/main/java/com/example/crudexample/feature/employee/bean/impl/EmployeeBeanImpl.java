package com.example.crudexample.feature.employee.bean.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crudexample.api.message.response.AbstractResponse;
import com.example.crudexample.dto.EmployeeDTO;
import com.example.crudexample.dto.StatusDTO;
import com.example.crudexample.feature.employee.api.message.request.AddUserRequest;
import com.example.crudexample.feature.employee.api.message.response.AddUserResponse;
import com.example.crudexample.feature.employee.api.message.response.AllUsersDetailResponse;
import com.example.crudexample.feature.employee.api.message.response.DeleteUserByIdResponse;
import com.example.crudexample.feature.employee.api.message.response.GetUserByIdResponse;
import com.example.crudexample.feature.employee.bean.EmployeeBean;
import com.example.crudexample.handler.EmployeeHandler;
import com.example.crudexample.handler.RequestValidator;
import com.example.crudexample.utils.APIGroupCode;
import com.example.crudexample.utils.AppUtil;
import com.example.crudexample.utils.log.AppLog;

@Service("userBean")
public class EmployeeBeanImpl implements EmployeeBean {

	@Autowired(required = true)
	private EmployeeHandler employeeHandler;

	@Autowired(required = true)
	private RequestValidator requestValidator;

	private final AppLog appLog;

	@Inject
	public EmployeeBeanImpl() {
		appLog = AppLog.getInstance(getClass());
	}

	@Override
	public AbstractResponse processGetAllUserProfile(final String accessorIdStr, final String sessionToken) {
		final String errorCodePrefix = APIGroupCode.EMPLOYEE_GET_ALL_USERS.getGroupCode()
				+ APIGroupCode.EMPLOYEE_GET_ALL_USERS.getApiCode() + "000";

		String beanServiceName = "processGetAllUserProfile";

		appLog.printLog(Level.DEBUG, beanServiceName, " Accessor ID:" + accessorIdStr);

		// Validating Permissions & Session Token
		requestValidator.validateAuthToken(sessionToken, beanServiceName, errorCodePrefix);
		requestValidator.validateEMRId(accessorIdStr, errorCodePrefix);

		List<EmployeeDTO> employeeDTOs = employeeHandler.performGetUsersProfileDetail(errorCodePrefix);

		AllUsersDetailResponse getAllUsersDetailResponse = new AllUsersDetailResponse();
		getAllUsersDetailResponse.setEmployeeDTO(employeeDTOs);
		appLog.printLog(Level.DEBUG, beanServiceName, AppUtil.convertObjectToJson(getAllUsersDetailResponse));
		
		return getAllUsersDetailResponse;
	}

	@Override
	public AbstractResponse processAddUser(final AddUserRequest addUserRequest, final String sessionToken) {
		final String errorCodePrefix = APIGroupCode.EMPLOYEE_ADD_USER.getGroupCode()
				+ APIGroupCode.EMPLOYEE_ADD_USER.getApiCode() + "000";
		
		String beanServiceName = "processAddUser";
		
		// Validating Permissions & Session Token
		requestValidator.validateAuthToken(sessionToken, beanServiceName, errorCodePrefix);
		
		StatusDTO statusDto = employeeHandler.performAddUser(addUserRequest, errorCodePrefix);
		
		AddUserResponse addUserResponse = new AddUserResponse();
		addUserResponse.setStatusDTO(statusDto);
		appLog.printLog(Level.DEBUG, beanServiceName, AppUtil.convertObjectToJson(addUserResponse));
		
		return addUserResponse;
	}
	
	@Override
	public GetUserByIdResponse processGetUserById(final String accessorIdStr, final String id, final String sessionToken) {
		final String errorCodePrefix = APIGroupCode.EMPLOYEE_GET_SINGLE_USER.getGroupCode()
				+ APIGroupCode.EMPLOYEE_GET_SINGLE_USER.getApiCode() + "000";

		String beanServiceName = "processGetUserById";

		appLog.printLog(Level.DEBUG, beanServiceName, " Accessor ID:" + accessorIdStr + "ID: " + id);

		// Validating Permissions & Session Token
		requestValidator.validateAuthToken(sessionToken, beanServiceName, errorCodePrefix);
		requestValidator.validateEMRId(accessorIdStr, errorCodePrefix);
		requestValidator.validateEMRId(id, errorCodePrefix);

		EmployeeDTO employeeDTO = employeeHandler.performGetUserById(id, errorCodePrefix);

		GetUserByIdResponse getUserByIdResponse = new GetUserByIdResponse();
		getUserByIdResponse.setEmployeeDTO(employeeDTO);
		appLog.printLog(Level.DEBUG, beanServiceName, AppUtil.convertObjectToJson(getUserByIdResponse));
		
		return getUserByIdResponse;
	}

	@Override
	public AbstractResponse processDeleteUserById(final String accessorIdStr,final String id, final String sessionToken) {
		final String errorCodePrefix = APIGroupCode.EMPLOYEE_GET_SINGLE_USER.getGroupCode()
				+ APIGroupCode.EMPLOYEE_GET_SINGLE_USER.getApiCode() + "000";
		
		String beanServiceName = "processDeleteUserById";
		appLog.printLog(Level.DEBUG, beanServiceName, " Accessor ID:" + accessorIdStr + "ID: " + id);

		// Validating Permissions & Session Token
		requestValidator.validateAuthToken(sessionToken, beanServiceName, errorCodePrefix);
		requestValidator.validateEMRId(accessorIdStr, errorCodePrefix);
		requestValidator.validateEMRId(id, errorCodePrefix);

		StatusDTO statusDTO = employeeHandler.performDeleteUserById(id, errorCodePrefix);

		DeleteUserByIdResponse deleteUserByIdResponse = new DeleteUserByIdResponse();
		deleteUserByIdResponse.setStatusDTO(statusDTO);
		appLog.printLog(Level.DEBUG, beanServiceName, AppUtil.convertObjectToJson(deleteUserByIdResponse));
		
		return deleteUserByIdResponse;
	}
}
