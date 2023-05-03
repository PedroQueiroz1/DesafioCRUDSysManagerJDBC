package br.com.syscrud.model;

import java.io.Serializable;
import java.util.List;

//'Author' está no sentido de autor do comentário.
//Preferi 'Author' pois 'reviewer' ia me confundir...
public class Author implements Printable, Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	private String name;

	// 1..N
	private List<Review> reviews;

	public Author(String name) {
		this.name = name;
	}
	
	public Author(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}



	public Author() {}

	public Author(int id, String name, List<Review> reviews) {
		this.id = id;
		this.name = name;
		this.reviews = reviews;
	}

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
	
	
	@Override
	public void printDetails() {
		System.out.println("\nAutor - ");
		System.out.println("Id: " + id);
		System.out.println("Nome: " + name);
		System.out.println("\nComentários realizados: ");
		
		if (reviews != null && !reviews.isEmpty()) {
			for (Review review : reviews) {
				System.out.println("Produto: " + review.getProduct().getName());
				System.out.println("Comentário: " + review.getComment());
				System.out.println("Avaliação: " + review.getStars() + " estrela(s)");
				System.out.println("-----------------------------");
			}
		} else {
			System.out.println("Nenhum comentário disponível.\n");
		}
	}
	
	
}
