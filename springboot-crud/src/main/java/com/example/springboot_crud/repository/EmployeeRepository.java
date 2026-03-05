package com.example.springboot_crud.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import com.example.springboot_crud.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	boolean existsByEmail(String email);

	boolean existsByEmailAndIdNot(String Email, Long Id);

	Optional<Employee> findByEmail(String email);

	List<Employee> findByDepartmentIgnoreCase(String department);

	@Query("SELECT e FROM Employee e WHERE " + "LOWER(e.firstName) LIKE LOWER(CONCAT('%', :name, '%')) OR "
			+ "LOWER(e.lastName)  LIKE LOWER(CONCAT('%', :name, '%'))")
	List<Employee> searchByName(@Param("name") String name);

	List<Employee> findBySalaryGreaterThan(Double Salary);

}
