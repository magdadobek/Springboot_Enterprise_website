package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Long producer;
	private Long ingredient;
	private Long indication;
	private Long side_effect;
	private boolean prescription;
	private String usage;
	private double price;
	private String image;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(Long id, String name, Long producer, Long ingredient, Long indication, Long side_effect,
			boolean prescription, String usage, double price, String image) {
		super();
		this.id = id;
		this.name = name;
		this.producer = producer;
		this.ingredient = ingredient;
		this.indication = indication;
		this.side_effect = side_effect;
		this.prescription = prescription;
		this.usage = usage;
		this.price = price;
		this.image = image;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getProducer() {
		return producer;
	}

	public void setProducer(Long producer) {
		this.producer = producer;
	}

	public Long getIngredient() {
		return ingredient;
	}

	public void setIngredient(Long ingredient) {
		this.ingredient = ingredient;
	}

	public Long getIndication() {
		return indication;
	}

	public void setIndication(Long indication) {
		this.indication = indication;
	}

	public Long getSide_effect() {
		return side_effect;
	}

	public void setSide_effect(Long side_effect) {
		this.side_effect = side_effect;
	}

	public boolean isPrescription() {
		return prescription;
	}

	public void setPrescription(boolean prescription) {
		this.prescription = prescription;
	}

	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
