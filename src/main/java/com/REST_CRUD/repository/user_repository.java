package com.REST_CRUD.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.REST_CRUD.model.User;

@Repository
public interface user_repository extends JpaRepository<User, Integer> {

}
