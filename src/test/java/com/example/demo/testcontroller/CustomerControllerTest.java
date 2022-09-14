package com.example.demo.testcontroller;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.controller.CustomerController;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;

import net.minidev.json.JSONValue;

@RunWith(SpringRunner.class)
@WebMvcTest({CustomerController.class})
public class CustomerControllerTest {
	
	@Autowired
	private MockMvc mockmvc;	
	@Autowired
	private CustomerController controller;
	
	@MockBean
	private CustomerRepository repository;
	@MockBean
	private CustomerService service;
	
	//default customer list used in all the tests
	List<Customer> customerList = Arrays.asList(
			new Customer(537,"rahul","hyderabad","active"));
	
	//customer list in json format formatted as a string
	String exampleCustomerJson = "{\"id\":\"537\",\"name\":\"rahul\",\"address\":\"hyderabad\",\"status\":\"active\"}";

	//data is added to database before each test
	@Before
	public void addingToDb() {
		repository.saveAll(customerList);
	}
	
	//data is deleted from database after each test
	@After
	public void clearDb() {
		repository.deleteAll();
	}
	
	//tests corresponding to respective methods in controller class
	@Test
	public void test_getAllActiveCustomers() throws Exception{
		
		Mockito.when(controller.getAllActiveCustomers()).thenReturn(customerList);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customer").accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockmvc.perform(requestBuilder).andReturn();
		
		String expected = JSONValue.toJSONString(customerList);
		
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(),false);
	}
	
	@Test
	public void test_getByName() throws Exception{
		
		Mockito.when(controller.getByName("rahul")).thenReturn(customerList);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customer/rahul").accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockmvc.perform(requestBuilder).andReturn();
		
		String expected = JSONValue.toJSONString(customerList);
		
		//System.out.println(result.getResponse().getContentAsString());
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(),false);
			
	}
	
	@Test
	public void test_saveCustomer() throws Exception{
		
		List<Customer> customerList1 = Arrays.asList(
				new Customer(537,"rahul","hyderabad","active"));
		
		Mockito.when(controller.saveCustomer(new Customer(537,"rahul","hyderabad","active"))).thenReturn(customerList1);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/customer/save")
				.accept(MediaType.APPLICATION_JSON)
				.content(exampleCustomerJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockmvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		//System.out.println(response.getContentAsString());
		assertEquals(HttpStatus.CREATED.value(),response.getStatus());
		
		//assertEquals("http://localhost:8080/customer/save",response.getHeader(HttpHeaders.LOCATION));
		
	}
	
	@Test
	public void test_deleteCustomerById() throws Exception {
		
		Customer customerlistdel = 
				new Customer(537,"rahul","hyderabad","deleted");
		
		String exampleCustomerJson1 = "{\"id\":\"537\",\"name\":\"rahul\",\"address\":\"hyderabad\",\"status\":\"deleted\"}";
		
		Mockito.when(controller.deleteCustomerById(537)).thenReturn(customerlistdel);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/customer/delete/537")
				.accept(MediaType.APPLICATION_JSON)
				.content(exampleCustomerJson1)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockmvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		//System.out.println(response.getContentAsString());
		assertEquals(HttpStatus.OK.value(),response.getStatus());
		
	}
	
	
}
