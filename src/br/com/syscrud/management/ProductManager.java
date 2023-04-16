package br.com.syscrud.management;

import java.util.Scanner;

import br.com.syscrud.dao.ProductDAO;
import br.com.syscrud.model.Product;

public class ProductManager {
	
	public static void productCreate(Scanner myTeclado, ProductDAO productDAO) {
		Product createProduct = new Product();
		System.out.println("\nDigite o nome do produto a ser criado");
		myTeclado.nextLine();
		createProduct.setName(myTeclado.nextLine());
		System.out.println("\nDigite o preço do " + createProduct.getName());
		createProduct.setPrice(myTeclado.nextDouble());
		System.out.println("\nDigite a quantidade de " + createProduct.getName());
		createProduct.setQuantity(myTeclado.nextInt());

		productDAO.save(createProduct);
	}

	public static void productRead(ProductDAO productDAO) {
		System.out.println("\n\nLista de produtos:");
		for (Product p : productDAO.findAll()) {
			System.out.println();
			System.out.println("ID: " + p.getId());
			System.out.println("Nome: " + p.getName());
			System.out.println("Preço: R$" + p.getPrice());
			System.out.println("Quantidade " + p.getQuantity());
		}
	}

	public static void productUpdate(Scanner myTeclado, ProductDAO productDAO) {
		Product updateProduct = new Product();

		System.out.println("\nDigite o ID do produto a ser alterado");
		updateProduct.setId(myTeclado.nextInt());

		System.out.println(
				"\nDigite o novo nome do produto. Nome atual - " + productDAO.findById(updateProduct.getId()).getName());
		updateProduct.setName(myTeclado.next());

		System.out.println("\nDigite o novo preço do " + updateProduct.getName() + ". Preço atual - R$"
				+ productDAO.findById(updateProduct.getId()).getPrice());
		updateProduct.setPrice(myTeclado.nextDouble());

		System.out.println("\nDigite a nova quantidade do " + updateProduct.getName() + ". Quantidade atual - "
				+ productDAO.findById(updateProduct.getId()).getQuantity());
		updateProduct.setQuantity(myTeclado.nextInt());

		productDAO.update(updateProduct);
	}

	public static void productDelete(Scanner myTeclado, ProductDAO productDAO) {
		System.out.println("\nDigite o ID do produto a ser deletado.");
		productDAO.deleteById(myTeclado.nextInt());
	}

}
