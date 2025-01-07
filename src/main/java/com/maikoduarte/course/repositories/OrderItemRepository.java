package com.maikoduarte.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maikoduarte.course.entities.OrderItem;
import com.maikoduarte.course.entities.pk.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

  
}
