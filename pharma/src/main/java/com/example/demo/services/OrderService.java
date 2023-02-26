package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
	OrderRepository repository;

	public OrderService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Optional<Order> findById(Long id) {
		return repository.findById(id);
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	public <S extends Order> S save(S entity) {
		return repository.save(entity);
	}

	public List<Order> findByUserid(String userid) {
		return repository.findByUserid(userid);
	}

	public List<Order> findAll() {
		return repository.findAll();
	}
	

}
