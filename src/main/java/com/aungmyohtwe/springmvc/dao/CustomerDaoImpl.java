package com.aungmyohtwe.springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.aungmyohtwe.springmvc.model.Customer;


@Repository("customerDao")
public class CustomerDaoImpl extends AbstractDao<Integer, Customer> implements CustomerDao {

	public Customer findById(int id) {
		Customer customer = getByKey(id);
		return customer;
	}

	@SuppressWarnings("unchecked")
	public List<Customer> findAllCustomers() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Customer> customers = (List<Customer>) criteria.list();
		return customers;
	}

	public void save(Customer customer) {
		persist(customer);
	}

	public void deleteById(Integer id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		Customer customer = (Customer)crit.uniqueResult();
		delete(customer);
	}

}
