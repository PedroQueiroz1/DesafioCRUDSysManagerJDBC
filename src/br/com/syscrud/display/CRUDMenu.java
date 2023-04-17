package br.com.syscrud.display;

import java.util.Scanner;

public class CRUDMenu {

	public static int displayCRUDMenu(Scanner myTeclado) {
		System.out.println("\nO que você gostaria de fazer? Digite o número correspondente.");
		System.out.println(" Criar [1]\n Mostrar Todos [2]\n Atualizar [3]\n Deletar [4]\n Voltar [5]");
		int crudResponse = myTeclado.nextInt();
		return crudResponse;
	}
	
	public static int displaySectorMenu(Scanner myTeclado) {
		System.out.println("Escolha um setor. Digite o número correspondente");
		System.out.println(" Produto [1]\n Autor [2]\n Análise [3]\n Sair [4]");
		int sectorResponse = myTeclado.nextInt();
		return sectorResponse;
	}
	
	public static int displayCategoryMenu(Scanner myTeclado) {
	System.out.println("Selecione a categoria de produto que você quer acessar.");
	System.out.println(" Filme [1]\n Livro[2]\n Voltar [3]");
	int category = myTeclado.nextInt();
	return category;
	}
	
	public static int promptForAnotherOperation(Scanner myTeclado) {
		System.out.println("\nDeseja realizar outra operação? (Sim [1] / Não [0])");
		int continueResponse = myTeclado.nextInt();
		return continueResponse;
	}
}
