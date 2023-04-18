package br.com.syscrud.display;

import java.util.Scanner;

public class CRUDMenu {

	public static int displayCRUDMenu(Scanner myTeclado) {
	    System.out.println("\nO que você gostaria de fazer? Digite o número correspondente:\n"
	    		+ "1. Criar\n2. Mostrar Todos\n3. Atualizar\n4. Deletar\n5. Voltar");
	    int crudResponse = myTeclado.nextInt();
	    return crudResponse;
	}
	
	public static int displayCRUDMenuProduct(Scanner myTeclado) {
	    System.out.println("\nO que você gostaria de fazer? Digite o número correspondente:\n"
	    		+ "1. Mostrar Todos\n2. Voltar");
	    int crudResponse = myTeclado.nextInt();
	    return crudResponse;
	}
	
	public static int displaySectorMenu(Scanner myTeclado) {
	    System.out.println("Escolha um setor. Digite o número correspondente:\n"
	    		+ "1. Produto\n2. Autor\n3. Análise\n4. Sair");
	    int sectorResponse = myTeclado.nextInt();
	    return sectorResponse;
	}
	
	public static int displayCategoryMenu(Scanner myTeclado) {
	    System.out.println("Selecione a categoria de produto que você quer acessar:\n"
	    		+ "1. Filme\n2. Livro\n3. Produtos - Informações gerais\n4. Voltar");
	    int category = myTeclado.nextInt();
	    return category;
	}
	
	public static int promptForAnotherOperation(Scanner myTeclado) {
	    System.out.println("\nDeseja realizar outra operação? (Sim [1] / Não [0])");
	    int continueResponse = myTeclado.nextInt();
	    return continueResponse;
	}
}
