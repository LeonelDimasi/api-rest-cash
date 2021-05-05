package com.cash.apirestcash.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cash.apirestcash.controller.DTO.LoanOut;
import com.cash.apirestcash.controller.DTO.UserOut;
import com.cash.apirestcash.exceptions.UserDuplicated;
import com.cash.apirestcash.exceptions.UserNotFound;
import com.cash.apirestcash.persistence.models.Loan;
import com.cash.apirestcash.persistence.models.User;
import com.cash.apirestcash.persistence.repository.LoanRepository;
import com.cash.apirestcash.persistence.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	LoanRepository loanRepository;

	public UserOut getUserById(Long idUser) throws UserNotFound {
		Optional<User> user = userRepository.findById(idUser);

		if (user.isEmpty())  throw new UserNotFound();

		UserOut userOut = new UserOut();
		userOut.setEmail(user.get().getEmail());
		userOut.setId(user.get().getId());
		userOut.setFirstName(user.get().getFirstName());
		userOut.setLastName(user.get().getLastName());

		List<LoanOut> loans = new ArrayList<LoanOut>();
		for (Loan u :user.get().getLoans()) {
			LoanOut loanOut = new LoanOut();
			loanOut.id = u.getId();
			loanOut.total = u.getTotal();
			loanOut.userId = u.getUser().getId();
			loans.add(loanOut);
		}
		userOut.setLoans(loans);

		return userOut;
	}

	public UserOut deleteUserById(Long idUser) throws UserNotFound {
		Optional<User> user = userRepository.findById(idUser);
		if (user.isEmpty())  throw new UserNotFound();
		userRepository.deleteById(user.get().getId());

		UserOut userOut = new UserOut();
		userOut.setId(user.get().getId());
		userOut.setEmail(user.get().getEmail());
		userOut.setFirstName(user.get().getFirstName());
		userOut.setLastName(user.get().getLastName());
		List<LoanOut> loans = new ArrayList<LoanOut>();
		for (Loan u :user.get().getLoans()) {
			LoanOut loanOut = new LoanOut();
			loanOut.id = u.getId();
			loanOut.total = u.getTotal();
			loanOut.userId = u.getUser().getId();
			loans.add(loanOut);
		}

		userOut.setLoans(loans);

		return userOut;
	}

	public User insertUser(User user) throws UserDuplicated {
		Optional<User> _user = userRepository.findByFirstNameAndLastName(user.getFirstName(),user.getLastName());
		if (_user.isPresent()) {
			throw new UserDuplicated();
		}
		User userSaved = userRepository.save(user);
		return userSaved;
	}

	public List<UserOut> getUsers() {
		List<User> users = userRepository.findAll();
		List<UserOut> usersOut = new ArrayList<UserOut>(); 
		for(User user : users) {

			UserOut userOut = new UserOut();
			userOut.setEmail(user.getEmail());
			userOut.setId(user.getId());
			userOut.setFirstName(user.getFirstName());
			userOut.setLastName(user.getLastName());

			List<LoanOut> loans = new ArrayList<LoanOut>();
			for (Loan u :user.getLoans()) {

				LoanOut loanOut = new LoanOut();
				loanOut.id = u.getId();
				loanOut.total = u.getTotal();
				loanOut.userId = u.getUser().getId();
				loans.add(loanOut);
			}

			userOut.setLoans(loans);
			usersOut.add(userOut);
		}
		return usersOut;
	}

}
