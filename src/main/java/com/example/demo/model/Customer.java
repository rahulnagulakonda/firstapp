package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


//Model for Table in Database 
@Entity
@Table(name="customer")
public class Customer {
	@Id //defining primary key for table
	private int id;
	private String name;
	private String address; 
	private String status;
	
	//Non-Parameterized Constructor
	public Customer() {}
	
	//Parameterized Constructor 
	public Customer(int id, String name, String address, String status) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.status = status;
	}
	
	//getters - to retrieve data from the database
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	public String getStatus() {
		return status;
	}
	
	//setters - to make changes to the database
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
