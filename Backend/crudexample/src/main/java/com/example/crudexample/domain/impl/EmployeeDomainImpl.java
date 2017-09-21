package com.example.crudexample.domain.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crudexample.domain.EmployeeDomain;
import com.example.crudexample.domain.entity.Employee;
import com.example.crudexample.domain.repo.EmployeeRepo;
import com.example.crudexample.dto.StatusDTO;

@Service("employeeDomain")
public class EmployeeDomainImpl implements EmployeeDomain{
	@Autowired(required = true)
	private EmployeeRepo employeeRepo;
	
	@Override
	public List<Employee> getAllUsersProfile() {
		return employeeRepo.findAll();
	}

	@Override
	public StatusDTO addUser(Employee employee) {
		StatusDTO statusDto = new StatusDTO();
		employeeRepo.save(employee);
		statusDto.setStatus("User Created Successfully");
		return statusDto;
	}

	@Override
	public Employee getUserById(String id) {
		return employeeRepo.findOne(Long.parseLong(id));
	}

	@Override
	public StatusDTO deleteUserById(String id) {
		StatusDTO statusDto = new StatusDTO();
		employeeRepo.delete(Long.parseLong(id));
		statusDto.setStatus("User Deleted Successfully");
		return statusDto;
	}
}
