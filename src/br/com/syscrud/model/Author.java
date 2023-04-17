package br.com.syscrud.model;

import java.util.List;

public class Author {
	
	private int id;
	
	private String name;
	
	// 1..N
	private List<Review> reviews;

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

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
}
