package com.aungmyohtwe.springmvc.service;

import java.util.List;

import com.aungmyohtwe.springmvc.model.Customer;


public interface CustomerService {
	
	Customer findById(int id);

	void saveCustomer(Customer customer);
	
	void updateCustomer(Customer customer);
	
	void deleteCustomerById(Integer id);

	List<Customer> findAllCustomers();

}