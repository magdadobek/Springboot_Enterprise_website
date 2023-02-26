package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Producer;

public interface ProducerRepository extends JpaRepository<Producer, Long> {

}
