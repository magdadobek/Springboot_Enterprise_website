package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cart;
import com.example.demo.repository.CartRepository;

@Service
public class CartService {
	@Autowired
	CartRepository repository;

	public CartService() {
		// TODO Auto-generated constructor stub
	}

	public List<Cart> findAll() {
		return repository.findAll();
	}

	public <S extends Cart> S save(S entity) {
		return repository.save(entity);
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	public void deleteAll() {
		repository.deleteAll();
	}

	public List<Cart> findByUserId(String userid) {
		return repository.findByUserid(userid);
	}

	public void delete(Cart entity) {
		repository.delete(entity);
	}

	public void deleteByProduct(Long product) {
		repository.deleteByProduct(product);
	}
	
	
	  
}
