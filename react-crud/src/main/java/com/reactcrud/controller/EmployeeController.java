package com.reactcrud.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.reactcrud.entity.Employee;
import com.reactcrud.exception.ResourceNotFoundException;
import com.reactcrud.repository.EmployeeRepo;
import com.reactcrud.service.EmployeeService;

import lombok.AllArgsConstructor;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/emp")
@AllArgsConstructor
@CrossOrigin("*")
public class EmployeeController {

	EmployeeService empService;
	EmployeeRepo empRepo;
	
	@PostMapping("/create")
//	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> createEmployee(@RequestBody Employee emp) {
//		return ResponseEntity.status(HttpStatus.CREATED).body(empService.createEmployee(emp));
		return new ResponseEntity<>(empService.createEmployee(emp), HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getEmpById(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.FOUND).body(empRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not foud " +id)));
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllEmp() {
		return ResponseEntity.status(HttpStatus.OK).body(empRepo.findAll());
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateEmployee(@RequestBody Employee updEmp) {
		Long id = updEmp.getId();
		Employee emp = empRepo.findById(id)
							.orElseThrow(() -> new ResourceNotFoundException("Resource not foud " +id));
		BeanUtils.copyProperties(updEmp, emp);
		empRepo.save(emp);
		return new ResponseEntity<>(empService.createEmployee(emp), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEmp(@PathVariable Long id) {
		empRepo.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully");
	}
	
}
