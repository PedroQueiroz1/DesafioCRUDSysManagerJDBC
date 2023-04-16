package br.com.syscrud.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	private static final String USERNAME = "root";
	
	private static final String PASSWORD = "admin123";
	
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/sysmanager_crud_jdbc";
	
	
	// Conexão com o banco de dados
	public static Connection createConnectionToMySQL() throws Exception{
		
		//Faz com que a classe seja carregada pela JVM
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		String characterEncoding = "UTF-8";
		String url = DATABASE_URL + "?characterEncoding=" + characterEncoding;
		
		//Cria conexão com o banco
		Connection connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
		
		return connection;
	}
	
	public static void main(String[] args) throws Exception {
		
		//Recuperar conexão com o banco
		Connection con = createConnectionToMySQL();
		
		if(con!=null) {
			System.out.println("Conexão obtida com sucesso!");
			con.close();
		}
	}
}
