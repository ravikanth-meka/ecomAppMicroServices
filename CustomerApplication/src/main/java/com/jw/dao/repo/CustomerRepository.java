package com.jw.dao.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jw.dao.entities.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String> {

	Customer findByEmail(String email);
}
