package br.com.syscrud.view;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.syscrud.dao.AuthorDAO;
import br.com.syscrud.dao.ProductDAO;
import br.com.syscrud.dao.ReviewDAO;
import br.com.syscrud.model.Author;
import br.com.syscrud.model.Product;
import br.com.syscrud.model.Review;

@ManagedBean
@SessionScoped
public class ReviewView implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Review> reviewList;
	private ReviewDAO reviewDAO;
	private Review review;
	
	private Author author;
	private Product product;

	private AuthorView authorView;
	
	public String create() throws SQLException, Exception {
	    if (review.getReviewer() == null || review.getReviewer().getName() == null ||
	        review.getProduct() == null || review.getProduct().getName() == null) {
	        FacesContext.getCurrentInstance().addMessage(null,
	            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Nome do avaliador ou do produto não podem estar em branco."));
	        return null;
	    }
	    
	    // Obtém o produto e o avaliador pelo nome
	    ProductDAO productDAO = new ProductDAO();
	    Product product = null;
	    try {
	        product = productDAO.findByName(review.getProduct().getName());
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	        FacesContext.getCurrentInstance().addMessage(null,
	            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Ocorreu um erro ao buscar o produto."));
	        return null;
	    }
	    
	    AuthorDAO reviewerDAO = new AuthorDAO();
	    Author reviewer = null;
	    try {
	        reviewer = reviewerDAO.findByName(review.getReviewer().getName());
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	        FacesContext.getCurrentInstance().addMessage(null,
	            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Ocorreu um erro ao buscar o avaliador."));
	        return null;
	    }
	    
	    // Verifica se o produto e o avaliador foram encontrados
	    if (product == null) {
	        FacesContext.getCurrentInstance().addMessage(null,
	            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Produto não encontrado."));
	        return null;
	    }
	    
	    if (reviewer == null) {
	        FacesContext.getCurrentInstance().addMessage(null,
	            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Avaliador não encontrado."));
	        return null;
	    }
	    
	    // Atribui os objetos encontrados à review
	    review.setProduct(product);
	    review.setReviewer(reviewer);
	    
	    // Salva a review
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
	
	//Limpa o formulário, volta ao estado de criação de objeto
	public String clearForm() {
		this.review = new Review();
		return "index";
	}
	
	//Ao clicar no botão delete
	//Redireciona para o arquivo "confirmDeleteMovie.xhtml"
	public String deleteConfirm(Review review) {
		this.review = review;
		return "confirmDeleteReview";
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
	
	public Converter getAuthorConverter() {
        return new Converter() {
            @Override
            public Object getAsObject(FacesContext context, UIComponent component, String value) {
                // Obtém o autor com o nome especificado
                AuthorDAO authorDAO = new AuthorDAO();
                Author author = null;
				try {
					author = authorDAO.findByName(value);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                return author;
            }

            @Override
            public String getAsString(FacesContext context, UIComponent component, Object value) {
                // Converte o autor para uma string com o nome correspondente
                if (value == null) {
                    return null;
                }
                Author author = (Author) value;
                return author.getName();
            }
        };
	}
	
	public Converter getProductConverter() {
	    return new Converter() {
	        @Override
	        public Object getAsObject(FacesContext context, UIComponent component, String value) {
	            // Obtém o produto com o nome especificado
	            ProductDAO productDAO = new ProductDAO();
	            Product product = null;
	            try {
	                product = productDAO.findByName(value);
	            } catch (ClassNotFoundException e) {
	                e.printStackTrace();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	            return product;
	        }

	        @Override
	        public String getAsString(FacesContext context, UIComponent component, Object value) {
	            // Converte o produto para uma string com o nome correspondente
	            if (value == null) {
	                return null;
	            }
	            Product product = (Product) value;
	            return product.getName();
	        }
	    };
	}
	
	
}
