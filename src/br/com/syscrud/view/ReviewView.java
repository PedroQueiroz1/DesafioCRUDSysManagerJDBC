package br.com.syscrud.view;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.syscrud.dao.ReviewDAO;
import br.com.syscrud.model.Review;

@ManagedBean
@SessionScoped
public class ReviewView implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Review> reviewList;
	private ReviewDAO reviewDAO;
	private Review review;
	

	public String create() throws SQLException, Exception {
		this.getReviewDAO().save(this.review);
		this.review = new Review();
		return "index";
	}
	
	public String delete() throws SQLException, Exception {
		this.getReviewDAO().deleteById(this.review.getId());
		this.review = new Review();
		return "index";
	}
	
	public String updateForm(Review review) {
		this.review = review;
		return "index";
	}
	
	public String update() throws SQLException, Exception {
		this.getReviewDAO().update(this.review);
		this.review = new Review();
		return "index";
	}
	
	public String clearForm() {
		this.review = new Review();
		return "index";
	}
	
	public String deleteConfirm(Review review) {
		this.review = review;
		return "confirmDelete";
	}
	
	public List<Review> getReviewList() throws ClassNotFoundException, SQLException{
		this.reviewList = this.getReviewDAO().findAll();
		return reviewList;
	}
	
	public void setReviewList(List<Review> reviewList) {
		this.reviewList = reviewList;
	}
	
	public ReviewDAO getReviewDAO() {
		if(this.reviewDAO == null) 
			this.reviewDAO = new ReviewDAO();
		return reviewDAO;
	}
	
	public void setReviewDAO(ReviewDAO reviewDAO) {
		this.reviewDAO = reviewDAO;
	}
	
	public Review getReview() {
		if(this.review == null)
			this.review = new Review();
		return review;
	}
	
	public void setReview(Review review) {
		this.review = review;
	}
}
