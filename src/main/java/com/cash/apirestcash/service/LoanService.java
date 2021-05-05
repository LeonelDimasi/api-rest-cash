package com.cash.apirestcash.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.cash.apirestcash.persistence.models.Loan;
import com.cash.apirestcash.persistence.repository.LoanRepository;


@Service
public class LoanService {
	@Autowired
	LoanRepository loanRepository;
	
	public Page<Loan> findAll(Pageable paging) {
		return loanRepository.findAll(paging);
	}

	public Page<Loan> findByUserContaining(Long userId, Pageable paging) {
		return loanRepository.findByUserId(userId,paging);
	}
}
