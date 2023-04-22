package org.jsp.userbootapp.controller;

import java.util.Optional;

import org.jsp.userbootapp.dto.ResponseStructure;
import org.jsp.userbootapp.dto.User;
import org.jsp.userbootapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sun.tools.javac.util.List;

@RestController
public class UserController {
	
	@Autowired
private UserService service;
	
	@PostMapping("/user")
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user) {
		return service.saveUser(user);
	}
	
	@PutMapping("/user")
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User user) {
		return service.updateUser(user);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<ResponseStructure<User>> findUserById(@PathVariable int id) {
		return service.findUserById(id);
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteUser(@PathVariable int id) {
		return service.delete(id);
		
	}
	@GetMapping("/user")
	public ResponseEntity<ResponseStructure<java.util.List<User>>> findAll(){
		return service.findAll();
	}

}
