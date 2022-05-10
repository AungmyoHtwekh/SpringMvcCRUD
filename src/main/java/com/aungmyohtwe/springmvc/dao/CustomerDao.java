package com.aungmyohtwe.springmvc.dao;

import java.util.List;

import com.aungmyohtwe.springmvc.model.Customer;


public interface CustomerDao {

	Customer findById(int id);

	void save(Customer user);
	
	void deleteById(Integer id);
	
	List<Customer> findAllCustomers();

}

