package com.maikoduarte.course.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import com.maikoduarte.course.entities.Category;


public interface CategoryRepository extends JpaRepository<Category, Long>{
  
  
}
