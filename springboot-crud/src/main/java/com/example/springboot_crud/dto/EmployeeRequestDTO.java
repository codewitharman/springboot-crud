package com.example.springboot_crud.dto;

import java.util.Objects;

import jakarta.validation.constraints.*;

//Request DTO (what the user sends TO you)
public class EmployeeRequestDTO {

	@NotBlank(message = "First name is required")
	@Size(min = 2, max = 50, message = "First name must be 2–50 characters")
	private String firstName;

	@NotBlank(message = "Last name is required")
	@Size(min = 2, max = 50, message = "Last name must be 2–50 characters")
	private String lastName;

	@NotBlank(message = "Email is required")
	@Email(message = "Email must be a valid address")
	private String email;

	@NotBlank(message = "Department is required")
	private String department;

	@NotNull(message = "Salary is required")
	@Positive(message = "Salary must be a positive number")
	private Double salary;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	@Override
	public int hashCode() {
		return Objects.hash(department, email, firstName, lastName, salary);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeRequestDTO other = (EmployeeRequestDTO) obj;
		return Objects.equals(department, other.department) && Objects.equals(email, other.email)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(salary, other.salary);
	}

	@Override
	public String toString() {
		return "EmployeeRequestDTO [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", department=" + department + ", salary=" + salary + "]";
	}

	public EmployeeRequestDTO(
			@NotBlank(message = "First name is required") @Size(min = 2, max = 50, message = "First name must be 2–50 characters") String firstName,
			@NotBlank(message = "Last name is required") @Size(min = 2, max = 50, message = "Last name must be 2–50 characters") String lastName,
			@NotBlank(message = "Email is required") @Email(message = "Email must be a valid address") String email,
			@NotBlank(message = "Department is required") String department,
			@NotNull(message = "Salary is required") @Positive(message = "Salary must be a positive number") Double salary) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.department = department;
		this.salary = salary;
	}

	public EmployeeRequestDTO() {
		super();
	}

}
