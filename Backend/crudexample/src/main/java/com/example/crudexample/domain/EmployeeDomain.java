package com.example.crudexample.domain;

import java.util.List;

import com.example.crudexample.domain.entity.Employee;
import com.example.crudexample.dto.EmployeeDTO;
import com.example.crudexample.dto.StatusDTO;

public interface EmployeeDomain {
	
	/**
	 * 
	 * @return List<Employee>
	 */
	List<Employee> getAllUsersProfile();
	
	/**
	 * 
	 * @param employee
	 * @return StatusDTO
	 */
	StatusDTO addUser(Employee employee);
	
	/**
	 * 
	 * @param id
	 * @return Employee
	 */
	Employee getUserById(String id);
	/**
	 * 
	 * 
	 * @param id
	 * @return Employee
	 */
	StatusDTO deleteUserById(String id);
}
