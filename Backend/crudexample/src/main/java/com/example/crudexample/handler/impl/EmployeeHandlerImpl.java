package com.example.crudexample.handler.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crudexample.api.message.response.GenericSuccessResponse;
import com.example.crudexample.domain.EmployeeDomain;
import com.example.crudexample.domain.entity.Employee;
import com.example.crudexample.dto.EmployeeDTO;
import com.example.crudexample.dto.StatusDTO;
import com.example.crudexample.exception.NotFoundException;
import com.example.crudexample.exception.helper.ErrorCause;
import com.example.crudexample.exception.helper.ExceptionDetail;
import com.example.crudexample.exception.helper.ResponseCode;
import com.example.crudexample.feature.employee.api.message.request.AddUserRequest;
import com.example.crudexample.handler.EmployeeHandler;

@Service("employeeHandler")
public class EmployeeHandlerImpl implements EmployeeHandler{
	
	@Autowired(required = true)
	private EmployeeDomain employeeDomain;
	
	@Override
	public List<EmployeeDTO> performGetUsersProfileDetail(final String errorCodePrefix) {

		List<Employee> employeeList = employeeDomain.getAllUsersProfile();
		List<EmployeeDTO> employeeDtos = new ArrayList<>();
		
		if (employeeList == null || employeeList.isEmpty()) {
			ExceptionDetail exceptionDetail = new ExceptionDetail(0, "", 0, errorCodePrefix, ResponseCode.NOT_FOUND,
					ErrorCause.NO_DATA_FOUND);

			throw new NotFoundException(exceptionDetail);
		}
		
		for (Employee employee : employeeList) {
			EmployeeDTO employeeDTO = new EmployeeDTO();
			employeeDTO.setName(employee.getName());
			employeeDTO.setAddress(employee.getAddress());
			employeeDTO.setZip(employee.getZip());
			employeeDTO.setAge(employee.getAge());
			employeeDtos.add(employeeDTO);
		}
		return employeeDtos;
	}

	@Override
	public StatusDTO performAddUser(final AddUserRequest addUserRequest, final String errorCodePrefix) {
		Employee employee = new Employee();
		employee.setAddress(addUserRequest.getAddress());
		employee.setAge(addUserRequest.getAge());
		employee.setName(addUserRequest.getName());
		employee.setZip(addUserRequest.getZip());
		
		StatusDTO statusDto = employeeDomain.addUser(employee);
		
		return statusDto;
	}

	@Override
	public EmployeeDTO performGetUserById(final String id, final String errorCodePrefix) {
		Employee employee = employeeDomain.getUserById(id);
		EmployeeDTO employeeDto = new EmployeeDTO();
		
		if (employee == null) {
			ExceptionDetail exceptionDetail = new ExceptionDetail(0, "", 0, errorCodePrefix, ResponseCode.NOT_FOUND,
					ErrorCause.NO_DATA_FOUND);

			throw new NotFoundException(exceptionDetail);
		}
		
		employeeDto.setName(employee.getName());
		employeeDto.setAddress(employee.getAddress());
		employeeDto.setZip(employee.getZip());
		employeeDto.setAge(employee.getAge());
		
		return employeeDto;
	}
	
	@Override
	public StatusDTO performDeleteUserById(final String id, final String errorCodePrefix) {
		
		Employee employee = employeeDomain.getUserById(id);
		if (employee == null) {
			ExceptionDetail exceptionDetail = new ExceptionDetail(0, "", 0, errorCodePrefix, ResponseCode.NOT_FOUND,
					ErrorCause.NO_DATA_FOUND);

			throw new NotFoundException(exceptionDetail);
		}
		StatusDTO statusDto = new StatusDTO();
		statusDto = employeeDomain.deleteUserById(id);
		
		
		//employeeDomain.deleteUserById(id);
		//statusDto.setStatus("User Deleted Successfully");
		
		return statusDto;
		
	}
}
