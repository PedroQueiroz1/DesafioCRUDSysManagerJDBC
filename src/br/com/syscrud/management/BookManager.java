package br.com.syscrud.management;

import java.util.Scanner;

import br.com.syscrud.dao.BookDAO;
import br.com.syscrud.model.Book;

public class BookManager {

	public static void bookCreate(Scanner myTeclado, BookDAO bookDAO) {
	    Book createBook = new Book();

	    System.out.println("\nDigite o nome do livro a ser criado:");
	    myTeclado.nextLine();
	    createBook.setName(myTeclado.nextLine());

	    System.out.println("\nDigite o preço do " + createBook.getName() + ":");
	    createBook.setPrice(myTeclado.nextDouble());

	    System.out.println("\nDigite a quantidade de " + createBook.getName() + ":");
	    createBook.setQuantity(myTeclado.nextInt());

	    bookDAO.save(createBook);
	}
	
	public static void bookRead(BookDAO bookDAO) {
	    System.out.println("\n\nLista de livros:");
	    for (Book book : bookDAO.findAll()) {
	        System.out.println();
	        System.out.println("ID: " + book.getId());
	        System.out.println("Nome: " + book.getName());
	        System.out.println("Preço: R$" + book.getPrice());
	        System.out.println("Quantidade: " + book.getQuantity());
	        System.out.println("Gênero: " + book.getGenre());
	    }
	}
	
	public static void bookUpdate(Scanner myTeclado, BookDAO bookDAO) {

	    System.out.println("\nDigite o ID do livro a ser alterado:");
	    int bookId = myTeclado.nextInt();
	    myTeclado.nextLine();

	    Book book = bookDAO.findById(bookId);
	    if (book == null) {
	        System.out.println("Livro não encontrado.");
	        return;
	    }

	    System.out.println("\nDigite o novo nome do livro. Nome atual: " + book.getName());
	    String newName = myTeclado.nextLine();
	    book.setName(newName);

	    System.out.println("\nDigite o novo preço do " + book.getName() + ". Preço atual: R$" + book.getPrice());
	    double newPrice = myTeclado.nextDouble();
	    book.setPrice(newPrice);

	    System.out.println("\nDigite a nova quantidade do " + book.getName() + ". Quantidade atual: " + book.getQuantity());
	    int newQuantity = myTeclado.nextInt();
	    book.setQuantity(newQuantity);

	    bookDAO.update(book);
	}
	
	public static void bookDelete(Scanner myTeclado, BookDAO bookDAO) {
	    System.out.println("\nDigite o ID do livro a ser deletado.");
	    bookDAO.deleteById(myTeclado.nextInt());
	}
}
