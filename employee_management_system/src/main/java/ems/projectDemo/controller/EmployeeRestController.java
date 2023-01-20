package ems.projectDemo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ems.projectDemo.entity.Employee;
import ems.projectDemo.service.EmployeeServiceImpl;

@RestController
@RequestMapping("/employees")
public class EmployeeRestController {

	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;
	
	@GetMapping("/getEmployeeById")
	public Employee getEmployeeByEmpId(@RequestParam int employeeId) {
		Employee employee = employeeServiceImpl.getEmployeeById(employeeId);

		if (employee == null) {
			throw new RuntimeException("Employee id not found - " + employeeId);
		}

		return employee;
	}
}
