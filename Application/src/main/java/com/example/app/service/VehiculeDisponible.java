package com.example.app.service;

import com.example.app.db.DataBaseConnection;
import com.example.app.models.Vehicule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehiculeDisponible {
	 public List<Vehicule> getVehiculesDisponible() throws ClassNotFoundException {
	        List<Vehicule> vehicules = new ArrayList<>();
	        String query = "SELECT * FROM Vehicules WHERE réservé = false";
	        
	        try (Connection connection = DataBaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

	            while (resultSet.next()) {
	                Vehicule vehicule = new Vehicule();
	                vehicule.setMatricule(resultSet.getString("matricule"));
	                vehicule.setMarque(resultSet.getString("marque"));
	                vehicule.setModele(resultSet.getString("modele"));
	                vehicule.setNombre_de_places(resultSet.getInt("nombre_de_places"));
	                vehicule.setRéservé(resultSet.getBoolean("réservé"));
	                vehicule.setDate_retour(resultSet.getDate("date_retour"));

	                vehicules.add(vehicule);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return vehicules;
	    }
	 public List<Vehicule> getVehiculesReserves() throws ClassNotFoundException {
		 List<Vehicule> vehicules = new ArrayList<>();
	        String query = "SELECT * FROM Vehicules WHERE réservé = true";
	        
	        try (Connection connection = DataBaseConnection.getConnection();
	             PreparedStatement statement = connection.prepareStatement(query);
	             ResultSet resultSet = statement.executeQuery()) {

	            while (resultSet.next()) {
	                Vehicule vehicule = new Vehicule();
	                vehicule.setMatricule(resultSet.getString("matricule"));
	                vehicule.setMarque(resultSet.getString("marque"));
	                vehicule.setModele(resultSet.getString("modele"));
	                vehicule.setNombre_de_places(resultSet.getInt("nombre_de_places"));
	                vehicule.setRéservé(resultSet.getBoolean("réservé"));
	                vehicule.setDate_retour(resultSet.getDate("date_retour"));
	                vehicules.add(vehicule);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return vehicules;
	    }
}

