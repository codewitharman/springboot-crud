// java
package com.example.springboot_crud.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springboot_crud.dto.EmployeeRequestDTO;
import com.example.springboot_crud.dto.EmployeeResponseDTO;
import com.example.springboot_crud.exception.DuplicateEmailException;
import com.example.springboot_crud.exception.ResourceNotFoundException;
import com.example.springboot_crud.model.Employee;
import com.example.springboot_crud.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Override
	public EmployeeResponseDTO createEmployee(EmployeeRequestDTO request) {
		log.debug("Creating employee with email: {}", request.getEmail());
		if (employeeRepository.existsByEmail(request.getEmail())) {
			throw new DuplicateEmailException(request.getEmail());
		}
		Employee employee = mapToEntity(request);
		Employee saved = employeeRepository.save(employee);
		return mapToResponse(saved);
	}

	@Override
	@Transactional(readOnly = true)
	public EmployeeResponseDTO getEmployeeById(Long id) {
		Employee employee = findOrThrow(id);
		return mapToResponse(employee);
	}

	@Override
	public List<EmployeeResponseDTO> getAllEmployees() {
		return employeeRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
	}

	@Override
	public EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO request) {
		Employee existing = findOrThrow(id);
		if (employeeRepository.existsByEmailAndIdNot(request.getEmail(), id)) {
			throw new DuplicateEmailException(request.getEmail());
		}

		existing.setFirstName(request.getFirstName());
		existing.setLastName(request.getLastName());
		existing.setEmail(request.getEmail());
		existing.setDepartment(request.getDepartment());
		existing.setSalary(request.getSalary());

		Employee updated = employeeRepository.save(existing);

		return mapToResponse(updated);
	}

	@Override
	public void deleteEmployee(Long id) {
		Employee employee = findOrThrow(id);
		employeeRepository.delete(employee);
		log.info("Employee deleted with id: {}", id);
	}

	@Override
	public List<EmployeeResponseDTO> getEmployeesByDepartment(String department) {
		return employeeRepository.findByDepartmentIgnoreCase(department).stream().map(this::mapToResponse)
				.collect(Collectors.toList());
	}

	@Override
	public List<EmployeeResponseDTO> searchEmployeesByName(String name) {
		return employeeRepository.searchByName(name).stream().map(this::mapToResponse).collect(Collectors.toList());
	}

	@Override
	public List<EmployeeResponseDTO> getEmployeesAboveSalary(Double salary) {
		return employeeRepository.findBySalaryGreaterThan(salary).stream().map(this::mapToResponse)
				.collect(Collectors.toList());
	}

	private Employee mapToEntity(EmployeeRequestDTO dto) {
		Employee emp = new Employee();
		emp.setFirstName(dto.getFirstName());
		emp.setLastName(dto.getLastName());
		emp.setEmail(dto.getEmail());
		emp.setDepartment(dto.getDepartment());
		emp.setSalary(dto.getSalary());
		return emp;
	}

	private EmployeeResponseDTO mapToResponse(Employee e) {
		EmployeeResponseDTO dto = new EmployeeResponseDTO();
		dto.setId(e.getId());
		dto.setFirstName(e.getFirstName());
		dto.setLastName(e.getLastName());
		dto.setEmail(e.getEmail());
		dto.setDepartment(e.getDepartment());
		dto.setSalary(e.getSalary());
		dto.setCreatedAt(e.getCreatedAt());
		dto.setUpdatedAt(e.getUpdatedAt());
		return dto;
	}

	private Employee findOrThrow(Long id) {
		return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
	}
}
