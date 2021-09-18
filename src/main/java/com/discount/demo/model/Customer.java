package com.discount.demo.model;

import org.springframework.data.annotation.Id;

/*
 * Model data of the customer
 */
public class Customer {
	
	public long id;
	public String name;
	public boolean isEmployee;
	public boolean isAffiliate;
	public String dateRegistered;
	
	
	
	
	public Customer(long id, String name, boolean isEmployee, boolean isAffiliate, String dateRegistered) {
		super();
		this.id = id;
		this.name = name;
		this.isEmployee = isEmployee;
		this.isAffiliate = isAffiliate;
		this.dateRegistered = dateRegistered;
	}
	@Id
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isEmployee() {
		return isEmployee;
	}
	public void setEmployee(boolean isEmployee) {
		this.isEmployee = isEmployee;
	}
	public boolean isAffiliate() {
		return isAffiliate;
	}
	public void setAffiliate(boolean isAffiliate) {
		this.isAffiliate = isAffiliate;
	}
	public String getDateRegistered() {
		return dateRegistered;
	}
	public void setDateRegistered(String dateRegistered) {
		this.dateRegistered = dateRegistered;
	}
	
	
	

}
