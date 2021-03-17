package com.REST_CRUD.resource;

import java.net.URI;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

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


import com.REST_CRUD.model.User_NRCluster;
import com.REST_CRUD.repository.User_NRCluster_repository;

@RestController
public class UserCluster_Resource {
	@Autowired
	private User_NRCluster_repository UN_r;
	
	@RequestMapping (value = "/userClusters") 
	public List<User_NRCluster> getAll() {
		return UN_r.findAll();
	}
	
	@GetMapping ("/userClusters/{id}")
	public User_NRCluster getClusterById (@PathVariable int id) throws UserPrincipalNotFoundException {
		Optional<User_NRCluster> cluster = UN_r.findById(id);
		 
		if(!cluster.isPresent())
			throw new UserPrincipalNotFoundException("id-" + id);
		return cluster.get();
		
	}
	
	@DeleteMapping("/userClusters/{id}")
	public void deleteCluster(@PathVariable int id) {
		UN_r.deleteById(id);
	}
	
	@PostMapping("/userClusters/{id}")
	public ResponseEntity<Object> createUserCluster (@RequestBody User_NRCluster Usercluster){
		User_NRCluster saveUserCluster = UN_r.save(Usercluster);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(saveUserCluster.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/userClusters/{id}")
	public ResponseEntity<Object> updateUserCluster(@RequestBody User_NRCluster Usercluster, @PathVariable int id){
		Optional<User_NRCluster> UserclusterOptional = UN_r.findById(id);
		
		if (!UserclusterOptional.isPresent())
			return ResponseEntity.notFound().build();

		Usercluster.setId(id);
		
		UN_r.save(Usercluster);

		return ResponseEntity.noContent().build();
	}
	

}
