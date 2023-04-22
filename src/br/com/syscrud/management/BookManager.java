package br.com.syscrud.management;

import java.sql.SQLException;
import java.util.Scanner;

import br.com.syscrud.converter.DoubleConverter;
import br.com.syscrud.dao.BookDAO;
import br.com.syscrud.model.Book;

public class BookManager {

	public static void bookCreate(Scanner myTeclado, BookDAO bookDAO) throws SQLException, Exception {
	    Book createBook = new Book();

	    System.out.println("\nDigite o nome do livro a ser criado:");
	    myTeclado.nextLine();
	    createBook.setName(myTeclado.nextLine());

	    System.out.println("\nDigite o preço do " + createBook.getName() + ":");
	    String price = myTeclado.nextLine();
		double priceDouble = DoubleConverter.parseDouble(price);
		createBook.setPrice(priceDouble);

	    System.out.println("\nDigite a quantidade do livro " + createBook.getName() + ":");
	    createBook.setQuantity(myTeclado.nextInt());
	    
	    System.out.println("\nDigite o gênero do livro " + createBook.getName() + ":");
	    myTeclado.nextLine();
	    createBook.setGenre(myTeclado.nextLine());

	    bookDAO.save(createBook);
	}
	
	public static void bookRead(BookDAO bookDAO) throws SQLException, Exception {
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
	
	public static void bookUpdate(Scanner myTeclado, BookDAO bookDAO) throws SQLException, Exception {

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
	    String newPrice = myTeclado.nextLine();
		double newPriceDouble = DoubleConverter.parseDouble(newPrice);
		book.setPrice(newPriceDouble);

	    System.out.println("\nDigite a nova quantidade do " + book.getName() + ". Quantidade atual: " + book.getQuantity());
	    int newQuantity = myTeclado.nextInt();
	    book.setQuantity(newQuantity);
	    
	    System.out.println("\nDigite o novo gênero do " + book.getName() + ". Gênero atual: " + book.getGenre());
	    myTeclado.nextLine();
	    String newGenre = myTeclado.nextLine();
	    book.setGenre(newGenre);

	    bookDAO.update(book);
	}
	

}
