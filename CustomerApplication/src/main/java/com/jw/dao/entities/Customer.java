package com.jw.dao.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/*CREATE TABLE CUSTOMER
   (	"CUSTOMERID" VARCHAR2(45 BYTE) NOT NULL ENABLE, 
	"CUSTOMERNAME" VARCHAR2(40 BYTE) NOT NULL ENABLE, 
	"CONTACTTITLE" VARCHAR2(30 BYTE), 
	"ADDRESS" VARCHAR2(60 BYTE), 
	"CITY" VARCHAR2(15 BYTE), 
	"STATE" VARCHAR2(15 BYTE), 
	"POSTALCODE" VARCHAR2(10 BYTE), 
	"COUNTRY" VARCHAR2(15 BYTE), 
	"PHONE" VARCHAR2(24 BYTE), 
	"EMAIL" VARCHAR2(24 BYTE) NOT NULL ENABLE, 
	"FAX" VARCHAR2(24 BYTE),
    CONSTRAINT EMAIL_pk PRIMARY KEY (EMAIL)
   ) ;
 */
@Entity
@Table( name = "CUSTOMER" )
public class Customer {
	
//	@Id
//	@GeneratedValue(generator="increment")
//	@GenericGenerator(name="increment", strategy = "increment")
	@Id @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String customerID;   
	
	@Column(name = "CUSTOMERNAME")
	private String customerName; 
	
	@Column(name = "CONTACTTITLE")
	private String contactTitle; 
	
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "CITY")
	private String city;         
	
	@Column(name = "STATE")
	private String state;
	
	@Column(name = "POSTALCODE")
	private String postalCode;
	
	@Column(name = "COUNTRY")
	private String country;      
	
	@Column(name = "PHONE")
	private String phone;        
	
	@Column(name = "EMAIL")
	private String email;        
	
	@Column(name = "FAX")
	private String fax;

	
	protected Customer() {
		
	}

	public Customer(String customerID, String customerName, String contactTitle, String address, String city,
			String state, String postalCode, String country, String phone, String email, String fax) {
		this.customerID = customerID;
		this.customerName = customerName;
		this.contactTitle = contactTitle;
		this.address = address;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		this.country = country;
		this.phone = phone;
		this.email = email;
		this.fax = fax;
	}

	
	
	public String getCustomerID() {
		return customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getContactTitle() {
		return contactTitle;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getCountry() {
		return country;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public String getFax() {
		return fax;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setContactTitle(String contactTitle) {
		this.contactTitle = contactTitle;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	/*
	 * public void setEmail(String email) { this.email = email; }
	 */

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", customerName=" + customerName + ", contactTitle="
				+ contactTitle + ", address=" + address + ", city=" + city + ", state=" + state + ", postalCode="
				+ postalCode + ", country=" + country + ", phone=" + phone + ", email=" + email + ", fax=" + fax + "]";
	}
	
	

	
}
