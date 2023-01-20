package ems.projectDemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ems.projectDemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	@Query("SELECT emp FROM Employee emp WHERE emp.department LIKE %?1%")
    List<Employee> searchDepartment(String department);

}
