package com.example.app.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/vehicules_db", "root", "root");
    }

}
