package br.com.syscrud.management;

import java.util.Scanner;

import br.com.syscrud.dao.AuthorDAO;
import br.com.syscrud.model.Author;

public class AuthorManager {
	
	public static void authorCreate(Scanner myTeclado, AuthorDAO authorDAO) {
		Author createAuthor = new Author();
		System.out.println("\nDigite o nome do autor a ser adicionado");
		myTeclado.nextLine();
		createAuthor.setName(myTeclado.nextLine());

		authorDAO.save(createAuthor);
	}

	public static void authorRead(AuthorDAO authorDAO) {
		System.out.println("\n\nLista de autores:");
		for (Author a : authorDAO.findAll()) {
			System.out.println();
			System.out.println("ID: " + a.getId());
			System.out.println("Nome: " + a.getName());
		}
	}

	public static void authorUpdate(Scanner myTeclado, AuthorDAO authorDAO) {
		Author updateAuthor = new Author();

		System.out.println("\nDigite o ID do autor a ser alterado");
		updateAuthor.setId(myTeclado.nextInt());

		System.out.println(
				"\nDigite o novo nome do autor. Nome atual - " + authorDAO.findById(updateAuthor.getId()).getName());
		updateAuthor.setName(myTeclado.next());

		authorDAO.update(updateAuthor);
	}

	public static void authorDelete(Scanner myTeclado, AuthorDAO authorDAO) {
		System.out.println("\nDigite o ID do autor a ser deletado.");
		authorDAO.deleteById(myTeclado.nextInt());
	}

}
