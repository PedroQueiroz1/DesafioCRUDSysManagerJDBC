package br.com.syscrud.management;

import java.util.Scanner;

import br.com.syscrud.dao.MovieDAO;
import br.com.syscrud.model.Movie;

public class MovieManager {

	public static void movieCreate(Scanner myTeclado, MovieDAO movieDAO) {
		Movie createMovie = new Movie();

		System.out.println("\nDigite o nome do filme a ser criado:");
		myTeclado.nextLine();
		createMovie.setName(myTeclado.nextLine());

		System.out.println("\nDigite o preço do " + createMovie.getName() + ":");
		createMovie.setPrice(myTeclado.nextDouble());

		System.out.println("\nDigite a quantidade de " + createMovie.getName() + ":");
		createMovie.setQuantity(myTeclado.nextInt());

		System.out.println("\nDigite a duração do " + createMovie.getName() + " em minutos:");
		createMovie.setDuration(myTeclado.nextInt());

		movieDAO.save(createMovie);
	}

	public static void movieRead(MovieDAO movieDAO) {
		System.out.println("\n\nLista de filmes:");
		for (Movie movie : movieDAO.findAll()) {
			System.out.println();
			System.out.println("ID: " + movie.getId());
			System.out.println("Nome: " + movie.getName());
			System.out.println("Preço: R$" + movie.getPrice());
			System.out.println("Quantidade: " + movie.getQuantity());
			System.out.println("Duração: " + movie.getDuration() + " minutos");
		}
	}

	public static void movieUpdate(Scanner myTeclado, MovieDAO movieDAO) {
		
		System.out.println("\nDigite o ID do filme a ser alterado:");
		int movieId = myTeclado.nextInt();
		myTeclado.nextLine();
		
	    Movie movie = movieDAO.findById(movieId);
	    if (movie == null) {
	        System.out.println("Filme não encontrado.");
	        return;
	    }

	    System.out.println("\nDigite o novo nome do filme. Nome atual: " + movie.getName());
	    String newName = myTeclado.nextLine();
	    movie.setName(newName);

	    System.out.println("\nDigite o novo preço do " + movie.getName() + ". Preço atual: R$" + movie.getPrice());
	    double newPrice = myTeclado.nextDouble();
	    movie.setPrice(newPrice);

	    System.out.println("\nDigite a nova quantidade do " + movie.getName() + ". Quantidade atual: " + movie.getQuantity());
	    int newQuantity = myTeclado.nextInt();
	    movie.setQuantity(newQuantity);

	    System.out.println("\nDigite a nova duração do " + movie.getName() + ". Duração atual: " + movie.getDuration());
	    int newDuration = myTeclado.nextInt();
	    movie.setDuration(newDuration);
	    
		movieDAO.update(movie);
	}

	  public static void movieDelete(Scanner myTeclado, MovieDAO movieDAO) {
	        System.out.println("\nDigite o ID do filme a ser deletado.");
	        movieDAO.deleteById(myTeclado.nextInt());
	    }
}
