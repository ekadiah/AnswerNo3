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

import com.REST_CRUD.model.NR_Cluster;
import com.REST_CRUD.repository.NRCluster_repository;

@RestController
public class NRCluster_Resource {
	@Autowired
	private NRCluster_repository N_r;
	
	@RequestMapping (value = "/NRClusters") 
	public List<NR_Cluster> getAll() {
		return N_r.findAll();
	}
	
	@GetMapping ("/NRClusters/{id}")
	public NR_Cluster getClusterById (@PathVariable int id) throws UserPrincipalNotFoundException {
		Optional<NR_Cluster> cluster = N_r.findById(id);
		 
		if(!cluster.isPresent())
			throw new UserPrincipalNotFoundException("id-" + id);
		return cluster.get();
		
	}
	
	@DeleteMapping("/NRClusters/{id}")
	public void deleteCluster(@PathVariable int id) {
		N_r.deleteById(id);
	}
	
	@PostMapping("/NRClusters/{id}")
	public ResponseEntity<Object> createCluster (@RequestBody NR_Cluster cluster){
		NR_Cluster saveCluster = N_r.save(cluster);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(saveCluster.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/NRClusters/{id}")
	public ResponseEntity<Object> updateCluster(@RequestBody NR_Cluster cluster, @PathVariable int id){
		Optional<NR_Cluster> clusterOptional = N_r.findById(id);
		
		if (!clusterOptional.isPresent())
			return ResponseEntity.notFound().build();

		cluster.setId(id);
		
		N_r.save(cluster);

		return ResponseEntity.noContent().build();
	}
	
	
}
