package br.com.syscrud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.JdbcPreparedStatement;

import br.com.syscrud.exception.ResourceNotFoundException;
import br.com.syscrud.factory.ConnectionFactory;
import br.com.syscrud.model.Author;
import br.com.syscrud.model.Product;
import br.com.syscrud.model.Review;
import br.com.syscrud.util.Constants;

public class ReviewDAO {

	// CREATE
	public void save(Review review) throws SQLException, Exception {

		String sql = "INSERT INTO `review` (`stars`, `comment`, `reviewer_id`, `product_id`) VALUES (?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, review.getStars());
			pstm.setString(2, review.getComment());
			pstm.setInt(3, review.getReviewer().getId());
			pstm.setInt(4, review.getProduct().getId());

			System.out.println("Nova análise salva! -> Análise: " + review.getComment());
			pstm.execute();
		} catch (SQLException e) {
			System.err.println(Constants.ERROR_MESSAGE_DB_OPERATION + e.getMessage());
			throw e;
		} finally {

			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.err.println(Constants.ERROR_MESSAGE_CLOSE_CONNECTION + e.getMessage());
			}
		}
	}

	// READ
	public List<Review> findAll() throws SQLException, ClassNotFoundException, ResourceNotFoundException {
		String sql = "SELECT * FROM review";
		List<Review> reviews = new ArrayList<Review>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			rset = pstm.executeQuery();

			while (rset.next()) {

				Review review = new Review();
				review.setId(rset.getInt("id"));
				review.setStars(rset.getInt("stars"));
				review.setComment(rset.getString("comment"));

				// Carrega o reviewer associado à review
				AuthorDAO authorDAO = new AuthorDAO();
				Author reviewer = authorDAO.findById(rset.getInt("reviewer_id"));
				review.setReviewer(reviewer);

				// Carrega o produto associado à review
				ProductDAO productDAO = new ProductDAO();
				Product product = productDAO.findById(rset.getInt("product_id"));
				review.setProduct(product);

				reviews.add(review);
			}
			
		} catch (SQLException e) {
			System.err.println(Constants.ERROR_MESSAGE_DB_OPERATION + e.getMessage());
			throw e;
		} catch (ClassNotFoundException e) {
			System.err.println(Constants.ERROR_MESSAGE_LOAD_DRIVER_CLASS + e.getMessage());
			throw e;
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.err.println(Constants.ERROR_MESSAGE_CLOSE_CONNECTION + e.getMessage());
			}
		}
		if (reviews.isEmpty()) {
			System.out.println(Constants.ERROR_MESSAGE_NOT_FOUND);
		}
		return reviews;
	}

	// READ
	public Review findById(int id) throws SQLException, ResourceNotFoundException, ClassNotFoundException {
		String sql = "SELECT * FROM `review` WHERE `id` = ?";
		Review review = null;
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			rset = pstm.executeQuery();

			if (rset.next()) {
				review = new Review();
				review.setId(rset.getInt("id"));
				review.setStars(rset.getInt("stars"));
				review.setComment(rset.getString("comment"));
				AuthorDAO authorDAO = new AuthorDAO();
				Author reviewer = authorDAO.findById(rset.getInt("reviewer_id"));
				review.setReviewer(reviewer);
				ProductDAO productDAO = new ProductDAO();
				Product product = productDAO.findById(rset.getInt("product_id"));
				review.setProduct(product);
			} else {
				System.out.println(Constants.ERROR_MESSAGE_NOT_FOUND);
			}

		} catch (SQLException e) {
			System.err.println(Constants.ERROR_MESSAGE_DB_OPERATION + e.getMessage());
			throw e;
		} catch (ClassNotFoundException e) {
			System.err.println(Constants.ERROR_MESSAGE_LOAD_DRIVER_CLASS + e.getMessage());
			throw e;
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.err.println(Constants.ERROR_MESSAGE_CLOSE_CONNECTION + e.getMessage());
			}
		}
		return review;
	}

	// READ
	public Review findByName(String name) throws SQLException, ClassNotFoundException, ResourceNotFoundException {
		String sql = "SELECT * FROM `review` WHERE `product_id` " + "= (SELECT `id` FROM `product` WHERE `name` = ?)";
		Review review = null;
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, name);
			rset = pstm.executeQuery();

			if (rset.next()) {
				review = new Review();
				review.setId(rset.getInt("id"));
				review.setStars(rset.getInt("stars"));
				review.setComment(rset.getString("comment"));
				AuthorDAO authorDAO = new AuthorDAO();
				Author reviewer = authorDAO.findById(rset.getInt("reviewer_id"));
				review.setReviewer(reviewer);
				ProductDAO productDAO = new ProductDAO();
				Product product = productDAO.findById(rset.getInt("product_id"));
				review.setProduct(product);
			}

		} catch (SQLException e) {
			System.err.println(Constants.ERROR_MESSAGE_DB_OPERATION + e.getMessage());
			throw e;
		} catch (ClassNotFoundException e) {
			System.err.println(Constants.ERROR_MESSAGE_LOAD_DRIVER_CLASS + e.getMessage());
			throw e;
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.err.println(Constants.ERROR_MESSAGE_CLOSE_CONNECTION + e.getMessage());
			}
		}
		return review;
	}

	// READ
	public List<Review> findByAuthorId(int authorId)
			throws SQLException, ClassNotFoundException, ResourceNotFoundException {
		String sql = "SELECT * FROM `review` WHERE `reviewer_id` = ?";
		List<Review> reviews = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, authorId);
			rset = pstm.executeQuery();

			while (rset.next()) {
				Review review = new Review();
				review.setId(rset.getInt("id"));
				review.setStars(rset.getInt("stars"));
				review.setComment(rset.getString("comment"));
				AuthorDAO authorDAO = new AuthorDAO();
				Author reviewer = authorDAO.findById(rset.getInt("reviewer_id"));
				review.setReviewer(reviewer);
				ProductDAO productDAO = new ProductDAO();
				Product product = productDAO.findById(rset.getInt("product_id"));
				review.setProduct(product);

				reviews.add(review);
			}
		} catch (SQLException e) {
			System.err.println(Constants.ERROR_MESSAGE_DB_OPERATION + e.getMessage());
			throw e;
		} catch (ClassNotFoundException e) {
			System.err.println(Constants.ERROR_MESSAGE_LOAD_DRIVER_CLASS + e.getMessage());
			throw e;
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.err.println(Constants.ERROR_MESSAGE_CLOSE_CONNECTION + e.getMessage());
			}
		}
		return reviews;
	}

	// READ
	public List<Review> findByProductId(int productId)
			throws SQLException, ClassNotFoundException, ResourceNotFoundException {
		String sql = "SELECT * FROM `review` WHERE `product_id` = ?";
		List<Review> reviews = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, productId);
			rset = pstm.executeQuery();

			if (rset.next()) {
				Review review = new Review();
				review.setId(rset.getInt("id"));
				review.setStars(rset.getInt("stars"));
				review.setComment(rset.getString("comment"));
				AuthorDAO authorDAO = new AuthorDAO();
				Author reviewer = authorDAO.findById(rset.getInt("reviewer_id"));
				review.setReviewer(reviewer);
				ProductDAO productDAO = new ProductDAO();
				Product product = productDAO.findById(rset.getInt("product_id"));
				review.setProduct(product);

				reviews.add(review);
			}
		} catch (SQLException e) {
			System.err.println(Constants.ERROR_MESSAGE_DB_OPERATION + e.getMessage());
			throw e;
		} catch (ClassNotFoundException e) {
			System.err.println(Constants.ERROR_MESSAGE_LOAD_DRIVER_CLASS + e.getMessage());
			throw e;
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.err.println(Constants.ERROR_MESSAGE_CLOSE_CONNECTION + e.getMessage());
			}
		}
		return reviews;
	}

	// UPDATE
	public void update(Review review) throws SQLException, ClassNotFoundException, ResourceNotFoundException {
		String sql = "UPDATE `review` SET `stars` = ?, `comment` = ?, "
				+ "`reviewer_id` = ?, `product_id` = ? WHERE `id` = ?";
		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);

			pstm.setInt(1, review.getStars());
			pstm.setString(2, review.getComment());
			pstm.setInt(3, review.getReviewer().getId());
			pstm.setInt(4, review.getProduct().getId());
			pstm.setInt(5, review.getId());

			int rowsAffected = pstm.executeUpdate();

			if (rowsAffected == 0) {
				System.out.println(Constants.ERROR_MESSAGE_NOT_FOUND);
			}

			System.out.println("Comentário atualizado! -> ID do comentário: " + review.getId());
		} catch (SQLException e) {
			System.err.println(Constants.ERROR_MESSAGE_DB_OPERATION + e.getMessage());
			throw e;
		} catch (ClassNotFoundException e) {
			System.err.println(Constants.ERROR_MESSAGE_LOAD_DRIVER_CLASS + e.getMessage());
			throw e;
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.err.println(Constants.ERROR_MESSAGE_CLOSE_CONNECTION + e.getMessage());
			}
		}
	}

	// DELETE
	public void deleteById(int id) throws SQLException, ClassNotFoundException, ResourceNotFoundException {
		String sql = "DELETE FROM `review` WHERE `id` = ?";
		Connection conn = null;
		JdbcPreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);
			pstm.setInt(1, id);

			int rowsAffected = pstm.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Comentário deletado! -> ID do comentário: " + id);
			} else {
				System.out.println(Constants.ERROR_MESSAGE_NOT_FOUND);
			}

		} catch (SQLException e) {
			System.err.println(Constants.ERROR_MESSAGE_DB_OPERATION + e.getMessage());
			throw e;
		} catch (ClassNotFoundException e) {
			System.err.println(Constants.ERROR_MESSAGE_LOAD_DRIVER_CLASS + e.getMessage());
			throw e;
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.err.println(Constants.ERROR_MESSAGE_CLOSE_CONNECTION + e.getMessage());
			}
		}
	}

	// DELETE
	public void deleteByAuthorId(int authorId) throws SQLException, Exception {
		String sql = "DELETE FROM `review` WHERE `reviewer_id` = ?";
		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, authorId);
			int rowsAffected = pstm.executeUpdate();

			System.out.println(rowsAffected + " comentários deletados!");

		} catch (SQLException e) {
			System.err.println(Constants.ERROR_MESSAGE_DB_OPERATION + e.getMessage());
			throw e;
		} catch (ClassNotFoundException e) {
			System.err.println(Constants.ERROR_MESSAGE_LOAD_DRIVER_CLASS + e.getMessage());
			throw e;
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.err.println(Constants.ERROR_MESSAGE_CLOSE_CONNECTION + e.getMessage());
			}
		}
	}
}
