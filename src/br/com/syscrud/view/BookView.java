package br.com.syscrud.view;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.syscrud.dao.BookDAO;
import br.com.syscrud.model.Book;

@ManagedBean
@SessionScoped
public class BookView implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Book> bookList;
	private BookDAO bookDAO;
	private Book book;
	

	public String create() throws SQLException, Exception {
		this.getBookDAO().save(this.book);
		this.book = new Book();
		return "index";
	}
	
	public String delete() throws SQLException, Exception {
		this.getBookDAO().deleteById(this.book.getId());
		this.book = new Book();
		return "index";
	}
	
	public String updateForm(Book book) {
		this.book = book;
		return "index";
	}
	
	public String update() throws SQLException, Exception {
		this.getBookDAO().update(this.book);
		this.book = new Book();
		return "index";
	}
	
	//Limpa o formulário, volta ao estado de criação de objeto
	public String clearForm() {
		this.book = new Book();
		return "index";
	}
	
	//Ao clicar no botão delete
	//Redireciona para o arquivo "confirmDeleteBook.xhtml"
	public String deleteConfirm(Book book) {
		this.book = book;
		return "confirmDeleteBook";
	}
	
	public List<Book> getBookList() throws ClassNotFoundException, SQLException{
		this.bookList = this.getBookDAO().findAll();
		return bookList;
	}
	
	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}
	
	public BookDAO getBookDAO() {
		if(this.bookDAO == null) 
			this.bookDAO = new BookDAO();
		return bookDAO;
	}
	
	public void setBookDAO(BookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}
	
	public Book getBook() {
		if(this.book == null)
			this.book = new Book();
		return book;
	}
	
	public void setBook(Book book) {
		this.book = book;
	}
}
