package br.com.syscrud.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.JdbcPreparedStatement;

import br.com.syscrud.exception.ResourceNotFoundException;
import br.com.syscrud.factory.ConnectionFactory;
import br.com.syscrud.model.Movie;
import br.com.syscrud.util.Constants;

public class MovieDAO {

	public void save(Movie movie) throws SQLException, Exception {
		String sqlProduct = "INSERT INTO product (name, price, quantity) VALUES (?, ?, ?)";
		String sqlMovie = "INSERT INTO movie (id, duration) VALUES (?, ?)";

		Connection conn = null;
		JdbcPreparedStatement pstmProduct = null;
		JdbcPreparedStatement pstmMovie = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();

			pstmProduct = (JdbcPreparedStatement) conn.prepareStatement(sqlProduct, Statement.RETURN_GENERATED_KEYS);
			pstmProduct.setString(1, movie.getName());
			pstmProduct.setDouble(2, movie.getPrice());
			pstmProduct.setInt(3, movie.getQuantity());
			pstmProduct.executeUpdate();

			ResultSet generatedKeys = pstmProduct.getGeneratedKeys();
			if (generatedKeys.next()) {
				int productId = generatedKeys.getInt(1);
				pstmMovie = (JdbcPreparedStatement) conn.prepareStatement(sqlMovie);
				pstmMovie.setInt(1, productId);
				pstmMovie.setInt(2, movie.getDuration());
				pstmMovie.executeUpdate();
				System.out.println("Nova filme salvo! -> Filme: " + movie.getName());
			}
		} catch (SQLException e) {
			System.err.println(Constants.ERROR_MESSAGE_DB_OPERATION + e.getMessage());
			throw e;
		} finally {

			try {
				if (pstmProduct != null) {
					pstmProduct.close();
				}
				if (pstmMovie != null) {
					pstmMovie.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.err.println(Constants.ERROR_MESSAGE_CLOSE_CONNECTION + e.getMessage());
			}
		}
	}

	public List<Movie> findAll() throws SQLException, ClassNotFoundException, ResourceNotFoundException {
		String sql = "SELECT m.id, m.duration, p.name, p.price, p.quantity " + "FROM movie m "
				+ "INNER JOIN product p ON m.id = p.id";
		List<Movie> movies = new ArrayList<>();
		Connection conn = null;
		JdbcPreparedStatement pstm = null;
		ResultSet rset = null;
		int count = 0;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);
			rset = pstm.executeQuery();

			while (rset.next()) {
				count++;
				Movie movie = new Movie();
				movie.setId(rset.getInt("id"));
				movie.setName(rset.getString("name"));
				movie.setPrice(rset.getDouble("price"));
				movie.setQuantity(rset.getInt("quantity"));
				movie.setDuration(rset.getInt("duration"));

				movies.add(movie);
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
		return movies;
	}

	public Movie findById(int id) throws SQLException, ClassNotFoundException {
		String sql = "SELECT p.*, m.duration FROM `product` p JOIN `movie` m ON p.id = m.id WHERE p.`id` = ?";
		Movie movie = null;
		Connection conn = null;
		JdbcPreparedStatement pstm = null;
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);
			pstm.setInt(1, id);
			rset = pstm.executeQuery();

			if (rset.next()) {
				movie = new Movie();
				movie.setId(rset.getInt("id"));
				movie.setName(rset.getString("name"));
				movie.setPrice(rset.getDouble("price"));
				movie.setQuantity(rset.getInt("quantity"));
				movie.setDuration(rset.getInt("duration"));
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
		return movie;
	}

	public Movie findByName(String name) throws SQLException, ClassNotFoundException {
		String sql = "SELECT * FROM `product` WHERE `name` = ?";
		Movie movie = null;
		Connection conn = null;
		JdbcPreparedStatement pstm = null;
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, name);
			rset = pstm.executeQuery();

			if (rset.next()) {
				movie = new Movie();
				movie.setId(rset.getInt("id"));
				movie.setName(rset.getString("name"));
				movie.setPrice(rset.getDouble("price"));
				movie.setQuantity(rset.getInt("quantity"));
				movie.setDuration(rset.getInt("duration"));
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
		return movie;
	}

	public void update(Movie movie) throws SQLException, ClassNotFoundException {
		String sqlProduct = "UPDATE `product` SET `name` = ?, `price` = ?, `quantity` = ? WHERE `id` = ?";
		String sqlMovie = "UPDATE `movie` SET `duration` = ? WHERE `id` = ?";
		Connection conn = null;
		JdbcPreparedStatement pstmProduct = null;
		JdbcPreparedStatement pstmMovie = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstmProduct = (JdbcPreparedStatement) conn.prepareStatement(sqlProduct);
			pstmMovie = (JdbcPreparedStatement) conn.prepareStatement(sqlMovie);

			pstmProduct.setString(1, movie.getName());
			pstmProduct.setDouble(2, movie.getPrice());
			pstmProduct.setInt(3, movie.getQuantity());
			pstmProduct.setInt(4, movie.getId());

			pstmMovie.setInt(1, movie.getDuration());
			pstmMovie.setInt(2, movie.getId());

			conn.setAutoCommit(false);
			int rowsAffectedProduct = pstmProduct.executeUpdate();
			int rowsAffectedMovie = pstmMovie.executeUpdate();
			conn.commit();

			if (rowsAffectedProduct == 0 && rowsAffectedMovie == 0) {
				System.out.println(Constants.ERROR_MESSAGE_NOT_FOUND);
			}

			System.out.println("Filme atualizado! -> ID do filme: " + movie.getId());
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
				if (pstmMovie != null) {
					pstmMovie.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.err.println(Constants.ERROR_MESSAGE_CLOSE_CONNECTION + e.getMessage());
			}
		}
	}

	public void deleteById(int id) throws SQLException, ClassNotFoundException {
		String sql = "DELETE FROM `movie` WHERE `id` = ?";
		Connection conn = null;
		JdbcPreparedStatement pstm = null;
		ProductDAO productDAO = new ProductDAO();
		MovieDAO movieDAO = new MovieDAO();

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);
			pstm.setInt(1, id);

			int rowsAffected = pstm.executeUpdate();

			movieDAO.deleteMovieByProductId(id);
			try {
				productDAO.deleteBookById(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (rowsAffected > 0) {
				System.out.println("Filme deletado! -> ID do filme: " + id);
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

	public void deleteMovieByProductId(int productId) throws SQLException, ClassNotFoundException {
		String sql = "DELETE FROM `movie` WHERE `id` = ?";
		Connection conn = null;
		JdbcPreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);
			pstm.setInt(1, productId);

			int rowsAffected = pstm.executeUpdate();

			System.out.println(rowsAffected + " filmes deletados!");

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
