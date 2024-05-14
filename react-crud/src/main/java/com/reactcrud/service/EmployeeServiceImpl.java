package com.reactcrud.service;

import org.springframework.stereotype.Service;

import com.reactcrud.entity.Employee;
import com.reactcrud.repository.EmployeeRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
	
	EmployeeRepo empRepo;

	@Override
	public Employee createEmployee(Employee emp) {
		return empRepo.save(emp);
	}

}
