package ru.javarush.winter2014.zkoss;

import java.util.Date;

public class DataFilter {

	private String name = null;
	private Integer age = null;
	private Boolean admin = null;
	private Date startDate = null;
	private Date endDate = null;

	public void clear() {
		name = null;
		age = null;
		admin = null;
		startDate = null;
		endDate = null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
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

	
	
}