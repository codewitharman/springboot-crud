package com.example.springboot_crud.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot_crud.dto.ApiResponse;
import com.example.springboot_crud.dto.EmployeeRequestDTO;
import com.example.springboot_crud.dto.EmployeeResponseDTO;
import com.example.springboot_crud.exception.GlobalExceptionHandler;
import com.example.springboot_crud.service.EmployeeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@PostMapping
	public ResponseEntity<ApiResponse<EmployeeResponseDTO>> createEmployee(@RequestBody EmployeeRequestDTO request) {
		EmployeeResponseDTO response = employeeService.createEmployee(request);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ApiResponse.success("Employee created successfully", response));// HTTP 201
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<EmployeeResponseDTO>>> getAllEmployees() {
		List<EmployeeResponseDTO> response = employeeService.getAllEmployees();
		return ResponseEntity.ok(ApiResponse.success("Employees retrieved successfully", response));// HTTP 200

	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<EmployeeResponseDTO>> getEmployeeById(@PathVariable Long id) {
		EmployeeResponseDTO response = employeeService.getEmployeeById(id);
		return ResponseEntity.ok(ApiResponse.success("Employee retrieved successfully", response));// HTTP 200
	}

	@GetMapping("/department/{department}")
	public ResponseEntity<ApiResponse<List<EmployeeResponseDTO>>> getEmployeesByDepartment(
			@PathVariable String department) {
		List<EmployeeResponseDTO> response = employeeService.getEmployeesByDepartment(department);

		return ResponseEntity.ok(ApiResponse.success("Employees in '" + department + "' retrieved", response));

	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<EmployeeResponseDTO>> updateEmployee(@PathVariable Long id,
			@RequestBody EmployeeRequestDTO request) {
		EmployeeResponseDTO response = employeeService.updateEmployee(id, request);
		return ResponseEntity.ok(ApiResponse.success("Employee updated successfully", response));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<EmployeeResponseDTO>> deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployee(id);
		return ResponseEntity.ok(ApiResponse.success("Employee deleted successfully"));
	}

	@GetMapping("/search")
	public ResponseEntity<ApiResponse<List<EmployeeResponseDTO>>> searchByName(@RequestParam String name) {
		List<EmployeeResponseDTO> response = employeeService.searchEmployeesByName(name);
		return ResponseEntity.ok(ApiResponse.success("Search results for '" + name + "'", response));
	}

	@GetMapping("/salary")
	public ResponseEntity<ApiResponse<List<EmployeeResponseDTO>>> getEmployeesAboveSalary(@RequestParam Double salary) {
		List<EmployeeResponseDTO> response = employeeService.getEmployeesAboveSalary(salary);
		return ResponseEntity.ok(ApiResponse.success("Search results for '" + salary + "'", response));
	}

}
