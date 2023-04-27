package br.com.syscrud.model;

import java.io.Serializable;

//Filmes
public class Movie extends Product implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int duration;

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}	
	
}
