package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Customer;


//interface to perform CRUD operations on the database

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Integer> 
{
	List<Customer> findAllByStatus(String status);
	List<Customer> findByName(String name);
	Customer findById(int id);
}
