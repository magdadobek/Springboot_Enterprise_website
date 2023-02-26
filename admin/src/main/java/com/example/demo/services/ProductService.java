package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	public ProductService() {
		super();
	}

	public List<Product> findAll() {
		return repository.findAll();
	}

	public Optional<Product> findById(Long id) {
		return repository.findById(id);
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
	public void deleteByIngredient(Long id) {
		repository.deleteByIngredient(id);
	}

	public List<Product> findByName(String name) {
		return repository.findByNameContainingIgnoreCase(name);
	}

	public List<Product> findByIngredient(Long id) {
		return repository.findByIngredientNot(id);
	}

	public List<Product> findById(List<Long> productIds) {
		  return repository.findAllById(productIds);
		}

	public List<Product> findByIndication(Long id) {
		return repository.findByIndication(id);
	}

	public <S extends Product> S save(S entity) {
		return repository.save(entity);
	}

}
