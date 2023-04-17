package br.com.syscrud.management;

import java.util.Scanner;

import br.com.syscrud.dao.AuthorDAO;
import br.com.syscrud.dao.ProductDAO;
import br.com.syscrud.dao.ReviewDAO;
import br.com.syscrud.model.Author;
import br.com.syscrud.model.Product;
import br.com.syscrud.model.Review;

public class ReviewManager {

	public static void reviewCreate(Scanner myTeclado, ReviewDAO reviewDAO, AuthorDAO authorDAO,
			ProductDAO productDAO) {
		Review createReview = new Review();
		System.out.println("\nDigite o nome do produto a ser analisado");
		myTeclado.nextLine();
		String productName = myTeclado.nextLine();
		System.out.println("\nDigite a quantidade de estrelas para a análise desse produto");
		createReview.setStars(myTeclado.nextInt());
		System.out.println("\nDigite o comentário");
		myTeclado.nextLine();
		createReview.setComment(myTeclado.nextLine());
		System.out.println("\nDigite o nome do autor dessa análise");
		String authorName = myTeclado.nextLine();

		Product product = productDAO.findByName(productName);
		if (product == null) {
			System.out.println("Produto não encontrado. Escolha um produto válido");
			return;
		}

		Author author = authorDAO.findByName(authorName);
		if (author == null) {
			System.out.println("Autor não reconhecido. Escolha um autor válido.");

		}

		createReview.setProduct(product);
		createReview.setReviewer(author);

		reviewDAO.save(createReview);
	}

	public static void reviewRead(ReviewDAO reviewDAO) {
		System.out.println("\n\nLista de reviews:");
		for (Review r : reviewDAO.findAll()) {
			System.out.println();
			System.out.println("ID: " + r.getId());
			System.out.println("Estrelas: " + r.getStars());
			System.out.println("Comentário: " + r.getComment());
			System.out.println("Autor: " + r.getReviewer().getName());
			System.out.println("Produto: " + r.getProduct().getName());
		}
	}

	public static void reviewUpdate(Scanner myTeclado, ReviewDAO reviewDAO) {
	    System.out.println("\nDigite o ID da review a ser alterada:");
	    int reviewId = myTeclado.nextInt();
	    myTeclado.nextLine();

	    Review review = reviewDAO.findById(reviewId);
	    if (review == null) {
	        System.out.println("Review não encontrada.");
	        return;
	    }

	    System.out.println("\nDigite a nova quantidade de estrelas. Quantidade atual: " + review.getStars());
	    int newStars = myTeclado.nextInt();
	    review.setStars(newStars);

	    System.out.println("\nDigite o novo comentário. Comentário atual: " + review.getComment());
	    myTeclado.nextLine();
	    String newComment = myTeclado.nextLine();
	    review.setComment(newComment);

	    reviewDAO.update(review);
	}


	public static void reviewDelete(Scanner myTeclado, ReviewDAO reviewDAO) {
		System.out.println("\nDigite o ID da review a ser deletada.");
		reviewDAO.deleteById(myTeclado.nextInt());
	}
}
