package br.com.syscrud.model;

import java.util.List;

public class Product implements Printable{
		
	private int id;
	
	private String name;
	
	private double price;

	private int quantity;
	
	//1..N
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public void printDetails() {
	    System.out.println("Produto - ");
	    System.out.println("Id: " + id);
	    System.out.println("Nome: " + name);
	    System.out.println("Preço: " + price);
	    System.out.println("Quantidade: " + quantity);
	    System.out.println("\nComentários: ");
	    
	    if (reviews != null && !reviews.isEmpty()) {
	        for (Review review : reviews) {
	            System.out.println("Autor: " + review.getReviewer().getName());
	            System.out.println("Comentário: " + review.getComment());
	            System.out.println("Avaliação: " + review.getStars() + " estrela(s)");
	            System.out.println("-----------------------------");
	        }
	    } else {
	        System.out.println("Nenhum comentário disponível.\n");
	    }
	}
	
}
