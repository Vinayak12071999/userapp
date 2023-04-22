package org.jsp.userbootapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.userbootapp.dao.UserDao;
import org.jsp.userbootapp.dto.ResponseStructure;
import org.jsp.userbootapp.dto.User;
import org.jsp.userbootapp.exception.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
@Autowired
private UserDao dao;

public ResponseEntity<ResponseStructure<User>> saveUser(User user) {
	ResponseStructure<User> structure=new ResponseStructure<User>();
	structure.setBody(dao.saveUser(user));
	structure.setMessage("user registered with Id :"+user.getId());
	structure.setCode(HttpStatus.ACCEPTED.value());
	return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.ACCEPTED);
}

public ResponseEntity<ResponseStructure<User>> updateUser(User user) {
	ResponseStructure<User> structure=new ResponseStructure<User>();
	structure.setBody(dao.updateUser(user));
	structure.setMessage("user updated successfully");
	structure.setCode(HttpStatus.ACCEPTED.value());
	return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.ACCEPTED) ;
}

public ResponseEntity<ResponseStructure<User>> findUserById(int id) {
	ResponseStructure<User> structure=new ResponseStructure<User>();
	Optional<User>  u=dao.findUserById(id);
	if(u.isPresent()) {
		structure.setBody(u.get());
		structure.setMessage("user found");
		structure.setCode(HttpStatus.FOUND.value());
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.FOUND);
	}
//	structure.setBody(null);
//	structure.setMessage("Invalid ID");
//	structure.setCode(HttpStatus.NOT_FOUND.value());
//	return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.NOT_FOUND);
	
	throw  new IdNotFoundException();
	
}

public ResponseEntity<ResponseStructure<String>> delete(int id) {
	ResponseStructure<String> structure=new ResponseStructure<String>();
	Optional<User>  u=dao.findUserById(id);
	if(u.isPresent()) {
		structure.setBody("User deleted");
		structure.setMessage("User found ");
		structure.setCode(HttpStatus.OK.value());
		dao.delete(u.get());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
	}
	else {
//	structure.setBody("User not found");
//	structure.setMessage("Invalid id");
//	structure.setCode(HttpStatus.NOT_FOUND.value());
//	return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
		throw new IdNotFoundException();
	}
}

public ResponseEntity<ResponseStructure<List<User>>> findAll(){
	ResponseStructure<List<User>> structure=new ResponseStructure<List<User>>();
	structure.setBody(dao.findAll());
	structure.setMessage("List of all users");
	structure.setCode(HttpStatus.OK.value());
	return new ResponseEntity<ResponseStructure<List<User>>>(structure, HttpStatus.OK);
}
}
