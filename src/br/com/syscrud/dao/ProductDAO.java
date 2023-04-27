package br.com.syscrud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.syscrud.factory.ConnectionFactory;
import br.com.syscrud.model.Product;
import br.com.syscrud.model.Review;
import br.com.syscrud.util.Constants;

public class ProductDAO {

	// READ
	public Product findById(int id) throws SQLException, ClassNotFoundException {
		String sql = "SELECT * FROM `product` WHERE `id` = ?";
		Product product = null;
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			rset = pstm.executeQuery();

			if (rset.next()) {
				product = new Product();
				product.setId(rset.getInt("id"));
				product.setName(rset.getString("name"));
				product.setPrice(rset.getDouble("price"));
				product.setQuantity(rset.getInt("quantity"));
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
		return product;
	}

	// READ
	public Product findByName(String name) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM `product` WHERE `name` = ?";
		Product product = null;
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, name);
			rset = pstm.executeQuery();

			if (rset.next()) {
				product = new Product();
				product.setId(rset.getInt("id"));
				product.setName(rset.getString("name"));
				product.setPrice(rset.getDouble("price"));
				product.setQuantity(rset.getInt("quantity"));
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

		return product;
	}

	// READ
	public void findAll() throws SQLException, ClassNotFoundException {
		String sql = "SELECT * FROM `product`";
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			rset = pstm.executeQuery();
			int count = 0;

			ReviewDAO reviewDAO = new ReviewDAO();

			while (rset.next()) {
				count++;
				Product product = new Product();
				product.setId(rset.getInt("id"));
				product.setName(rset.getString("name"));
				product.setPrice(rset.getDouble("price"));
				product.setQuantity(rset.getInt("quantity"));

				List<Review> reviews = reviewDAO.findByProductId(product.getId());
				product.setReviews(reviews);

				product.printDetails();
			}
			if (count == 0) {
				System.out.println(Constants.ERROR_MESSAGE_NOT_FOUND);
			}
		} catch (SQLException e) {
			System.err.println(Constants.ERROR_MESSAGE_DB_OPERATION + e.getMessage());
			throw e;
		} catch (ClassNotFoundException e) {
			System.err.println(Constants.ERROR_MESSAGE_DB_OPERATION + e.getMessage());
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
	}

	// DELETE
	public void deleteBookById(int id) throws SQLException, Exception {
		String sql = "DELETE FROM `product` WHERE `id` = ?";
		Connection conn = null;
		PreparedStatement pstm = null;

		BookDAO bookDAO = new BookDAO();
		bookDAO.deleteBookByProductId(id);

		try {
			conn = ConnectionFactory.createConnectionToMySQL();

			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);

			pstm.executeUpdate();

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

	public void deleteMovieById(int id) throws SQLException, Exception {
		String sql = "DELETE FROM `product` WHERE `id` = ?";
		Connection conn = null;
		PreparedStatement pstm = null;

		MovieDAO movieDAO = new MovieDAO();
		movieDAO.deleteMovieByProductId(id);

		try {
			conn = ConnectionFactory.createConnectionToMySQL();

			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);

			pstm.executeUpdate();

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