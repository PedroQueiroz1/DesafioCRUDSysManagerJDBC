package br.com.syscrud.management;

import java.sql.SQLException;
import java.util.Scanner;

import br.com.syscrud.dao.ProductDAO;

public class ProductManager {

	
	public static void productRead(ProductDAO productDAO) throws Exception {
		System.out.println("\n\nLista de TODOS os produtos já criados:");
		productDAO.findAll();
	}
	
	public static void bookDelete(Scanner myTeclado, ProductDAO productDAO) throws SQLException, Exception {
	    System.out.println("\nDigite o ID do livro a ser deletado.");
	    productDAO.deleteById(myTeclado.nextInt());
	}
}
