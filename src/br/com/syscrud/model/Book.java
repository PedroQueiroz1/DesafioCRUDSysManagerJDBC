package br.com.syscrud.model;

import java.io.Serializable;

//Livros
public class Book extends Product implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String genre;

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
}
