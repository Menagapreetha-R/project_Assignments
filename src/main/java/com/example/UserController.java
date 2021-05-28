package com.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	@Autowired
	UserRepository repo;
	@PostMapping("/user/add")
	public User addUser(@RequestBody User user) {
		return repo.save(user);
		
	}
	@GetMapping("/user/all")
	public List<User> getUsers() {
		Iterator<User> iterator= repo.findAll().iterator();
		List<User> users=new ArrayList<User>();
		while(iterator.hasNext()) {
			users.add(iterator.next());
		}
		
		return users;
		
	}
	@GetMapping("/user/{id}")
	public  Optional<User>  getUser(@PathVariable Integer id) {
		return repo.findById(id);
		
	}
	@DeleteMapping("/user/{id}")
	public  String  deleteUser(@PathVariable Integer id) {
		repo.deleteById(id);
		return "Document Deleted";
		
	}




}
