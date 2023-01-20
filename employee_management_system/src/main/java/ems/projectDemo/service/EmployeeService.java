package ems.projectDemo.service;

import java.util.List;

import ems.projectDemo.entity.Employee;

public interface EmployeeService {

	// method to list all employees
	public List<Employee> findAll();

	// find employee by empId
	public Employee getEmployeeById(int empId);

	public void save(Employee Employee);

	public void deleteById(int theId);
	
	public List<Employee> searchDepartment(String department);

}
