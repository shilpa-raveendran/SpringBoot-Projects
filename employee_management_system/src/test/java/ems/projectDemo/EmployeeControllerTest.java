package ems.projectDemo;

import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import ems.projectDemo.controller.EmployeeController;
import ems.projectDemo.dao.EmployeeRepository;
import ems.projectDemo.entity.Employee;
import ems.projectDemo.entity.PageCounter;
import ems.projectDemo.service.EmployeeServiceImpl;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeRepository empRepo;

	@MockBean
	private EmployeeServiceImpl employeeServiceImpl;

	@MockBean
	private PageCounter pageCounter;

	@Test
	public void findAllTest() throws Exception {
		List<Employee> expectedList = new ArrayList<>();
		expectedList.add(new Employee(null, "Joe", "Boe", 30.5, "joe@bob.com", "HR"));

		Mockito.when(employeeServiceImpl.findAll()).thenReturn(expectedList);

		mockMvc.perform(get("/employees/listAll"))
			.andExpect(status().isOk())
			.andExpect(view().name("employeePage"))
			.andExpect(model().attribute("employeeList", expectedList));
	}

	@Test
	public void showFormForAddTest() throws Exception {
		mockMvc.perform(get("/employees/showAddForm"))
				.andExpect(status().isOk())
				.andExpect(view().name("addForm"))
				.andExpect(model().attribute("employee", new Employee()));
	}

	@Test
	public void addEmployeeTest() throws Exception {
		Employee expectedEmp = new Employee(null, "Joe", "Boe", 30.5, "joe@bob.com", "HR");
		mockMvc.perform(post("/employees/addEmployee")
				.param("firstName", "Joe")
				.param("lastName", "Boe")
				.param("salary", "30.5")
				.param("email", "joe@bob.com")
				.param("department", "HR"))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/employees/listAll"));

		Mockito.verify(employeeServiceImpl).save(eq(expectedEmp));
	}

	@Test
	public void addEmployeeTest_InvalidUser() throws Exception {
		Employee expectedEmp = new Employee(null, "Joe", "Boe", 30.5, "joe", "HR");

		mockMvc.perform(post("/employees/addEmployee")
				.param("firstName", "Joe")
				.param("lastName", "Boe")
				.param("salary", "30.5")
				.param("email", "joe")
				.param("department", "HR"))
				.andExpect(status().isOk())
				.andExpect(view().name("addForm"))
				.andExpect(model().attribute("employee", expectedEmp));

		Mockito.verifyNoInteractions(employeeServiceImpl);
	}

	// Testing the show update form
	@Test
	public void showUpdateFormTest() throws Exception {
		Employee expectedEmp = new Employee(null, "Joe", "Boe", 30.5, "joe@bob.com", "HR");
	
		
		Mockito.when(employeeServiceImpl.getEmployeeById(1)).thenReturn(expectedEmp);
		mockMvc.perform(get("/employees/showFormForUpdate")
				.param("employeeId", "1"))
				.andExpect(status().isOk())
				.andExpect(view().name("employee-form"));
			
	}

	@Test
	public void saveEmployeeTest() throws Exception {
		Employee expectedEmp = new Employee(null, "Joe", "Boe", 30.5, "joe@bob.com", "HR");
		mockMvc.perform(post("/employees/saveUpdate")
				.param("firstName", "Joe")
				.param("lastName", "Boe")
				.param("salary", "30.5")
				.param("email", "joe@bob.com")
				.param("department", "HR"))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/employees/listAll"));

		Mockito.verify(employeeServiceImpl).save(eq(expectedEmp));
	}

	@Test
	public void saveEmployee_InvalidUser() throws Exception {
		Employee expectedEmp = new Employee(null, "Joe", "Boe", 30.5, "joe", "HR");

		mockMvc.perform(post("/employees/saveUpdate")
				.param("firstName", "Joe")
				.param("lastName", "Boe")
				.param("salary", "30.5")
				.param("email", "joe")
				.param("department", "HR"))
				.andExpect(status().isOk())
				.andExpect(view().name("employee-form"))
				.andExpect(model().attribute("employee", expectedEmp));

		Mockito.verifyNoInteractions(employeeServiceImpl);
	}
	
	@Test
	public void deleteTest() throws Exception {
		Employee expectedEmp = new Employee(null, "Joe", "Boe", 30.5, "joe@bob.com", "HR");
		mockMvc.perform(get("/employees/deleteEmployee")
				.param("employeeId", "1")
				.param("firstName", "Joe")
				.param("lastName", "Boe")
				.param("salary", "30.5")
				.param("email", "joe@bob.com")
				.param("department", "HR"))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/employees/listAll"));
	}
	
	@Test
	public void searchByDepartmentTest() throws  Exception {
		List<Employee> expectedList = new ArrayList<>();
		expectedList.add(new Employee(null, "Simon", "Test", 233.3, "tes@tes.com", "HR"));
		
		Mockito.when(empRepo.searchDepartment("HR")).thenReturn(expectedList);
		
		mockMvc.perform(get("/employees/findByDepartment")
				.param("department", "HR"))
				.andExpect(status().isOk())
				.andExpect(view().name("employeePage"));
	}
}
