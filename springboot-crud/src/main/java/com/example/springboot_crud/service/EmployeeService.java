package com.example.springboot_crud.service;

import java.util.List;

import com.example.springboot_crud.dto.EmployeeRequestDTO;
import com.example.springboot_crud.dto.EmployeeResponseDTO;

public interface EmployeeService {
	
	EmployeeResponseDTO createEmployee(EmployeeRequestDTO  request);

	EmployeeResponseDTO getEmployeeById(Long id);

	List<EmployeeResponseDTO> getAllEmployees();

	EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO request);

	void deleteEmployee(Long id);

	List<EmployeeResponseDTO> getEmployeesByDepartment(String department);

	List<EmployeeResponseDTO> searchEmployeesByName(String name);

	List<EmployeeResponseDTO> getEmployeesAboveSalary(Double salary);

}
