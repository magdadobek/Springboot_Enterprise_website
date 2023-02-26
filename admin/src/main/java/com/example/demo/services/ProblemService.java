package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Problem;
import com.example.demo.repository.ProblemRepository;

@Service
public class ProblemService {
	@Autowired
	ProblemRepository repository;

	public ProblemService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public <S extends Problem> S save(S entity) {
		return repository.save(entity);
	}

	public List<Problem> findAll() {
		return repository.findAll();
	}

	public Optional<Problem> findById(Long id) {
		return repository.findById(id);
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}

}
