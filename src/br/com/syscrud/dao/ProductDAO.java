package br.com.syscrud.dao;

import java.sql.Connection;
import java.sql.ResultSet;

import com.mysql.cj.jdbc.JdbcPreparedStatement;

import br.com.syscrud.factory.ConnectionFactory;
import br.com.syscrud.model.Product;

public class ProductDAO {
	
	public Product findById(int id) {
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
	    return product;
	}

	public Product findByName(String name) {
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
		return product;
	}
	
}