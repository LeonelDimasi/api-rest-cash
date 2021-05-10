package com.cash.apirestcash.controller.DTO;

import java.util.List;

public class LoansFilter {

	private List<LoanDTO> items;
	
	private Paging paging;
	
	public LoansFilter() {
	}
	
	public LoansFilter(List<LoanDTO> loans, Paging paging) {
		this.paging = paging;
		this.items = loans;
	}

	public List<LoanDTO> getItems() {
		return items;
	}

	public void setItems(List<LoanDTO> items) {
		this.items = items;
	}

	public Paging getPaging() {
		return paging;
	}

	public void setPaging(Paging paging) {
		this.paging = paging;
	}

}
