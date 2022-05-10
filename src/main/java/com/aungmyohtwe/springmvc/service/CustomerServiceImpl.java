package com.aungmyohtwe.springmvc.service;

import java.util.List;

import com.aungmyohtwe.springmvc.dao.CustomerDao;
import com.aungmyohtwe.springmvc.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao dao;

	public Customer findById(int id) {
		return dao.findById(id);
	}

	public void saveCustomer(Customer customer) {
		dao.save(customer);
	}

	public void updateCustomer(Customer customer) {
		Customer entity = dao.findById(customer.getId());
		if(entity!=null){
			entity.setFirstName(customer.getFirstName());
			entity.setLastName(customer.getLastName());
			entity.setEmail(customer.getEmail());
			entity.setPhoneNo(customer.getPhoneNo());
			entity.setAddress(customer.getAddress());
		}
	}

	
	public void deleteCustomerById(Integer id) {
		dao.deleteById(id);
	}

	public List<Customer> findAllCustomers() {
		return dao.findAllCustomers();
	}

	
}
