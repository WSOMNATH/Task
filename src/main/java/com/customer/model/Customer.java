package com.customer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
	public class Customer {

	    @Id
	    private Long id;
	    private String name;
	    private Double creditLimit;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Double getCreditLimit() {
			return creditLimit;
		}
		public void setCreditLimit(Double creditLimit) {
			this.creditLimit = creditLimit;
		}
		public Customer(Long id, String name, Double creditLimit) {
			super();
			this.id = id;
			this.name = name;
			this.creditLimit = creditLimit;
		}
		public Customer() {
			super();
			// TODO Auto-generated constructor stub
		}
	    

	}

	
	

