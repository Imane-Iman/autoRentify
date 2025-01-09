package com.example.app.service;

import com.example.app.db.DataBaseConnection;
import com.example.app.models.Reservation;
import com.example.app.models.Vehicule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class VehiculeDR {
	 
	 //Méthode pour trouver une voiture disponible pour une destination donnée:
	 public Vehicule getVehiculeDisponiblePourDestination() throws ClassNotFoundException {
	        String query = "SELECT * FROM Vehicules WHERE réservé = false LIMIT 1";

	        try (Connection connection = DataBaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {

	            ResultSet resultSet = statement.executeQuery();

	            if (resultSet.next()) {
	                Vehicule vehicule = new Vehicule();
	                vehicule.setMatricule(resultSet.getString("matricule"));
	                vehicule.setMarque(resultSet.getString("marque"));
	                vehicule.setModele(resultSet.getString("modele"));
	                vehicule.setNombre_de_places(resultSet.getInt("nombre_de_places"));
	                vehicule.setRéservé(resultSet.getBoolean("réservé"));
	                vehicule.setDate_retour(resultSet.getDate("date_retour"));

	                return vehicule;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	 // Méthode pour réserver une voiture qui se dirige à la meme destination
	 public Vehicule getVehiculePartageable(String prefecture,String ville, Date dateReservation) {
		 String query= "SELECT * FROM vehicules v\r\n"
		 		+ "NATURAL JOIN reservations r\r\n"
		 		+ "WHERE v.réservé=true\r\n"
		 		+ "AND r.date_reservation=? AND v.nombre_de_places>0 AND mutualisée=true \r\n"
		 		+ "AND r.ville= ?\r\n"
		 		+ "AND r.prefecture = ?\r\n"
		 		+ "LIMIT 1;";
		 try(Connection connection = DataBaseConnection.getConnection();
		 PreparedStatement statment = connection.prepareStatement(query)){
			 statment.setDate(1, new java.sql.Date(dateReservation.getTime()));
			 statment.setString(2,ville);
			 statment.setString(3,prefecture);
			 ResultSet resultSet = statment.executeQuery();
			 if (resultSet.next()) {
				 Vehicule vehicule = new Vehicule();
				 vehicule.setMatricule(resultSet.getString("matricule"));
				 vehicule.setModele(resultSet.getString("modele"));
				 vehicule.setMarque(resultSet.getString("marque"));
				 vehicule.setDate_retour(resultSet.getDate("date_retour"));
				 vehicule.setNombre_de_places(resultSet.getInt("nombre_de_places"));
				 vehicule.setRéservé(resultSet.getBoolean("réservé"));
				 return vehicule;
			 }
		 }catch(SQLException e) {
			 e.printStackTrace();
		 } catch (ClassNotFoundException e) {
             throw new RuntimeException(e);
         }
         return null;
	 }
	// Méthode pour effectuer une reservation
	public boolean reserverVehicule(Reservation reservation) throws ClassNotFoundException {
		String query = "INSERT INTO reservations(matricule,casaOuNon,ville,prefecture, date_reservation, date_retour,mutualisée) VALUES(?,?,?,?,?,?,?) ";
		String UpdateVehicules = "UPDATE vehicules SET réservé=true, date_retour=?,nombre_de_places=nombre_de_places-1 WHERE matricule= ? ";
		try(Connection connection = DataBaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query);
				PreparedStatement updateStatement = connection.prepareStatement(UpdateVehicules)){
			statement.setString(1, reservation.getMatricule());
			statement.setString(2, reservation.getCasaOuNon());
			statement.setString(3, reservation.getVille());
			statement.setString(4, reservation.getPrefecture());
			statement.setDate(5, new java.sql.Date (reservation.getDateReservation().getTime()));
			statement.setDate(6, new java.sql.Date (reservation.getDateRetour().getTime()));
			statement.setBoolean(7, reservation.getMutualisée());
			int lignesinseres = statement.executeUpdate();
			if (lignesinseres>0) {
				updateStatement.setDate(1, new java.sql.Date (reservation.getDateRetour().getTime()));
				updateStatement.setString(2, reservation.getMatricule());
				updateStatement.executeUpdate();
				return true;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}

