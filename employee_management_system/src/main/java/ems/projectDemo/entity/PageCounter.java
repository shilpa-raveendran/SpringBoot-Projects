package ems.projectDemo.entity;

import lombok.Data;

@Data
public class PageCounter {

	public int pageCounter;
	
	public void incrementCount() {
		this.pageCounter=pageCounter+1;
	}
	public int getCurrrentPageCount() {
		
		return this.getPageCounter();
	}
}
