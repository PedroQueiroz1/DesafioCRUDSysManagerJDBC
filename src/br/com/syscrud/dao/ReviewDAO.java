package br.com.syscrud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.JdbcPreparedStatement;

import br.com.syscrud.factory.ConnectionFactory;
import br.com.syscrud.model.Author;
import br.com.syscrud.model.Product;
import br.com.syscrud.model.Review;

public class ReviewDAO {

	public void save(Review review) {

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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public List<Review> findAll() {
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
		} catch (Exception e) {
			e.printStackTrace();
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return reviews;
	}

	public Review findById(int id) {
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
			}
		} catch (Exception e) {
			e.printStackTrace();
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return review;
	}

	public Review findByName(String productName) {
		String sql = "SELECT * FROM `review` WHERE `product_id` = (SELECT `id` FROM `product` WHERE `name` = ?)";
		Review review = null;
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, productName);
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
		} catch (Exception e) {
			e.printStackTrace();
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return review;
	}

	public void update(Review review) {
		String sql = "UPDATE `review` SET `stars` = ?, `comment` = ?, `reviewer_id` = ?, `product_id` = ? WHERE `id` = ?";
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

			pstm.executeUpdate();

			System.out.println("Review atualizada! -> Review ID: " + review.getId());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteById(int id) {
		String sql = "DELETE FROM `review` WHERE `id` = ?";
		Connection conn = null;
		JdbcPreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);
			pstm.setInt(1, id);

			System.out.println("Review deleted! -> Review ID: " + id);
			pstm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
