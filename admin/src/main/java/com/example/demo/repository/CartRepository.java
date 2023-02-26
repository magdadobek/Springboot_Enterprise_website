package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
	
	List<Cart> findByUserid(String userid);
	
	void deleteByProduct(Long product);
}
