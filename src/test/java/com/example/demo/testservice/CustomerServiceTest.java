package com.example.demo.testservice;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {
	
	@Autowired
	private CustomerService service;
	@Autowired
	private CustomerRepository repository;
	
	//data is added to database before each test is performed
	@Before
	public void addToDb() {
		List<Customer> customers= Arrays.asList(
				new Customer(537,"rahul","hyderabad","active"),
				new Customer(234,"suneel","bengaluru","deleted"));
		repository.saveAll(customers);
	}
	
	//data is deleted from database after each test 
	@After
	public void cleardb() {
		repository.deleteAll();
	}
	
	//tests corresponding to respective methods in service class
	@Test
	public void test_getAllByStatus() {
		List<Customer> getCustomersByStatus = service.getAllByStatus();
		assertThat(getCustomersByStatus.size()).isEqualTo(1);
	}
	
	@Test
	public void test_saveCustomer() {
		Customer newCustomer = new Customer(789,"ravi","chennai","active");
		service.saveCustomer(newCustomer);
		List<Customer> getCustomersByStatus = service.getAllByStatus();
		assertThat(getCustomersByStatus.size()).isEqualTo(2);
	}

	@Test
	public void test_getByName() {
		List<Customer> getCustomerByName = service.getByName("rahul");
		assertThat(getCustomerByName.size()).isEqualTo(1);
	}
	
	@Test
	public void test_deleteById() {
		Customer deletedCustomer = service.deleteById(537);
		assertThat(deletedCustomer.getStatus()).isEqualTo("deleted");
	}
}
