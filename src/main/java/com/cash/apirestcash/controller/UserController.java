package com.cash.apirestcash.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.cash.apirestcash.controller.DTO.UserInput;
import com.cash.apirestcash.controller.DTO.UserOut;
import com.cash.apirestcash.exceptions.UserDuplicated;
import com.cash.apirestcash.exceptions.UserNotFound;
import com.cash.apirestcash.persistence.models.User;
import com.cash.apirestcash.service.UserService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController()
@RequestMapping("/users")
public class UserController {

	public static Logger Logger  = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@GetMapping("/")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 404, message = "User Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public @ResponseBody ResponseEntity<List<UserOut>> getUsers(){
		try {	
			return new ResponseEntity<List<UserOut>>(userService.getUsers(),HttpStatus.OK);
		}catch(Exception e) {
			Logger.error("Internal Server Error: ",e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 404, message = "User Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public @ResponseBody ResponseEntity<UserOut> getUserById(@PathVariable("id") Long idUser ){
		try {	
			return new ResponseEntity<UserOut>(userService.getUserById(idUser),HttpStatus.OK);
		}catch(UserNotFound e) {
			Logger.error("User Not Found: ",e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			Logger.error("Internal Server Error: ",e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@DeleteMapping(value = "/{id}")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 404, message = "User Not Found"),
			@ApiResponse(code = 404, message = "User Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public @ResponseBody ResponseEntity<UserOut> deleteUserById(@PathVariable("id") Long idUser ){
		try {	
			return new ResponseEntity<UserOut>(userService.deleteUserById(idUser),HttpStatus.OK);
		}catch(UserNotFound e) {
			Logger.error("User Not Found: ",e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			Logger.error("Internal Server Error: ",e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PostMapping("")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 406, message = "User duplicado"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public @ResponseBody ResponseEntity<User> insertUser(@RequestBody UserInput user ){
		try {	
			User user2 = new User();
			user2.setEmail(user.getEmail());
			user2.setFirstName(user.getFirstName());
			user2.setLastName(user.getLastName());
			
			return new ResponseEntity<User>(userService.insertUser(user2),HttpStatus.OK);
		} catch(UserDuplicated e) {
			return new ResponseEntity<User>(HttpStatus.NOT_ACCEPTABLE);
		}catch(Exception e) {
			Logger.error("Internal Server Error: ",e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
