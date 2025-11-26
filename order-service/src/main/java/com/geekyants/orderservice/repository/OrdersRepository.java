package com.geekyants.orderservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.geekyants.orderservice.entity.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, UUID> {

}
