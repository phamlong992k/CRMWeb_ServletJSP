package com.myClass.Entity;


import java.sql.Date;

public class Job {
	private int id;
	private String name;
	private Date startDate;
	private Date endDate;
	public Job() {
		
	}
	
	public int getId() {
		
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "Job [id=" + id + ", name=" + name + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
	
}
