package com.cash.apirestcash.persistence.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cash.apirestcash.persistence.models.Loan;

@Repository
public interface LoanRepository  extends JpaRepository<Loan,Long>  {

	Page<Loan> findByUserId(Long idUser, Pageable paging);

	Page<Loan> findAll(Pageable paging);
	
}
