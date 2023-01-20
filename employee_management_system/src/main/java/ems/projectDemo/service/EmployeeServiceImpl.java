package ems.projectDemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ems.projectDemo.dao.EmployeeRepository;
import ems.projectDemo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository empRepo;

	@Autowired
	public EmployeeServiceImpl(EmployeeRepository empRepo) {
		this.empRepo = empRepo;
	}

	@Override
	public List<Employee> findAll() {
		return empRepo.findAll();
	}

	@Override
	public Employee getEmployeeById(int empId) {
		Optional<Employee> emp = empRepo.findById(empId);
		Employee theEmployee = null;

		if (emp.isPresent()) {
			theEmployee = emp.get();
		} else {
			throw new RuntimeException("Did not find employee id - " + empId);
		}

		return theEmployee;

	}

	@Override
	public void save(Employee theEmployee) {
		empRepo.save(theEmployee);
	}

	@Override
	public void deleteById(int theId) {
		empRepo.deleteById(theId);
	}

	@Override
	public List<Employee> searchDepartment(String department) {
		return empRepo.searchDepartment(department);
	}
}
