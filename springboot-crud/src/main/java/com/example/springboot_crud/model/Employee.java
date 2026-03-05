package com.example.springboot_crud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Objects;

// @Entity tells JPA: "This class represents a database table"
@Entity

// @Table(name = "employees") — names the table in the DB
// Without this, table name defaults to class name (Employee → EMPLOYEE)
@Table(name = "employees")

// Lombok annotations — these auto-generate code so you don't have to write it
@Data // generates getters, setters, toString, equals, hashCode
@NoArgsConstructor // generates: public Employee() {}
@AllArgsConstructor // generates: public Employee(Long id, String firstName, ...)
@Builder // lets you write: Employee.builder().firstName("Alice").build()
public class Employee {

	@Id // marks this field as the PRIMARY KEY
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// IDENTITY = auto-increment (1, 2, 3, ...) handled by the database
	private Long id;

	@NotBlank(message = "First name is required")
	// @NotBlank = not null AND not empty string AND not just spaces
	@Size(min = 2, max = 50, message = "First name must be 2–50 characters")
	@Column(name = "first_name", nullable = false, length = 50)
	private String firstName;

	@NotBlank(message = "Last name is required")
	@Size(min = 2, max = 50, message = "Last name must be 2–50 characters")
	@Column(name = "last_name", nullable = false, length = 50)
	private String lastName;

	@NotBlank(message = "Email is required")
	@Email(message = "Must be a valid email address")
	// unique = true ensures no two employees can have the same email
	@Column(name = "email", nullable = false, unique = true, length = 100)
	private String email;

	@NotBlank(message = "Department is required")
	@Column(name = "department", nullable = false, length = 50)
	private String department;

	@NotNull(message = "Salary is required")
	@Positive(message = "Salary must be a positive number")
	@Column(name = "salary", nullable = false)
	private Double salary;

	// updatable = false means this field is NEVER changed after first insert
	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	// @PrePersist runs automatically BEFORE the first INSERT
	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

	// @PreUpdate runs automatically BEFORE every UPDATE
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * @return the salary
	 */
	public Double getSalary() {
		return salary;
	}

	/**
	 * @param salary the salary to set
	 */
	public void setSalary(Double salary) {
		this.salary = salary;
	}

	/**
	 * @return the createdAt
	 */
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the updatedAt
	 */
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public int hashCode() {
		return Objects.hash(createdAt, department, email, firstName, id, lastName, salary, updatedAt);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(createdAt, other.createdAt) && Objects.equals(department, other.department)
				&& Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(id, other.id) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(salary, other.salary) && Objects.equals(updatedAt, other.updatedAt);
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", department=" + department + ", salary=" + salary + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + "]";
	}

	public Employee(Long id,
			@NotBlank(message = "First name is required") @Size(min = 2, max = 50, message = "First name must be 2–50 characters") String firstName,
			@NotBlank(message = "Last name is required") @Size(min = 2, max = 50, message = "Last name must be 2–50 characters") String lastName,
			@NotBlank(message = "Email is required") @Email(message = "Must be a valid email address") String email,
			@NotBlank(message = "Department is required") String department,
			@NotNull(message = "Salary is required") @Positive(message = "Salary must be a positive number") Double salary,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.department = department;
		this.salary = salary;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}