package com.jw.controller;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.jw.dao.entities.Customer;
import com.jw.dao.repo.CustomerRepository;
import com.jw.utils.StringUtil;

@RestController
@RequestMapping("customer")
public class CustomerController {

	@Autowired
	CustomerRepository customerRepository;

	@PostMapping
	public Customer addNewCustomer(@RequestBody Customer customer) {
		try {
			customerRepository.save(customer);
		} catch (DataIntegrityViolationException | ConstraintViolationException e) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Please use different email id", e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer Data is not correct", e);
		}
		return customer;
	}

	@PutMapping
	public Customer updateExistingCustomer(@RequestParam(value = "email") String email,
			@RequestBody Customer customer) {
		Customer updateCustomer = null;
		try {
			updateCustomer = customerRepository.findByEmail(email);
			if (updateCustomer == null) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer Not Found with id: " + email);
			}
			updateCustomer.setAddress(StringUtil.isNotNullorNotBlank(customer.getAddress()) ? customer.getAddress()
					: updateCustomer.getAddress());

			updateCustomer.setCity(
					StringUtil.isNotNullorNotBlank(customer.getCity()) ? customer.getCity() : updateCustomer.getCity());

			updateCustomer.setContactTitle(
					StringUtil.isNotNullorNotBlank(customer.getContactTitle()) ? customer.getContactTitle()
							: updateCustomer.getContactTitle());

			updateCustomer.setCustomerName(
					StringUtil.isNotNullorNotBlank(customer.getCustomerName()) ? customer.getCustomerName()
							: updateCustomer.getCustomerName());

			updateCustomer.setPhone(StringUtil.isNotNullorNotBlank(customer.getPhone()) ? customer.getPhone()
					: updateCustomer.getPhone());

			updateCustomer
					.setPostalCode(StringUtil.isNotNullorNotBlank(customer.getPostalCode()) ? customer.getPostalCode()
							: updateCustomer.getPostalCode());

			updateCustomer.setState(StringUtil.isNotNullorNotBlank(customer.getState()) ? customer.getState()
					: updateCustomer.getState());

			customerRepository.save(updateCustomer);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer Data is not correct", e);
		}
		return updateCustomer;
	}

	// @RequestMapping(value="/getcustomer", produces="application/json",
	// method=RequestMethod.GET)
	// @RequestMapping(value="/getcustomer", method=RequestMethod.GET)
	// @GetMapping(value="/getcustomer")
	@GetMapping
	// @ResponseBody
	// public ResponseEntity<Customer>
	// getCustomerDetails(@RequestParam(value="email") String email) {
	public Customer getCustomerDetails(@RequestParam(value = "email") String email) {

		Customer customer = customerRepository.findByEmail(email);
		// return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		if (customer == null) {
			// https://www.baeldung.com/exception-handling-for-rest-with-spring
			// Spring 5 introduced the ResponseStatusException class.
			// We can create an instance of it providing an HttpStatus and optionally a
			// reason and a cause:
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer Not Found");
		}
		return customer;

	}

	@DeleteMapping
	public ResponseEntity<Customer> deleteCustomer(@RequestParam(value = "email") String email) {
		Customer customer = customerRepository.findByEmail(email);
		HttpHeaders responseHeaders = new HttpHeaders();
		if (customer == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Customer Not Found with id: " + email + ", Hence No Action taken");
		} else {
			customerRepository.delete(customer);
			responseHeaders.set("MyResponseHeader", "Deleted the customer successfully");
		}
		return new ResponseEntity<Customer>(customer, responseHeaders, HttpStatus.OK);
		// return customer;
	}

	@GetMapping(value = "/getAllcustomers")
	public List<Customer> getAllCustomerDetails() {
		List<Customer> customers = (List<Customer>) customerRepository.findAll();
		return customers;
	}

}
