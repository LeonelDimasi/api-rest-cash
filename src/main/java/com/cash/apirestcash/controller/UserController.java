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
import com.cash.apirestcash.controller.DTO.UserDTO;
import com.cash.apirestcash.exceptions.UserDuplicated;
import com.cash.apirestcash.exceptions.UserNotFound;
import com.cash.apirestcash.service.UserService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController()
@RequestMapping("/users")
public class UserController {

	public static final Logger LOGGER  = LoggerFactory.getLogger(UserController.class);
	private static final String INTERNAL_SERVER_ERROR = "Internal Server Error: ";
	@Autowired
	UserService userService;
	
	@GetMapping("/")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public @ResponseBody ResponseEntity<List<UserDTO>> getUsers(){
		try {	
			return new ResponseEntity<List<UserDTO>>(userService.getUsers(),HttpStatus.OK);
		}catch(Exception e) {
			LOGGER.error(INTERNAL_SERVER_ERROR,e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 404, message = "User Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public @ResponseBody ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long idUser ){
		try {	
			return new ResponseEntity<UserDTO>(userService.getUserById(idUser),HttpStatus.OK);
		}catch(UserNotFound e) {
			LOGGER.error("User Not Found: ",e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			LOGGER.error(INTERNAL_SERVER_ERROR,e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@DeleteMapping(value = "/{id}")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 404, message = "User Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public @ResponseBody ResponseEntity<UserDTO> deleteUserById(@PathVariable("id") Long idUser ){
		try {	
			return new ResponseEntity<UserDTO>(userService.deleteUserById(idUser),HttpStatus.OK);
		}catch(UserNotFound e) {
			LOGGER.error("User Not Found: ",e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			LOGGER.error(INTERNAL_SERVER_ERROR,e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PostMapping("")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 406, message = "User duplicated"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public @ResponseBody ResponseEntity<UserDTO> insertUser(@RequestBody UserDTO user ){
		try {	
			return new ResponseEntity<UserDTO>(userService.insertUser(user),HttpStatus.OK);
		} catch(UserDuplicated e) {
			LOGGER.info("User duplicated: ",e);
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}catch(Exception e) {
			LOGGER.error(INTERNAL_SERVER_ERROR,e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
