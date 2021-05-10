package com.cash.apirestcash.controller;


import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.cash.apirestcash.controller.DTO.LoanDTO;
import com.cash.apirestcash.controller.DTO.LoansFilter;
import com.cash.apirestcash.controller.DTO.Paging;
import com.cash.apirestcash.persistence.repository.UserRepository;
import com.cash.apirestcash.service.LoanService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController()
@RequestMapping("/loans")
public class LoanController {

	public static final  Logger LOGGER  = LoggerFactory.getLogger(LoanController.class);

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
			Pageable paging = PageRequest.of(page-1, size);
			List<LoanDTO> loansOut;
			if (userId == null) {
				loansOut = loanService.findAll(paging);
			}
			else {
				loansOut = loanService.findByUserContaining(userId, paging);
			}
			Paging pagingOut = new Paging();
			pagingOut.setPage(page); 
			pagingOut.setSize(size);
			pagingOut.setTotal(loansOut.size()); 
			return new ResponseEntity<LoansFilter>(new LoansFilter(loansOut,pagingOut),HttpStatus.OK);
		}catch(Exception e) {
			LOGGER.error("Internal Server Error: ",e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
