package com.cash.apirestcash;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.cash.apirestcash.controller.UserController;
import com.cash.apirestcash.controller.DTO.UserDTO;

@SpringBootTest
class ApiRestCashApplicationTests {

	@Test
	void getUsers() {
		UserController userController = new UserController();
		ResponseEntity<List<UserDTO>> response = userController.getUsers();
		//assertEquals(,response);
	}

}
