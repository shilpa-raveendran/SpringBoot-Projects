package ems.projectDemo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ems.projectDemo.entity.Employee;
import ems.projectDemo.entity.PageCounter;
import ems.projectDemo.service.EmployeeServiceImpl;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;
	
	@Autowired
	private PageCounter pageCounter;

	@GetMapping("/listAll")
	public String findAll(Model model) {

		List<Employee> empList = employeeServiceImpl.findAll();
		pageCounter.incrementCount();
		model.addAttribute("employeeList", empList);
		model.addAttribute("currentPageCount",pageCounter.getPageCounter());
		return "employeePage";
	}

	@GetMapping("/showAddForm")
	public String showFormForAdd(Model model) {

		// create model attribute to bind form data
		Employee theEmployee = new Employee();

		model.addAttribute("employee", theEmployee);
		model.addAttribute("currentPageCount",pageCounter.getPageCounter());
		return "addForm";
	}

	@PostMapping("/addEmployee")
	public String addEmployee(@Valid Employee toSave, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "addForm";
		} else {
			employeeServiceImpl.save(toSave);
			return "redirect:/employees/listAll";
		}
	}

	// /employees/showFormForUpdate/{employeeId} - update existing employee

	@GetMapping("/showFormForUpdate")
	public String showUpdateForm(@RequestParam("employeeId") int employeeId, Model model) {

		// get the employee from the service
		Employee employee = employeeServiceImpl.getEmployeeById(employeeId);

		// set employee as a model attribute to pre-populate the form
		model.addAttribute("employee", employee);
		model.addAttribute("currentPageCount",pageCounter.getPageCounter());
		// send over to our form
		return "employee-form";

	}

	@PostMapping("/saveUpdate")
	public String saveEmployee(@Valid Employee employee, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "employee-form";
		} else {
			employeeServiceImpl.save(employee);
			return "redirect:/employees/listAll";
		}
	}

	// employees/{employeeId} - delete employee

	@GetMapping("/deleteEmployee")
	public String delete(@RequestParam("employeeId") int theId) {

		// delete the employee
		employeeServiceImpl.deleteById(theId);

		// redirect to /employees/list
		return "redirect:/employees/listAll";

	}
	@GetMapping("/findByDepartment")
	public String searchByDepartment(@RequestParam("department") String department , Model model) {
		List<Employee> empList= employeeServiceImpl.searchDepartment(department);
		model.addAttribute("employeeList", empList);
		model.addAttribute("currentPageCount",pageCounter.getPageCounter());
		model.addAttribute("searchDept",department);
		return "employeePage";
	}
}
