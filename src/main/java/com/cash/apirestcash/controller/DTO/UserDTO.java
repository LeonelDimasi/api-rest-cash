package com.cash.apirestcash.controller.DTO;

import java.util.List;

public class UserDTO {
	
	private Long id;
	private String email;
	private String firstName;
	private String lastName;
	private List<LoanDTO> loans;
	
	
	
	public UserDTO() {
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
	public List<LoanDTO> getLoans() {
		return loans;
	}
	public void setLoans(List<LoanDTO> list) {
		this.loans = list;
	} 

	
}
