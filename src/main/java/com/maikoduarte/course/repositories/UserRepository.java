package com.maikoduarte.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maikoduarte.course.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
  
}
