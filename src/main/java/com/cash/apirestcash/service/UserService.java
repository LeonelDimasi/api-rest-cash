package com.cash.apirestcash.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cash.apirestcash.controller.DTO.UserDTO;
import com.cash.apirestcash.exceptions.UserDuplicated;
import com.cash.apirestcash.exceptions.UserNotFound;
import com.cash.apirestcash.persistence.models.User;
import com.cash.apirestcash.persistence.repository.LoanRepository;
import com.cash.apirestcash.persistence.repository.UserRepository;
import com.cash.apirestcash.utils.Mhelper;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	LoanRepository loanRepository;

	public UserDTO getUserById(Long idUser) throws UserNotFound {
		Optional<User> user = userRepository.findById(idUser);
		if (user.isEmpty())  throw new UserNotFound();
		return this.convertToUserDTO(user.get());
	}

	public UserDTO deleteUserById(Long idUser) throws UserNotFound {
		Optional<User> user = userRepository.findById(idUser);
		if (user.isEmpty())  throw new UserNotFound();
		userRepository.deleteById(user.get().getId());
		return this.convertToUserDTO(user.get());
	}

	public UserDTO insertUser(UserDTO user) throws UserDuplicated {
		Optional<User> _user = userRepository.findByFirstNameAndLastName(user.getFirstName(),user.getLastName());
		if (_user.isPresent()) 
			throw new UserDuplicated();
		return this.convertToUserDTO(userRepository.save(this.convertToUser(user)));
	}

	public List<UserDTO> getUsers() {
		List<User> users = userRepository.findAll();
		List<UserDTO> usersOut = new ArrayList<UserDTO>(); 
		for(User user : users) {
			usersOut.add(this.convertToUserDTO(user));
		}
		return usersOut;
	}

	
	public UserDTO convertToUserDTO(final User user) {
		return Mhelper.modelMapper().map(user, UserDTO.class);
	}
	
	public User convertToUser(final UserDTO user) {
		return Mhelper.modelMapper().map(user, User.class);
	}
}
