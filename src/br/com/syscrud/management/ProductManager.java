package br.com.syscrud.management;

import br.com.syscrud.dao.ProductDAO;

public class ProductManager {

	public static void productRead(ProductDAO productDAO) {
		System.out.println("\n\nLista de produtos:");
		productDAO.findAll();
	}
}
