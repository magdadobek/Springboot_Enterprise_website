package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Long product;
	private String userid;
///////////////////////
	public Cart(Long id, Long product, String user) {
		super();
		this.id = id;
		this.product = product;
		this.userid = user;
	}
	public Cart(Long product, String user) {
		super();
		this.product = product;
		this.userid = user;
	}
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProduct() {
		return product;
	}
	public void setProduct(Long product) {
		this.product = product;
	}
	public String getUserId() {
		return userid;
	}
	public void setUserId(String user) {
		this.userid = user;
	}
}
