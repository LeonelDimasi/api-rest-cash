package com.cash.apirestcash.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.cash.apirestcash.controller.DTO.LoanDTO;
import com.cash.apirestcash.persistence.models.Loan;
import com.cash.apirestcash.persistence.repository.LoanRepository;
import com.cash.apirestcash.utils.Mhelper;


@Service
public class LoanService {
	@Autowired
	LoanRepository loanRepository;
	
	public List<LoanDTO> findAll(Pageable paging) {
		Page<Loan> loans = loanRepository.findAll(paging);
		return loans.stream().map(this::convertToLoanDTO).collect(Collectors.toList());
	}

	public List<LoanDTO> findByUserContaining(Long userId, Pageable paging) {
		Page<Loan> loans = loanRepository.findByUserId(userId,paging);
		return loans.stream().map(this::convertToLoanDTO).collect(Collectors.toList());
	}
	
	public LoanDTO convertToLoanDTO(final Loan loan) {
		return Mhelper.modelMapper().map(loan, LoanDTO.class);
	}
}
