package br.com.syscrud.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.JdbcPreparedStatement;

import br.com.syscrud.factory.ConnectionFactory;
import br.com.syscrud.model.Book;

public class BookDAO {

	public void save(Book book) {
		String sqlProduct = "INSERT INTO product (name, price, quantity) VALUES (?, ?, ?)";
		String sqlBook = "INSERT INTO book (id, genre) VALUES (?, ?)";

		Connection conn = null;
		JdbcPreparedStatement pstmProduct = null;
		JdbcPreparedStatement pstmBook = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();

			pstmProduct = (JdbcPreparedStatement) conn.prepareStatement(sqlProduct, Statement.RETURN_GENERATED_KEYS);
			pstmProduct.setString(1, book.getName());
			pstmProduct.setDouble(2, book.getPrice());
			pstmProduct.setInt(3, book.getQuantity());
			pstmProduct.executeUpdate();

			ResultSet generatedKeys = pstmProduct.getGeneratedKeys();
			if (generatedKeys.next()) {
				int productId = generatedKeys.getInt(1);
				pstmBook = (JdbcPreparedStatement) conn.prepareStatement(sqlBook);
				pstmBook.setInt(1, productId);
				pstmBook.setString(2, book.getGenre());
				pstmBook.executeUpdate();
			}

			System.out.println("Novo livro salvo! -> Nome do livro: " + book.getName());
		} catch (Exception e) {
			e.printStackTrace();
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public List<Book> findAll() {
		String sql = "SELECT b.id, b.genre, p.name, p.price, p.quantity " + "FROM book b "
				+ "INNER JOIN product p ON b.id = p.id";
		List<Book> books = new ArrayList<>();
		Connection conn = null;
		JdbcPreparedStatement pstm = null;
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);
			rset = pstm.executeQuery();

			while (rset.next()) {
				Book book = new Book();
				book.setId(rset.getInt("id"));
				book.setName(rset.getString("name"));
				book.setPrice(rset.getDouble("price"));
				book.setQuantity(rset.getInt("quantity"));
				book.setGenre(rset.getString("genre"));

				books.add(book);
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
		return books;
	}

	public Book findById(int id) {
	    String sql = "SELECT p.*, b.genre FROM `product` p JOIN `book` b ON p.id = b.id WHERE p.`id` = ?";
	    Book book = null;
	    Connection conn = null;
	    JdbcPreparedStatement pstm = null;
	    ResultSet rset = null;

	    try {
	        conn = ConnectionFactory.createConnectionToMySQL();
	        pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);
	        pstm.setInt(1, id);
	        rset = pstm.executeQuery();

	        if (rset.next()) {
	            book = new Book();
	            book.setId(rset.getInt("id"));
	            book.setName(rset.getString("name"));
	            book.setPrice(rset.getDouble("price"));
	            book.setQuantity(rset.getInt("quantity"));
	            book.setGenre(rset.getString("genre"));
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
	    return book;
	}
	
	public Book findByName(String name) {
	    String sql = "SELECT * FROM `product` WHERE `name` = ?";
	    Book book = null;
	    Connection conn = null;
	    JdbcPreparedStatement pstm = null;
	    ResultSet rset = null;

	    try {
	        conn = ConnectionFactory.createConnectionToMySQL();
	        pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);
	        pstm.setString(1, name);
	        rset = pstm.executeQuery();

	        if (rset.next()) {
	            book = new Book();
	            book.setId(rset.getInt("id"));
	            book.setName(rset.getString("name"));
	            book.setPrice(rset.getDouble("price"));
	            book.setQuantity(rset.getInt("quantity"));
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
	    return book;
	}

	public void update(Book book) {
	    String sqlProduct = "UPDATE `product` SET `name` = ?, `price` = ?, `quantity` = ? WHERE `id` = ?";
	    Connection conn = null;
	    JdbcPreparedStatement pstmProduct = null;

	    try {
	        conn = ConnectionFactory.createConnectionToMySQL();
	        pstmProduct = (JdbcPreparedStatement) conn.prepareStatement(sqlProduct);

	        pstmProduct.setString(1, book.getName());
	        pstmProduct.setDouble(2, book.getPrice());
	        pstmProduct.setInt(3, book.getQuantity());
	        pstmProduct.setInt(4, book.getId());

	        conn.setAutoCommit(false);
	        pstmProduct.executeUpdate();
	        conn.commit();

	        System.out.println("Livro atualizado! -> Livro ID: " + book.getId());
	    } catch (Exception e) {
	        try {
	            if (conn != null) {
	                conn.rollback();
	            }
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        e.printStackTrace();
	    } finally {
	        try {
	            if (pstmProduct != null) {
	                pstmProduct.close();
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
	    String sql = "DELETE FROM `book` WHERE `id` = ?";
	    Connection conn = null;
	    JdbcPreparedStatement pstm = null;

	    try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);
			pstm.setInt(1, id);

			pstm.execute();
			System.out.println("Livro deletado! -> ID do Livro: " + id);
	    } catch (Exception e) {
	        try {
	            if (conn != null) {
	                conn.rollback();
	            }
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
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
