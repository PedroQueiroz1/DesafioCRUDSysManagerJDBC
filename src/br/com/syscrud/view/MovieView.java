package br.com.syscrud.view;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
	
	public String clearForm() {
		this.movie = new Movie();
		return "index";
	}
	
	public String deleteConfirm(Movie movie) {
		this.movie = movie;
		return "confirmDelete";
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
}
