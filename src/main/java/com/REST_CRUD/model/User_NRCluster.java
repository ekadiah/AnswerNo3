package com.REST_CRUD.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User_NRCluster {
	@Id
	@GeneratedValue
	
	@Column(name="id")
	private int id;
	
	@Column(name="user_id")
	private int user_id;
	
	@Column(name="nr_cluster_id")
	private int nr_cluster_id;
	
	@Column(name="role")
	private String role;
	
	public User_NRCluster() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getNr_cluster_id() {
		return nr_cluster_id;
	}

	public void setNr_cluster_id(int nr_cluster_id) {
		this.nr_cluster_id = nr_cluster_id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	

}
