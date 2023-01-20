package ems.projectDemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="EMPLOYEE")
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emp_id")
	private Integer empId;
	
	@Column(name = "first_name")
	@NotEmpty(message = "Please provide first name")
	private String firstName;
	
	@Column(name = "last_Name")
	@NotEmpty(message = "Please provide last name")
	private String lastName;
	
	@Column(name = "salary")
	@DecimalMin(value = "15.5", message="The min salary is $15.50")
	private double salary;
	
	@Column(name = "email")
	@Email
	@NotEmpty(message = "Please provide an email")
	private String email;
	
	@Column(name = "department")
	@NotEmpty(message = "Please provide the department")
	private String department;

}
