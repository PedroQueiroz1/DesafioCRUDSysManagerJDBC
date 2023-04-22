package br.com.syscrud.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mysql.cj.jdbc.JdbcPreparedStatement;

import br.com.syscrud.factory.ConnectionFactory;
import br.com.syscrud.model.Author;
import br.com.syscrud.model.Review;
import br.com.syscrud.util.Constants;

public class AuthorDAO {

	// CREATE
	public void save(Author author) throws SQLException, Exception {

		String sql = "INSERT INTO `author` (`name`) VALUES (?)";

		Connection conn = null;
		JdbcPreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, author.getName());

			System.out.println("Novo autor salvo! -> Autor: " + author.getName());
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
	public void findAll() throws SQLException, ClassNotFoundException {
		String sql = "SELECT * FROM `author`";
		Connection conn = null;
		JdbcPreparedStatement pstm = null;
		ResultSet rset = null;
		int count = 0;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);
			rset = pstm.executeQuery();

			ReviewDAO reviewDAO = new ReviewDAO();

			while (rset.next()) {
				count++;
				Author author = new Author();
				author.setId(rset.getInt("id"));
				author.setName(rset.getString("name"));

				List<Review> reviews = reviewDAO.findByAuthorId(author.getId());
				author.setReviews(reviews);

				author.printDetails();
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

	// READ
	public Author findById(int id) throws SQLException, ClassNotFoundException {
		String sql = "SELECT * FROM `author` WHERE `id` = ?";
		Author author = null;
		Connection conn = null;
		JdbcPreparedStatement pstm = null;
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);
			pstm.setInt(1, id);
			rset = pstm.executeQuery();

			if (rset.next()) {
				author = new Author();
				author.setId(rset.getInt("id"));
				author.setName(rset.getString("name"));
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
		return author;
	}

	// READ
	public Author findByName(String name) throws SQLException, ClassNotFoundException {
		String sql = "SELECT * FROM `author` WHERE `name` = ?";
		Author author = null;
		Connection conn = null;
		JdbcPreparedStatement pstm = null;
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, name);
			rset = pstm.executeQuery();

			if (rset.next()) {
				author = new Author();
				author.setId(rset.getInt("id"));
				author.setName(rset.getString("name"));
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
		return author;
	}

	// UPDATE
	public void update(Author author) throws SQLException, Exception {
		String sql = "UPDATE `author` SET `name` = ? WHERE `id` = ?";
		Connection conn = null;
		JdbcPreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);

			pstm.setString(1, author.getName());
			pstm.setInt(2, author.getId());

			System.out.println("O autor foi atualizado! -> ID do Autor: " + author.getId());
			pstm.execute();

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
	public void deleteById(int id) throws SQLException, Exception {
		String sql = "DELETE FROM `author` WHERE `id` = ?";
		Connection conn = null;
		JdbcPreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();

			ReviewDAO reviewDAO = new ReviewDAO();
			reviewDAO.deleteByAuthorId(id);

			pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);
			pstm.setInt(1, id);

			int rowsAffected = pstm.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Autor deletado!");
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

}
