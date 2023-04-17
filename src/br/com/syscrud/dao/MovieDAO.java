package br.com.syscrud.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.JdbcPreparedStatement;

import br.com.syscrud.factory.ConnectionFactory;
import br.com.syscrud.model.Movie;

public class MovieDAO {

	public void save(Movie movie) {
		String sqlProduct = "INSERT INTO product (name, price, quantity) VALUES (?, ?, ?)";
		String sqlMovie = "INSERT INTO movie (id, duration) VALUES (?, ?)";

		Connection conn = null;
		JdbcPreparedStatement pstmProduct = null;
		JdbcPreparedStatement pstmMovie = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();

			pstmProduct = (JdbcPreparedStatement)  conn.prepareStatement(sqlProduct, Statement.RETURN_GENERATED_KEYS);
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
			}

			System.out.println("Novo filme salvo! -> Nome do filme: " + movie.getName());
		} catch (Exception e) {
			e.printStackTrace();
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public List<Movie> findAll() {
		String sql = "SELECT m.id, m.duration, p.name, p.price, p.quantity " +
	             "FROM movie m " +
	             "INNER JOIN product p ON m.id = p.id";
		List<Movie> movies = new ArrayList<>();
		Connection conn = null;
		JdbcPreparedStatement pstm = null;
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);
			rset = pstm.executeQuery();

			while (rset.next()) {
				Movie movie = new Movie();
				movie.setId(rset.getInt("id"));
				movie.setName(rset.getString("name"));
				movie.setPrice(rset.getDouble("price"));
				movie.setQuantity(rset.getInt("quantity"));
				movie.setDuration(rset.getInt("duration"));

				movies.add(movie);
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
		return movies;
	}

	public Movie findById(int id) {
		String sql = "SELECT * FROM `product` WHERE `id` = ?";
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
		return movie;
	}
	
	public Movie findByName(String name) {
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
	    return movie;
	}
	
	public void update(Movie movie) {
	    String sql = "UPDATE `product` SET `name` = ?, `price` = ?, `quantity` = ?, `duration` = ? WHERE `id` = ?";
	    Connection conn = null;
	    JdbcPreparedStatement pstm = null;

	    try {
	        conn = ConnectionFactory.createConnectionToMySQL();
	        pstm = (JdbcPreparedStatement) conn.prepareStatement(sql);

	        pstm.setString(1, movie.getName());
	        pstm.setDouble(2, movie.getPrice());
	        pstm.setInt(3, movie.getQuantity());
	        pstm.setInt(4, movie.getDuration());
	        pstm.setInt(5, movie.getId());

	        System.out.println("Movie updated! -> Movie ID: " + movie.getId());
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
	        
	        System.out.println("Movie deleted! -> Movie ID: " + id);
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

