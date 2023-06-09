package br.com.syscrud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.syscrud.exception.ResourceNotFoundException;
import br.com.syscrud.factory.ConnectionFactory;
import br.com.syscrud.model.Book;
import br.com.syscrud.util.Constants;

public class BookDAO {

	// CREATE
	public void save(Book book) throws SQLException, Exception {
		String sqlProduct = "INSERT INTO product (name, price, quantity) VALUES (?, ?, ?)";
		String sqlBook = "INSERT INTO book (id, genre) VALUES (?, ?)";

		Connection conn = null;
		PreparedStatement pstmProduct = null;
		PreparedStatement pstmBook = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();

			pstmProduct = conn.prepareStatement(sqlProduct, Statement.RETURN_GENERATED_KEYS);
			pstmProduct.setString(1, book.getName());
			pstmProduct.setDouble(2, book.getPrice());
			pstmProduct.setInt(3, book.getQuantity());
			pstmProduct.executeUpdate();

			ResultSet generatedKeys = pstmProduct.getGeneratedKeys();
			if (generatedKeys.next()) {
				int productId = generatedKeys.getInt(1);
				pstmBook = conn.prepareStatement(sqlBook);
				pstmBook.setInt(1, productId);
				pstmBook.setString(2, book.getGenre());
				pstmBook.executeUpdate();
			}

			System.out.println("Novo livro salvo! -> Nome do livro: " + book.getName());
		} catch (SQLException e) {
			System.err.println(Constants.ERROR_MESSAGE_DB_OPERATION + e.getMessage());
			throw e;
		} finally {
			try {
				if (pstmProduct != null) {
					pstmProduct.close();
				}
				if (pstmBook != null) {
					pstmBook.close();
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
	public List<Book> findAll() throws SQLException, ClassNotFoundException, ResourceNotFoundException {
		String sql = "SELECT b.id, b.genre, p.name, p.price, p.quantity " + "FROM book b "
				+ "INNER JOIN product p ON b.id = p.id";
		List<Book> books = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		int count = 0;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			rset = pstm.executeQuery();

			while (rset.next()) {
				count++;
				Book book = new Book();
				book.setId(rset.getInt("id"));
				book.setName(rset.getString("name"));
				book.setPrice(rset.getDouble("price"));
				book.setQuantity(rset.getInt("quantity"));
				book.setGenre(rset.getString("genre"));

				books.add(book);
			}
	        if (count == 0) {
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
		if (books.isEmpty()) {
			System.out.println(Constants.ERROR_MESSAGE_NOT_FOUND);
		}
		return books;
	}

	// READ
	public Book findById(int id) throws SQLException, ClassNotFoundException {
		String sql = "SELECT p.*, b.genre FROM `product` p JOIN `book` b ON p.id = b.id WHERE p.`id` = ?";
		Book book = null;
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			rset = pstm.executeQuery();

			if (rset.next()) {
				book = new Book();
				book.setId(rset.getInt("id"));
				book.setName(rset.getString("name"));
				book.setPrice(rset.getDouble("price"));
				book.setQuantity(rset.getInt("quantity"));
				book.setGenre(rset.getString("genre"));
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
		return book;
	}

	// READ
	public Book findByName(String name) throws SQLException, ClassNotFoundException {
		String sql = "SELECT * FROM `product` WHERE `name` = ?";
		Book book = null;
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, name);
			rset = pstm.executeQuery();

			if (rset.next()) {
				book = new Book();
				book.setId(rset.getInt("id"));
				book.setName(rset.getString("name"));
				book.setPrice(rset.getDouble("price"));
				book.setQuantity(rset.getInt("quantity"));
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
		return book;
	}

	// UPDATE
	public void update(Book book) throws SQLException, Exception {
		String sqlProduct = "UPDATE `product` SET `name` = ?, `price` = ?, `quantity` = ? WHERE `id` = ?";
		String sqlBook = "Update `book` SET `genre` = ? WHERE `id` = ?";
		Connection conn = null;
		PreparedStatement pstmProduct = null;
		PreparedStatement pstmBook = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstmProduct = conn.prepareStatement(sqlProduct);
			pstmBook = conn.prepareStatement(sqlBook);

			
			pstmProduct.setString(1, book.getName());
			pstmProduct.setDouble(2, book.getPrice());
			pstmProduct.setInt(3, book.getQuantity());
			pstmProduct.setInt(4, book.getId());
			
			pstmBook.setString(1, book.getGenre());
			pstmBook.setInt(2, book.getId());

			conn.setAutoCommit(false);
			int rowsAffectedProduct = pstmProduct.executeUpdate();
			int rowsAffectedBook = pstmBook.executeUpdate();
			conn.commit();
			
			if (rowsAffectedProduct == 0 && rowsAffectedBook == 0) {
				System.out.println(Constants.ERROR_MESSAGE_NOT_FOUND);
			}

			System.out.println("Livro atualizado! -> ID do livro: " + book.getId());
			
		} catch (SQLException e) {
			System.err.println(Constants.ERROR_MESSAGE_DB_OPERATION + e.getMessage());
			throw e;
		} catch (ClassNotFoundException e) {
			System.err.println(Constants.ERROR_MESSAGE_LOAD_DRIVER_CLASS + e.getMessage());
			throw e;
		} finally {
			try {
				if (pstmProduct != null) {
					pstmProduct.close();
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
	public void deleteById(int id) throws SQLException, ClassNotFoundException {
		String sql = "DELETE FROM `book` WHERE `id` = ?";
		Connection conn = null;
		PreparedStatement pstm = null;
		ProductDAO productDAO = new ProductDAO();
		BookDAO bookDAO = new BookDAO();
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			
			int rowsAffected = pstm.executeUpdate();
			
			bookDAO.deleteBookByProductId(id);
			try {
				productDAO.deleteBookById(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (rowsAffected > 0) {
				System.out.println("Livro deletado!");
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
	public void deleteBookByProductId(int productId) throws SQLException, ClassNotFoundException {
	    String sql = "DELETE FROM `book` WHERE `id` = ?";
	    Connection conn = null;
	    PreparedStatement pstm = null;

	    try {
	        conn = ConnectionFactory.createConnectionToMySQL();
	        pstm = conn.prepareStatement(sql);
	        pstm.setInt(1, productId);

	        pstm.executeUpdate();

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
}
