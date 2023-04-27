package br.com.syscrud.view;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.syscrud.dao.AuthorDAO;
import br.com.syscrud.model.Author;

@ManagedBean
@SessionScoped
public class AuthorView implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Author> authorList;
	private AuthorDAO authorDAO;
	private Author author;
	

	public String create() throws SQLException, Exception {
		this.getAuthorDAO().save(this.author);
		this.author = new Author();
		return "index";
	}
	
	public String delete() throws SQLException, Exception {
		this.getAuthorDAO().deleteById(this.author.getId());
		this.author = new Author();
		return "index";
	}
	
	public String updateForm(Author author) {
		this.author = author;
		return "index";
	}
	
	public String update() throws SQLException, Exception {
		this.getAuthorDAO().update(this.author);
		this.author = new Author();
		return "index";
	}
	
	//Limpa o formulário, volta ao estado de criação de objeto
	public String clearForm() {
		this.author = new Author();
		return "index";
	}
	
	//Ao clicar no botão delete
	//Redireciona para o arquivo "confirmDeleteAuthor.xhtml"
	public String deleteConfirm(Author author) {
		this.author = author;
		return "confirmDeleteAuthor";
	}
	
	public List<Author> getAuthorList() throws ClassNotFoundException, SQLException{
		this.authorList = this.getAuthorDAO().findAllList();
		return authorList;
	}
	
	public void setAuthorList(List<Author> authorList) {
		this.authorList = authorList;
	}
	
	public AuthorDAO getAuthorDAO() {
		if(this.authorDAO == null) 
			this.authorDAO = new AuthorDAO();
		return authorDAO;
	}
	
	public void setAuthorDAO(AuthorDAO authorDAO) {
		this.authorDAO = authorDAO;
	}
	
	public Author getAuthor() {
		if(this.author == null)
			this.author = new Author();
		return author;
	}
	
	public void setAuthor(Author author) {
		this.author = author;
	}
}
