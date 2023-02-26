package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Producer;
import com.example.demo.repository.ProducerRepository;

@Service
public class ProducerService {
	@Autowired
	ProducerRepository repository;

	public ProducerService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public <S extends Producer> S save(S entity) {
		return repository.save(entity);
	}

	public Optional<Producer> findById(Long id) {
		return repository.findById(id);
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	public List<Producer> findAll() {
		return repository.findAll();
	}
	
	

}
