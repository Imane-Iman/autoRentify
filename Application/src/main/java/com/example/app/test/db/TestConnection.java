package com.example.app.test.db;

import com.example.app.db.DataBaseConnection;

import java.sql.Connection;
import java.sql.SQLException;


public class TestConnection {
	public static void main(String[] args) throws ClassNotFoundException {
        try {
            Connection connection = DataBaseConnection.getConnection();
            if (connection != null) {
                System.out.println("Connexion réussie !");
                connection.close();
            } else {
                System.out.println("Échec de la connexion !");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
