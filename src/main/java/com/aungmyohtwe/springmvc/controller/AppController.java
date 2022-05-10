package com.aungmyohtwe.springmvc.controller;

import java.util.List;

import javax.validation.Valid;

import com.aungmyohtwe.springmvc.model.Customer;
import com.aungmyohtwe.springmvc.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/")
public class AppController {

	@Autowired
	CustomerService customerService;
	
	@Autowired
	MessageSource messageSource;

	/**
	 * This method will list all existing customers.
	 */
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model) {

		List<Customer> customers = customerService.findAllCustomers();
		model.addAttribute("customers", customers);
		return "customerslist";
	}

	/**
	 * This method will provide the medium to add a new customer.
	 */
	@RequestMapping(value = { "/newcustomer" }, method = RequestMethod.GET)
	public String newCustomer(ModelMap model) {
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		model.addAttribute("edit", false);
		return "registration";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the customer input
	 */
	@RequestMapping(value = { "/newcustomer" }, method = RequestMethod.POST)
	public String saveCustomer(@Valid Customer customer, BindingResult result,
						   ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}
		
		customerService.saveCustomer(customer);

		model.addAttribute("success", "Customer " + customer.getFirstName() + " "+ customer.getLastName() + " registered successfully");
		//return "success";
		return "registrationsuccess";
	}


	/**
	 * This method will provide the medium to update an existing customer.
	 */
	@RequestMapping(value = { "/edit-customer-{id}" }, method = RequestMethod.GET)
	public String editCustomer(@PathVariable Integer id, ModelMap model) {
		Customer customer = customerService.findById(id);
		model.addAttribute("customer", customer);
		model.addAttribute("edit", true);
		return "registration";
	}
	
	/**
	 * This method will be called on form submission, handling POST request for
	 * updating customer in database. It also validates the customer input
	 */
	@RequestMapping(value = { "/edit-customer-{id}" }, method = RequestMethod.POST)
	public String updateCustomer(@Valid Customer customer, BindingResult result,
							 ModelMap model, @PathVariable Integer id) {

		if (result.hasErrors()) {
			return "registration";
		}

		customerService.updateCustomer(customer);

		model.addAttribute("success", "Customer " + customer.getFirstName() + " "+ customer.getLastName() + " updated successfully");
		return "registrationsuccess";
	}

	
	/**
	 * This method will delete an customer by it's Id value.
	 */
	@RequestMapping(value = { "/delete-customer-{id}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable Integer id) {
		customerService.deleteCustomerById(id);
		return "redirect:/list";
	}


}
