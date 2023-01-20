package ems.projectDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ems.projectDemo.entity.PageCounter;

@RestController
@RequestMapping("employees")
public class PageCountController {
	
	@Autowired
	private PageCounter pageCounter;
	
	@GetMapping("/currentcount")
	public Integer currentCount() {
		return pageCounter.getCurrrentPageCount();
	}

}
