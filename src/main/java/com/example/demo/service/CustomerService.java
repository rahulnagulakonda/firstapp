package com.example.demo.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;

//different services that are needed to establish communication between repository and controller class
@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository repository;
	
	//returns a list of all the active customers
	public List<Customer> getAllByStatus() throws RuntimeException{
		if(repository.findAllByStatus("active")==null) {
			throw new RuntimeException("No Customers Active");
		} else {
			return repository.findAllByStatus("active");
		}
	}
	
	//returns the details of a newly added customer
	public List<Customer> saveCustomer(Customer inputCustomer) throws RuntimeException{
		
		if(repository.findByName(inputCustomer.getName()).isEmpty()) { 
			repository.save(inputCustomer);
			return repository.findByName(inputCustomer.getName());
		} else { 
			throw new RuntimeException("Customer Already Exists");
		}

	}
	
	//returns the details of a customer with a given name
	public List<Customer> getByName(String inputName) throws RuntimeException{
		
		if(repository.findByName(inputName).isEmpty()) { 
			throw new RuntimeException("Customer Does Not Exists");
		} else { 
			return repository.findByName(inputName);
		}
	}
	
	//returns the updated details of the customer after setting status to deleted
	public Customer deleteById(int id) throws RuntimeException{
		 
		if(repository.findById(id)==null) {
			throw new RuntimeException("Customer Does Not Exists");
			
		} else {
			Customer customer = repository.findById(id);
			if(customer.getStatus().equals("deleted")) {
				throw new RuntimeException("Customer Status Deleted");
			} else {
				customer.setStatus("deleted");
				repository.save(customer);
				return repository.findById(id);
			}
		}
	}
}
