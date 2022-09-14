package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;

//controls the communication between web server and the application
@RestController
@RequestMapping("/customer") //default web page is mapped to "http://localhost:8080/customer"
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	//using get method for viewing the data of active customers on webpage
	@GetMapping
	public List<Customer> getAllActiveCustomers()
	{
		return customerService.getAllByStatus();
	}
	
	//creating a new record or updating an old record using post method
	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Customer> saveCustomer(@RequestBody Customer inputCustomer)
	{
		return customerService.saveCustomer(inputCustomer);
	}
	
	//to view details of a particular customer 
	@GetMapping("/{name}")
	public List<Customer> getByName(@PathVariable String name){
		return customerService.getByName(name);
	}
	
	//deleting a customer using the id 
	@DeleteMapping("/delete/{id}")
	public Customer deleteCustomerById(@PathVariable int id){
		return customerService.deleteById(id);
	}
}
