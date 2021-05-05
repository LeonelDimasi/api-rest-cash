package com.cash.apirestcash.persistence.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cash.apirestcash.persistence.models.User;

@Repository
public interface UserRepository  extends JpaRepository<User,Long>{

	Optional<User> findByFirstNameAndLastName(String firstName, String lastName);
	
}
