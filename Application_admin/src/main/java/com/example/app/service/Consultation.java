package com.example.app.service;

import com.example.app.db.DataBaseConnection;
import com.example.app.model.Reservation;
import com.example.app.model.Vehicule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Consultation {
	public List<Reservation> getReservations() throws ClassNotFoundException{
		List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM  reservations";
        
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Reservation reservation = new Reservation();
                reservation.setMatricule(resultSet.getString("matricule"));
                reservation.setCasaOuNon(resultSet.getString("casaOuNon"));
                reservation.setVille(resultSet.getString("ville"));
                reservation.setPrefecture(resultSet.getString("prefecture"));
                reservation.setDateReservation(resultSet.getDate("date_reservation"));
                reservation.setDateRetour(resultSet.getDate("date_retour"));
                reservation.setMutualisee(resultSet.getBoolean("mutualisée"));

                reservations.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;
	}
		
        public List<Vehicule> getVehicules() throws ClassNotFoundException {
	        List<Vehicule> vehicules = new ArrayList<>();
	        String query = "SELECT * FROM Vehicules";
	        
	        try (Connection connection = DataBaseConnection.getConnection();
	             PreparedStatement statement = connection.prepareStatement(query);
	             ResultSet resultSet = statement.executeQuery()) {

	            while (resultSet.next()) {
	                Vehicule vehicule = new Vehicule();
	                vehicule.setMatricule(resultSet.getString("matricule"));
	                vehicule.setMarque(resultSet.getString("marque"));
	                vehicule.setModele(resultSet.getString("modele"));
	                vehicule.setNombre_de_places(resultSet.getInt("nombre_de_places"));
//	                vehicule.setRéservé(resultSet.getBoolean("réservé"));
//	                vehicule.setDate_retour(resultSet.getDate("date_retour"));

	                vehicules.add(vehicule);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return vehicules;
	    }
	}

