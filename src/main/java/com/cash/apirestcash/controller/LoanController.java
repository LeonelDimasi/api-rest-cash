package com.cash.apirestcash.controller;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.cash.apirestcash.controller.DTO.LoanOut;
import com.cash.apirestcash.controller.DTO.LoansFilter;
import com.cash.apirestcash.controller.DTO.Paging;
import com.cash.apirestcash.persistence.models.Loan;
import com.cash.apirestcash.persistence.repository.UserRepository;
import com.cash.apirestcash.service.LoanService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController()
@RequestMapping("/loans")
public class LoanController {

	public static Logger Logger  = LoggerFactory.getLogger(LoanController.class);


	@Autowired
	LoanService loanService;

	@Autowired
	UserRepository userRepository;

	@GetMapping(value = "")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Ok"),
		@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public @ResponseBody ResponseEntity<LoansFilter> getLoans(
			@RequestParam( required = true ) int page , 
			@RequestParam( required = true ) int size , 
			@RequestParam( required = false ) Long userId 
			){
		try {	
			List<Loan> loans = new ArrayList<Loan>();
			Pageable paging = PageRequest.of(page, size);
			Page<Loan> pageLoans;
			if (userId == null) {
				pageLoans = loanService.findAll(paging);
			}
			else {
				//Optional<User> user = userRepository.findById(userId);
				pageLoans = loanService.findByUserContaining(userId, paging);
			}
			loans = pageLoans.getContent();
			LoansFilter loansFilter = new LoansFilter();
			List<LoanOut> loansOut = new ArrayList<LoanOut>();
			for(Loan loan :loans) {
				LoanOut l = new  LoanOut();
				l.id = loan.getId();
				l.total = loan.getTotal();
				l.userId = loan.getUser().getId();
				loansOut.add(l);
			}
			loansFilter.items = loansOut;

			Paging pagingOut = new Paging();
			pagingOut.page = page;
			pagingOut.size = size;
			pagingOut.total = loansOut.size();
			loansFilter.paging = pagingOut;

			return new ResponseEntity<LoansFilter>(loansFilter,HttpStatus.OK);
		}catch(Exception e) {
			Logger.error("Internal Server Error: ",e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
