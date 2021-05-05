package com.cash.apirestcash.controller.DTO;

import java.util.List;

public class UserOut {
	
	private Long id;
	private String email;
	private String firstName;
	private String lastName;
	private List<LoanOut> loans;
	
	
	
	public UserOut() {
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public List<LoanOut> getLoans() {
		return loans;
	}
	public void setLoans(List<LoanOut> list) {
		this.loans = list;
	} 

}
