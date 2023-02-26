package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;

	public UserService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public <S extends User> S save(S entity) {
		return repository.save(entity);
	}

	public Optional<User> findById(Long id) {
		return repository.findById(id);
	}

	public Optional<User> findByName(String name) {
		return repository.findByName(name);
	}

	public Optional<User> findByEmail(String email) {
		return repository.findByEmail(email);
	}

	public boolean existsById(Long id) {
		return repository.existsById(id);
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}


}
