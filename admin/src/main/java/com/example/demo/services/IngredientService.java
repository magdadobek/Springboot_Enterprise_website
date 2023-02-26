package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Ingredient;
import com.example.demo.repository.IngredientRepository;

@Service
public class IngredientService {
	@Autowired
	IngredientRepository repository;

	public IngredientService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public <S extends Ingredient> S save(S entity) {
		return repository.save(entity);
	}

	public List<Ingredient> findAll() {
		return repository.findAll();
	}

	public Optional<Ingredient> findById(Long id) {
		return repository.findById(id);
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}


}
