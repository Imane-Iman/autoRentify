package com.example.app.controller;

import com.example.app.service.Consultation;
import com.example.app.model.Reservation;
import com.example.app.model.Vehicule;
import com.example.app.db.DataBaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class SampleController implements Initializable{
	@FXML
	private Button button1;
	@FXML
	private Button button2;
	@FXML
	private Button button3;
	@FXML
	private Button button4;
	@FXML
	private Button button5;
	@FXML
    private TableView<Reservation> tabReservations;
    @FXML
    private TableColumn<Reservation, String> colMatricule;
    @FXML
    private TableColumn<Reservation, String> colCasaOuNon;
    @FXML
    private TableColumn<Reservation, String> colVille;
    @FXML
    private TableColumn<Reservation, Integer> ColPréfecture;
    @FXML
    private TableColumn<Reservation, java.sql.Date> colDateReser;
    @FXML
    private TableColumn<Reservation, java.sql.Date> colDateRet;
    @FXML
    private TableColumn<Reservation, Boolean> colMutualisée;
    
    @FXML
    private TableView<Vehicule> tabVehicules;
    @FXML
    private TableColumn<Vehicule, String> colmatricule;
    @FXML
    private TableColumn<Vehicule, String> colMarque;
    @FXML
    private TableColumn<Vehicule, String> colModele;
    @FXML
    private TableColumn<Vehicule, Integer> colNombre_places;
    @FXML
    private TextField textMatricule4;
    @FXML
    private TextField textMarque4;
    @FXML
    private TextField textModele4;
    @FXML
    private TextField textNmbPlaces4;
    @FXML
    private TextField text5;
    @FXML
    private TextField textMatricule6;
    @FXML
    private TextField textNombre6;
    
	private Stage stage;
    private Scene scene;
    private Parent root;
	
	 public void switchToScene1(ActionEvent e) throws IOException {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/app/Sample.fxml"));
	        root = loader.load();
	        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
	        scene = new Scene(root);
	        stage.setScene(scene);
			stage.setResizable(false);
	        stage.show();
	    }
	    
	    public void switchToScene2(ActionEvent e) throws IOException {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/app/Sample2.fxml"));
	        root = loader.load();
	        SampleController controller = loader.getController();
	        controller.initializeTableView2();
	        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
	        scene = new Scene(root);
	        stage.setScene(scene);
	        stage.setResizable(false);
	        stage.show();
	    }
	    
	    public void switchToScene3(ActionEvent e) throws IOException {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/app/Sample3.fxml"));
	        root = loader.load();
	        SampleController controller = loader.getController();
	        controller.initializeTableView();
	        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
	        scene = new Scene(root);
	        stage.setScene(scene);
	        stage.setResizable(false);
	        stage.show();
	    } public void switchToScene4(ActionEvent e) throws IOException {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/app/Sample4.fxml"));
	        root = loader.load();
	        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
	        scene = new Scene(root);
	        stage.setScene(scene);
			stage.setResizable(false);
	        stage.show();
	    }
	    
	    public void switchToScene5(ActionEvent e) throws IOException {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("Sample5.fxml"));
	        root = loader.load();
	        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
	        scene = new Scene(root);
	        stage.setScene(scene);
	        stage.setResizable(false);
	        stage.show();
	    }
	    public void switchToScene6(ActionEvent e) throws IOException {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/app/Sample6.fxml"));
	        root = loader.load();
	        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
	        scene = new Scene(root);
	        stage.setScene(scene);
	        stage.setResizable(false);
	        stage.show();
	    }
	    
	    public void initializeTableView() {
	    	if (colmatricule != null) colmatricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));       
	        if (colModele != null) colModele.setCellValueFactory(new PropertyValueFactory<>("modele"));        
	        if (colMarque != null) colMarque.setCellValueFactory(new PropertyValueFactory<>("marque"));        
	        if (colNombre_places != null) colNombre_places.setCellValueFactory(new PropertyValueFactory<>("nombre_de_places")); 
	    }
	    
	    public void initializeTableView2() {
	        if (colMatricule != null) colMatricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
	        if (colCasaOuNon != null) colCasaOuNon.setCellValueFactory(new PropertyValueFactory<>("casaOuNon"));
	        if (colVille != null) colVille.setCellValueFactory(new PropertyValueFactory<>("ville"));
	        if (ColPréfecture != null) ColPréfecture.setCellValueFactory(new PropertyValueFactory<>("prefecture"));
	        if (colDateReser != null) colDateReser.setCellValueFactory(new PropertyValueFactory<>("dateReservation"));
	        if (colDateRet != null) colDateRet.setCellValueFactory(new PropertyValueFactory<>("dateRetour"));
	        if (colMutualisée != null) colMutualisée.setCellValueFactory(new PropertyValueFactory<>("mutualisee"));
	    }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	 private void showAlerts(AlertType alertType, String title, String text) {
	    	Alert alert = new Alert(alertType);
	    	alert.setTitle(title);
	    	alert.setContentText(text);
	    	alert.showAndWait();
	    }
	 public void consulterReservations(ActionEvent e) throws ClassNotFoundException {
	            Consultation consultation = new Consultation();
	            List<Reservation> reservations = consultation.getReservations();
	            if (reservations.isEmpty()) {
	                showAlerts(AlertType.WARNING, "Aucune réservation", "Aucune réservation n'est enregistrée.");
	                return;
	            }
	            ObservableList<Reservation> listReservations = FXCollections.observableArrayList(reservations);
	            tabReservations.setItems(listReservations);
	            tabReservations.setVisible(true);
	   }
	public void consulterVehicules(ActionEvent e) throws ClassNotFoundException {
		Consultation consultation = new Consultation();
		List<Vehicule> vehicules = consultation.getVehicules();
		if (vehicules.isEmpty()) {
			showAlerts(AlertType.WARNING,"Aucun véhicule","Aucun véhicule n'est enregistrée.");
			return;
		}
		ObservableList<Vehicule> listVehicules = FXCollections.observableArrayList(vehicules);
		tabVehicules.setItems(listVehicules);
		tabVehicules.setVisible(true);
	}
	private boolean matriculeExisteDeja(Connection connection, String matricule) throws SQLException {
        String query = "SELECT COUNT(*) FROM vehicules WHERE matricule = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, matricule);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        }
        return false;
    }
	@FXML
    private void ajouterVehicule(ActionEvent event) {
        String matricule = textMatricule4.getText();
        String marque = textMarque4.getText();
        String modele = textModele4.getText();
        String nombreDePlacesText = textNmbPlaces4.getText();
        int nombreDePlaces;

        if (matricule.isEmpty() || marque.isEmpty() || modele.isEmpty() || nombreDePlacesText.isEmpty()) {
            showAlerts(AlertType.WARNING, "Données manquantes", "Veuillez remplir tous les champs.");
            return;
        }

        try {
            nombreDePlaces = Integer.parseInt(nombreDePlacesText);
        } catch (NumberFormatException e) {
            showAlerts(AlertType.WARNING, "Données incorrectes", "Le nombre de places doit être un entier.");
            return;
        }

        try (Connection connection = DataBaseConnection.getConnection()) {
            if (matriculeExisteDeja(connection, matricule)) {
                showAlerts(AlertType.WARNING, "Matricule existante", "La matricule saisie est déjà enregistrée.");
                return;
            }

            String query = "INSERT INTO vehicules (matricule, marque, modele, nombre_de_places,réservé) VALUES (?, ?, ?, ?,false)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, matricule);
                statement.setString(2, marque);
                statement.setString(3, modele);
                statement.setInt(4, nombreDePlaces);
                statement.executeUpdate();

                showAlerts(AlertType.INFORMATION, "Succès", "Véhicule ajouté avec succès.");
                textMatricule4.clear();
                textMarque4.clear();
                textModele4.clear();
                textNmbPlaces4.clear();
            }
        } catch (SQLException | ClassNotFoundException e) {
            showAlerts(AlertType.ERROR, "Erreur", "Une erreur s'est produite lors de l'ajout du véhicule.");
            e.printStackTrace();
        }
    }
	@FXML
	public void supprimerVehicule(ActionEvent e) {
		String matricule = text5.getText();
		if (matricule.isEmpty()) {
			showAlerts(AlertType.WARNING,"Données manquantes","Veuillez saisir la matricule");
			return;
		}
		try(Connection connection = DataBaseConnection.getConnection()){
			String query = "DELETE FROM vehicules WHERE matricule = ?";
			try(PreparedStatement statement = connection.prepareStatement(query)){
				statement.setString(1, matricule);
				int ligneTrouvée = statement.executeUpdate();
				if (ligneTrouvée>0) {
					showAlerts(AlertType.INFORMATION,"Succès","Véhicule ayant la matricule: "+matricule+" était supprimé avec succès de la base de données");
					text5.clear();
				}
				else {
					showAlerts(AlertType.WARNING,"Non trouvé","Aucun véhicule n'est trouvé dans la base de données avec la matricule: "+matricule);
				}
			}
			
		} catch (ClassNotFoundException e1) {
			showAlerts(AlertType.ERROR, "Erreur", "Une erreur s'est produite lors de la suppression du véhicule.");
			e1.printStackTrace();
		} catch (SQLException e1) {
			showAlerts(AlertType.ERROR, "Erreur", "Une erreur s'est produite lors de la suppression du véhicule.");
			e1.printStackTrace();
		}
	}
	@FXML
	 public void annulerReservation(ActionEvent e) {
        String matricule = textMatricule6.getText();
        String nombrePlacesString = textNombre6.getText();
        int nombrePlaces;

        if (matricule.isEmpty() || nombrePlacesString.isEmpty()) {
            showAlerts(AlertType.WARNING, "Données manquantes", "Veuillez remplir tous les champs.");
            return;
        }

        try {
            nombrePlaces = Integer.parseInt(nombrePlacesString);
        } catch (NumberFormatException ex) {
            showAlerts(AlertType.WARNING, "Données incorrectes", "Le nombre de places doit être un entier.");
            return;
        }

        try (Connection connection = DataBaseConnection.getConnection()) {
            connection.setAutoCommit(false);

            // Vérification de l'existence de la réservation
            String checkQuery = "SELECT COUNT(*) FROM reservations WHERE matricule = ?";
            try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {
                checkStatement.setString(1, matricule);
                try (ResultSet rs = checkStatement.executeQuery()) {
                    if (rs.next() && rs.getInt(1) == 0) {
                        showAlerts(AlertType.WARNING, "Aucune réservation", "Aucune réservation trouvée avec cette matricule.");
                        connection.rollback();
                        return;
                    }
                }
            }

            // Première commande SQL : suppression de la réservation
            String deleteQuery = "DELETE FROM reservations WHERE matricule = ?";
            try (PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
                deleteStatement.setString(1, matricule);
                deleteStatement.executeUpdate();
            }

            // Deuxième commande SQL : mise à jour du véhicule
            String updateQuery = "UPDATE vehicules SET date_retour = NULL, réservé = false, nombre_de_places = ? WHERE matricule = ?";
            try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                updateStatement.setInt(1, nombrePlaces);
                updateStatement.setString(2, matricule);
                updateStatement.executeUpdate();
            }

            connection.commit();
            showAlerts(AlertType.INFORMATION, "Succès", "Réservation annulée avec succès.");
            textMatricule6.clear();
            textNombre6.clear();

        } catch (SQLException | ClassNotFoundException ex) {
            showAlerts(AlertType.ERROR, "Erreur", "Une erreur s'est produite lors de l'annulation de la réservation.");
            ex.printStackTrace();
        }
    }

}