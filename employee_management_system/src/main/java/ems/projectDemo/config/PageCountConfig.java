package ems.projectDemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ems.projectDemo.entity.PageCounter;

@Configuration
public class PageCountConfig {
	
	@Bean
	public PageCounter pageCounter() {
		return new PageCounter();
	}

}
