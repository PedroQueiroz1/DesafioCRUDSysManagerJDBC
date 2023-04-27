package br.com.syscrud.view;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.syscrud.dao.MovieDAO;
import br.com.syscrud.model.Movie;

@ManagedBean
@SessionScoped
public class MovieView implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Movie> movieList;
	private MovieDAO movieDAO;
	private Movie movie;
	

	public String create() throws SQLException, Exception {
		this.getMovieDAO().save(this.movie);
		this.movie = new Movie();
		return "index";
	}
	
	public String delete() throws SQLException, Exception {
		this.getMovieDAO().deleteById(this.movie.getId());
		this.movie = new Movie();
		return "index";
	}
	
	public String updateForm(Movie movie) {
		this.movie = movie;
		return "index";
	}
	
	public String update() throws SQLException, Exception {
		this.getMovieDAO().update(this.movie);
		this.movie = new Movie();
		return "index";
	}
	
	//Limpa o formulário, volta ao estado de criação de objeto
	public String clearForm() {
		this.movie = new Movie();
		return "index";
	}
	
	//Ao clicar no botão delete
	//Redireciona para o arquivo "confirmDeleteMovie.xhtml"
	public String deleteConfirm(Movie movie) {
		this.movie = movie;
		return "confirmDeleteMovie";
	}
	
	public List<Movie> getMovieList() throws ClassNotFoundException, SQLException{
		this.movieList = this.getMovieDAO().findAll();
		return movieList;
	}
	
	public void setMovieList(List<Movie> movieList) {
		this.movieList = movieList;
	}
	
	public MovieDAO getMovieDAO() {
		if(this.movieDAO == null) 
			this.movieDAO = new MovieDAO();
		return movieDAO;
	}
	
	public void setMovieDAO(MovieDAO movieDAO) {
		this.movieDAO = movieDAO;
	}
	
	public Movie getMovie() {
		if(this.movie == null)
			this.movie = new Movie();
		return movie;
	}
	
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
	// VERIFICA se o valor é numérico e se é maior que zero.
	// Caso não, lança uma exceção correspondente.
	public void validateNumberEntry(FacesContext context, UIComponent component, Object value) throws ValidatorException {
	    if (value == null || !(value instanceof Number)) {
	        throw new ValidatorException(new FacesMessage("O valor deve ser numérico."));
	    }

	    double price = ((Number) value).doubleValue();

	    if (price <= 0) {
	        throw new ValidatorException(new FacesMessage("O valor deve ser maior que 0."));
	    }
	}
}
