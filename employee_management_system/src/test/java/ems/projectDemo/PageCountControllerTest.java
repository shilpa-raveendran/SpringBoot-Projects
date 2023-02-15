package ems.projectDemo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import ems.projectDemo.controller.PageCountController;
import ems.projectDemo.entity.PageCounter;


@WebMvcTest(PageCountController.class)
public class PageCountControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PageCounter pageCounter;
	
	@Test
	public void currentCount() throws Exception  {
		Mockito.when(pageCounter.getCurrrentPageCount()).thenReturn(1);
		mockMvc.perform(get("/employees/currentcount"))
				.andExpect(status().isOk());
	}

}
