package com.demo.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbUtil {
	private static Connection connection = null;

	public static Connection getConnection() {
		invalidateConnection();
		System.out.println("Acquiring a new DB connection");
		try {
			Properties prop = new Properties();
			InputStream inputStream = DbUtil.class.getClassLoader().getResourceAsStream("/db.properties");
			prop.load(inputStream);
			String driver = prop.getProperty("driver");
			String url = prop.getProperty("url");
			if (url.indexOf("MYSQL_PRIVATE_IP") > 0) {
				// replacing this with localhost
				url = url.replaceAll("MYSQL_PRIVATE_IP", "localhost");
			}
			String user = prop.getProperty("user");
			String password = prop.getProperty("password");
			Class.forName(driver);
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("Database connection established ");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("1. errorcode: " + e.getErrorCode() + " sqlstate: " + e.getSQLState() + " message: "
					+ e.getMessage());
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("2. message: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("3. message: " + e.getMessage());
			e.printStackTrace();
		}
		return connection;
	}

	public static void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
				connection = null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			}
		}
	}

	public static void invalidateConnection() {
		if (connection == null) {
			System.out.println("Connection is null.. returning");
			return;
		}

		System.out.println("Invalidating the connection...");
		try {
			connection.close();
			System.out.println("Closed the connection...");
		} catch (SQLException e) {
			System.out.println("Excepton during close of connection... " + e.toString());
			e.printStackTrace();
		}
		connection = null;
	}
}
