package br.com.syscrud.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.JdbcPreparedStatement;

import br.com.syscrud.factory.ConnectionFactory;
import br.com.syscrud.model.Product;

public class ProductDAO {
	
	public void save(Product product) {

		String sql = "INSERT INTO `product` (`name`, `price`, `quantity`) VALUES (?, ?, ?)";

		Connection conn = null;
		JdbcPreparedStatement pstm = null;

		try {
			// Cria conexão com banco de dados
			conn = ConnectionFactory.createConnectionToMySQL();

			// pstm para executar uma query
			pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, product.getName());
			pstm.setDouble(2, product.getPrice());
			pstm.setInt(3, product.getQuantity());

			System.out.println("New product saved! -> Product Name: " + product.getName());
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

	public List<Product> findAll() {
		String sql = "SELECT * FROM product";
		List<Product> products = new ArrayList<Product>();
		Connection conn = null;
		JdbcPreparedStatement pstm = null;

		// Recuperar dados do banco
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);
			rset = pstm.executeQuery();

			while (rset.next()) {
				Product product = new Product();
				product.setId(rset.getInt("id"));
				product.setName(rset.getString("name"));
				product.setPrice(rset.getDouble("price"));
				product.setQuantity(rset.getInt("quantity"));

				products.add(product);
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
		return products;
	}
	
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
	
	public void update(Product product) {
		String sql = "UPDATE `product` SET `name` = ?, `price` = ?, `quantity` = ? WHERE `id` = ?";
		Connection conn = null;
		JdbcPreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);
			
			pstm.setString(1, product.getName());
			pstm.setDouble(2, product.getPrice());
			pstm.setInt(3, product.getQuantity());
			pstm.setInt(4, product.getId());

			System.out.println("Product updated! -> Review ID: " + product.getId());
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

	public void deleteById(int id) {
		String sql = "DELETE FROM `product` WHERE `id` = ?";
		Connection conn = null;
		JdbcPreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);
			pstm.setInt(1, id);
			
			System.out.println("Product deleted! -> Product ID: " + id);
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