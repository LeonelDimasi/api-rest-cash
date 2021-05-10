package com.cash.apirestcash.controller.DTO;

public class Paging {

	private int page;
	
	private int size;
	
	private int total;

	public Paging() {
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	} 
	
}
