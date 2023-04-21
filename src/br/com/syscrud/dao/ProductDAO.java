package br.com.syscrud.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mysql.cj.jdbc.JdbcPreparedStatement;

import br.com.syscrud.exception.ResourceNotFoundException;
import br.com.syscrud.factory.ConnectionFactory;
import br.com.syscrud.model.Product;
import br.com.syscrud.model.Review;
import br.com.syscrud.util.Constants;

public class ProductDAO {

	public Product findById(int id) throws SQLException, ClassNotFoundException {
		String sql = "SELECT * FROM `product` WHERE `id` = ?";
		Product product = null;
		Connection conn = null;
		JdbcPreparedStatement pstm = null;
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);
			pstm.setInt(1, id);
			rset = pstm.executeQuery();

			if (rset.next()) {
				product = new Product();
				product.setId(rset.getInt("id"));
				product.setName(rset.getString("name"));
				product.setPrice(rset.getDouble("price"));
				product.setQuantity(rset.getInt("quantity"));
			} else {
	            throw new ResourceNotFoundException(Constants.ERROR_MESSAGE_NOT_FOUND);
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
	
	public Product findByName(String name) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM `product` WHERE `name` = ?";
		Product product = null;
		Connection conn = null;
		JdbcPreparedStatement pstm = null;
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, name);
			rset = pstm.executeQuery();

			if (rset.next()) {
				product = new Product();
				product.setId(rset.getInt("id"));
				product.setName(rset.getString("name"));
				product.setPrice(rset.getDouble("price"));
				product.setQuantity(rset.getInt("quantity"));
			} else {
				throw new ResourceNotFoundException("Produto não encontrado");
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

	public void findAll() throws SQLException, ClassNotFoundException {
		String sql = "SELECT * FROM `product`";
		Connection conn = null;
		JdbcPreparedStatement pstm = null;
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);
			rset = pstm.executeQuery();

			ReviewDAO reviewDAO = new ReviewDAO();

			while (rset.next()) {
				Product product = new Product();
				product.setId(rset.getInt("id"));
				product.setName(rset.getString("name"));
				product.setPrice(rset.getDouble("price"));
				product.setQuantity(rset.getInt("quantity"));

				List<Review> reviews = reviewDAO.findByProductId(product.getId());
				product.setReviews(reviews);
				
				product.printDetails();
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
}