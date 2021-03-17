package com.REST_CRUD.resource;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;
import java.net.URI;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.REST_CRUD.model.User;
import com.REST_CRUD.repository.user_repository;


@RestController
public class User_Resource {
	@Autowired
	private user_repository u_r;
	
	@RequestMapping (value = "/User") 
	public List<User> getAll() {
		return u_r.findAll();
	}
	
	@GetMapping ("/User/{id}")
	public User getStudentByIdUser (@PathVariable int id) throws UserPrincipalNotFoundException {
		Optional<User> user = u_r.findById(id);
		 
		if(!user.isPresent())
			throw new UserPrincipalNotFoundException("id-" + id);
		return user.get();
		
	}
	
	@DeleteMapping("/User/{id}")
	public void deleteUser(@PathVariable int id) {
		u_r.deleteById(id);
	}
	
	@PostMapping("/User/{id}")
	public ResponseEntity<Object> createUser(@RequestBody User user){
		User saveUser = u_r.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(saveUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/User/{id}")
	public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable int id){
		Optional<User> userOptional = u_r.findById(id);
		
		if (!userOptional.isPresent())
			return ResponseEntity.notFound().build();

		user.setId(id);
		
		u_r.save(user);

		return ResponseEntity.noContent().build();
	}
	
	

}
