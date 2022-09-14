package com.example.demo.testrepository;

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

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerRepositoryTest {
	
	@Autowired
	private CustomerRepository repository;
	
	//data is added to database before each test
	@Before
	public void addToDb() {
		List<Customer> customers= Arrays.asList(
				new Customer(537,"rahul","hyderabad","active"),
				new Customer(234,"suneel","bengaluru","deleted"));
		repository.saveAll(customers);
	}
	
	//data is deleted from database after each test
	@After
	public void clearDb() {
		repository.deleteAll();
	}
	
	//tests corresponding to each method in repository class
	@Test
	public void test_findAllByStatus() {	
		List<Customer> findCustomersByStatus = repository.findAllByStatus("active");
		assertThat(findCustomersByStatus.size()).isEqualTo(1);
	}
	
	@Test
	public void test_findByName() {
		List<Customer> findCustomerByName = repository.findByName("rahul");
		assertThat(findCustomerByName.size()).isEqualTo(1);
	}
	
	@Test
	public void test_findCustomerById() {
		Customer findCustomerById = repository.findById(537);
		assertThat(findCustomerById.getId()).isEqualTo(537);
	}

}
