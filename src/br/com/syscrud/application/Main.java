package br.com.syscrud.application;

import java.util.Scanner;

import br.com.syscrud.dao.AuthorDAO;
import br.com.syscrud.dao.BookDAO;
import br.com.syscrud.dao.MovieDAO;
import br.com.syscrud.dao.ProductDAO;
import br.com.syscrud.dao.ReviewDAO;
import br.com.syscrud.display.CRUDMenu;
import br.com.syscrud.management.AuthorManager;
import br.com.syscrud.management.BookManager;
import br.com.syscrud.management.MovieManager;
import br.com.syscrud.management.ReviewManager;

public class Main {

	public static void main(String[] args) {
		try (Scanner myTeclado = new Scanner(System.in)) {
			boolean mainMenu = true;

			while (mainMenu) {
				int sectorResponse = CRUDMenu.displaySectorMenu(myTeclado);

				if (sectorResponse == 1) {
					boolean categoryBoolean = false;

					do {
						int category = CRUDMenu.displayCategoryMenu(myTeclado);
						if (category == 1) {
							/*
							 * - - -FILME - - -
							 */
							MovieDAO movieDAO = new MovieDAO();
							boolean back = false;
							while (!back) {
								int crudResponse = CRUDMenu.displayCRUDMenu(myTeclado);
								boolean extraQuestion = true;

								if (crudResponse == 1) {
									MovieManager.movieCreate(myTeclado, movieDAO);
								} else if (crudResponse == 2) {
									MovieManager.movieRead(movieDAO);
								} else if (crudResponse == 3) {
									MovieManager.movieUpdate(myTeclado, movieDAO);
								} else if (crudResponse == 4) {
									MovieManager.movieDelete(myTeclado, movieDAO);
								} else if (crudResponse == 5) {
									System.out.println();
									back = true;
									categoryBoolean = true;
									extraQuestion = false;
								} else {
									System.out.println("Opção não reconhecida. Escolha uma opção válida.");
									continue;
								}
								if (extraQuestion) {
									boolean goodResponse = false;
									while (!goodResponse) {
										int continueResponse = CRUDMenu.promptForAnotherOperation(myTeclado);

										if (continueResponse == 0) {
											goodResponse = true;
											extraQuestion = false;
											back = true;
											mainMenu = false;
											categoryBoolean = false;
										} else if (continueResponse == 1) {
											goodResponse = true;
										} else {
											System.out.println("Opção não reconhecida. Escolha uma opção válida.");
										}
									}
								}
							}
						} else if (category == 2) {
							/*
							 *  - - - LIVRO - - -
							 */
							BookDAO bookDAO = new BookDAO();

							boolean back = false;
							while (!back) {

								int crudResponse = CRUDMenu.displayCRUDMenu(myTeclado);
								boolean extraQuestion = true;

								if (crudResponse == 1) {
									BookManager.bookCreate(myTeclado, bookDAO);
								} else if (crudResponse == 2) {
									BookManager.bookRead(bookDAO);
								} else if (crudResponse == 3) {
									BookManager.bookUpdate(myTeclado, bookDAO);
								} else if (crudResponse == 4) {
									BookManager.bookDelete(myTeclado, bookDAO);
								} else if (crudResponse == 5) {
									System.out.println();
									back = true;
									categoryBoolean = true;
									extraQuestion = false;
								} else {
									System.out.println("Opção não reconhecida. Escolha uma opção válida.");
									continue;
								}
								if (extraQuestion) {
									boolean goodResponse = false;
									while (!goodResponse) {
										int continueResponse = CRUDMenu.promptForAnotherOperation(myTeclado);

										if (continueResponse == 0) {
											goodResponse = true;
											extraQuestion = false;
											back = true;
											mainMenu = false;
											categoryBoolean = false;
										} else if (continueResponse == 1) {
											goodResponse = true;
										} else {
											System.out.println("Opção não reconhecida. Escolha uma opção válida.");
										}
									}
								}
							}
						} else if (category == 3) {
							System.out.println();
							categoryBoolean = true;
							break;
						} else {
							System.out.println("Opção não reconhecida. Escolha uma opção válida.");
							categoryBoolean = true;
						}
					} while (categoryBoolean);
				} else if (sectorResponse == 2) {
					/*
					 * - - - - - - - - - AUTHOR - - - - - - - - -
					 */
					AuthorDAO authorDAO = new AuthorDAO();
					boolean back = false;

					while (!back) {
						int crudResponse = CRUDMenu.displayCRUDMenu(myTeclado);
						boolean extraQuestion = true;

						if (crudResponse == 1) {
							AuthorManager.authorCreate(myTeclado, authorDAO);
						} else if (crudResponse == 2) {
							AuthorManager.authorRead(authorDAO);
						} else if (crudResponse == 3) {
							AuthorManager.authorUpdate(myTeclado, authorDAO);
						} else if (crudResponse == 4) {
							AuthorManager.authorDelete(myTeclado, authorDAO);
						} else if (crudResponse == 5) {
							System.out.println();
							back = true;
							extraQuestion = false;
						} else {
							System.out.println("Opção não reconhecida. Escolha uma opção válida.");
							continue;
						}

						if (extraQuestion) {
							boolean goodResponse = false;
							while (!goodResponse) {
								int continueResponse = CRUDMenu.promptForAnotherOperation(myTeclado);

								if (continueResponse == 0) {
									mainMenu = false;
									goodResponse = true;
									back = true;
								} else if (continueResponse == 1) {
									goodResponse = true;
								} else {
									System.out.println("Opção não reconhecida. Escolha uma opção válida.");
								}
							}
						}
					}
				} else if (sectorResponse == 3) {
					/*
					 * - - - - - - - - - REVIEW - - - - - - - - -
					 */
					ReviewDAO reviewDAO = new ReviewDAO();
					AuthorDAO authorDAO = new AuthorDAO();
					ProductDAO productDAO = new ProductDAO();
					boolean back = false;

					while (!back) {
						int crudResponse = CRUDMenu.displayCRUDMenu(myTeclado);
						boolean extraQuestion = true;

						if (crudResponse == 1) {
							ReviewManager.reviewCreate(myTeclado, reviewDAO, authorDAO, productDAO);
						} else if (crudResponse == 2) {
							ReviewManager.reviewRead(reviewDAO);
						} else if (crudResponse == 3) {
							ReviewManager.reviewUpdate(myTeclado, reviewDAO);
						} else if (crudResponse == 4) {
							ReviewManager.reviewDelete(myTeclado, reviewDAO);
						} else if (crudResponse == 5) {
							System.out.println();
							back = true;
							extraQuestion = false;
						} else {
							System.out.println("Opção não reconhecida. Escolha uma opção válida.");
							continue;
						}

						if (extraQuestion) {
							boolean goodResponse = false;
							while (!goodResponse) {
								int continueResponse = CRUDMenu.promptForAnotherOperation(myTeclado);

								if (continueResponse == 0) {
									mainMenu = false;
									goodResponse = true;
									back = true;
								} else if (continueResponse == 1) {
									goodResponse = true;
								} else {
									System.out.println("Opção não reconhecida. Escolha uma opção válida.");
								}
							}
						}
					}
				} else if (sectorResponse == 4) {
					System.out.println("Encerrando o programa...");
					mainMenu = false;
				} else {
					System.out.println("Opção não reconhecida. Escolha uma opção válida.");
				}
			}
		}
	}
}
