package com.myClass.ConnectDatabase;

import java.sql.Connection;
import java.sql.DriverManager;



public class MyConnect {
	private final static String URL="jdbc:mysql://localhost:3306/crm";
	private final static String USER="root";
	private final static String PASSWORD="Goboi123";
	public static Connection getConnect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(URL, USER, PASSWORD);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
}
