package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Problem {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;

	//////////////////////////////////
	public Problem(String name) {
		super();
		this.name = name;
	}
	public Problem(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Problem() {
		super();
		// TODO Auto-generated constructor stub
	}
	//////////////////////////////
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
