package app.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {

	private static final String username = "root";
	private static final String password = "david";
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/inventory";

	public Connection getConnection() throws ClassNotFoundException, SQLException {

		Connection con = DriverManager.getConnection(DB_URL,username,password);
		return con;

	}
}
