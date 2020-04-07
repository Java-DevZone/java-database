package com.javadevzone.javadb.repository;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	private static final String PASSWORD = "123456";
	private static final String USER = "root";
	private static final String URL = "jdbc:mysql://localhost:3306/curso-java";
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
