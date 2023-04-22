package br.com.syscrud.model;

//São os comentários.
//Comentários são feitos tanto em filmes quanto em livros.
//Os comentários são feitos pelos autores
public class Review {

	private int id;
	
	private int stars;
	
	private String comment;
	
	private Author reviewer;
	
	private Product product;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public Author getReviewer() {
		return reviewer;
	}

	public void setReviewer(Author reviewer) {
		this.reviewer = reviewer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
