package com.example.app.controller;

import com.example.app.models.Reservation;
import com.example.app.models.Vehicule;
import com.example.app.service.VehiculeDR;
import com.example.app.service.VehiculeDisponible;
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
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class SampleController implements Initializable {
    
    @FXML
    private ChoiceBox<String> préfecture;
    @FXML
    private ChoiceBox<String> ville;
    @FXML
    private TextField autreVille;
    @FXML
    private DatePicker dateReservation;
    @FXML
    private DatePicker dateRetour;
    @FXML
    private ToggleGroup type;
    @FXML
    private ToggleGroup destination;
    @FXML
    private Button reserverButton;
    @FXML
    private Button retourButton;
    @FXML
    private RadioButton OuiButton;
    @FXML
    private RadioButton NonButton;
    @FXML
    private Label labelVille;
    @FXML
    private Label labelPrefecture;
    
    private VehiculeDR vehiculeDR;
    @FXML
    private TableView<Vehicule> tabVehicules;
    @FXML
    private TableColumn<Vehicule, String> colMatricule;
    @FXML
    private TableColumn<Vehicule, String> colModele;
    @FXML
    private TableColumn<Vehicule, String> colMarque;
    @FXML
    private TableColumn<Vehicule, Integer> ColNombrePlaces;
    @FXML
    private TableColumn<Vehicule, Date> colDateRetour;
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    
    private ObservableList<String> prefectures = FXCollections.observableArrayList("Anfa","Sidi Bernoussi","Ain Sbaa Hay Mohammedi","Al Fida Mers Sultan","Moulay Rachid","Ain Chock","Ben M'Sick Sidi Othmane","Hay Hassani");
    private ObservableList<String> villes = FXCollections.observableArrayList("Agadir","Al Hoceima","Beni Mellal","Boujdour","Berrechid","Bouskoura","Casablanca","Chefchaouen","Dakhla","El Jadida","Errachidia","Essaouira","Fès","Guelmim","Ifrane","Kénitra","Khouribga","Laayoune","Larache","Marrakech","Meknès","Mohammédia","Nador","Nouaceur","Ouarzazate","Oujda","Rabat","Safi","Salé","Settat","Tanger","Tétouan","Tan-Tan","Taza","Youssoufia","Zagora","Autre..");
   

    
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
        controller.initializeTableView();
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
        controller.initializeChoiceBox(); 
        controller.initializeRadioButtonListeners();
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	vehiculeDR = new VehiculeDR();
    	
     }

    
    public void initializeChoiceBox() {
        if (préfecture != null) {
            préfecture.setItems(prefectures);
        }
        if (ville != null) {
            ville.setItems(villes);
            ville.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null && newValue.equals("Autre..")) {
                    autreVille.setVisible(true);
                } else {
                    autreVille.setVisible(false);
                }
            });
        }
    }

    private void initializeRadioButtonListeners() {
        destination.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == OuiButton) {
                préfecture.setVisible(true);
                labelPrefecture.setVisible(true);
                ville.setVisible(false);
                labelVille.setVisible(false);
                autreVille.setVisible(false);
            } else if (newValue == NonButton) {
                préfecture.setVisible(false);
                labelPrefecture.setVisible(false);
                ville.setVisible(true);
                labelVille.setVisible(true);
            }
        });
    }
    public void initializeTableView() {
    	if (colMatricule != null) colMatricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));       
        if (colModele != null) colModele.setCellValueFactory(new PropertyValueFactory<>("modele"));        
        if (colMarque != null) colMarque.setCellValueFactory(new PropertyValueFactory<>("marque"));        
        if (ColNombrePlaces != null) ColNombrePlaces.setCellValueFactory(new PropertyValueFactory<>("nombre_de_places")); 
        if (colDateRetour != null) colDateRetour.setCellValueFactory(new PropertyValueFactory<>("date_retour"));
    }
    public void consulterVehicules(ActionEvent e) throws ClassNotFoundException {
    	VehiculeDisponible vehiucleDisponible = new VehiculeDisponible();
    	List<Vehicule> vehicules = vehiucleDisponible.getVehiculesDisponible();
    	if (vehicules.isEmpty()) {
    		showAlertConsultationReservation();
    		vehicules = vehiucleDisponible.getVehiculesReserves();
    	}
    	else{
    		vehicules = vehiucleDisponible.getVehiculesDisponible();
    	}
    	ObservableList<Vehicule> listVehicules = FXCollections.observableArrayList(vehicules);
    	tabVehicules.setItems(listVehicules);
    	tabVehicules.setVisible(true);
    }  
    private void showAlertConsultationReservation() {
    	Alert alert = new Alert(AlertType.WARNING);
    	alert.setTitle("Tous les véhicules sont réservés");
    	alert.setHeaderText(null);
    	alert.setContentText("Tous les véhicules sont actuellement réservés. Une réservation mutualisée peut être permise.");
    	alert.showAndWait();
    }
    private void showAlerts(AlertType alertType, String title, String text) {
    	Alert alert = new Alert(alertType);
    	alert.setTitle(title);
    	alert.setContentText(text);
    	alert.showAndWait();
    }
    public void effectuerReservation(ActionEvent e) {
    	try {
    		Date dateRes = Date.valueOf(dateReservation.getValue());
    		Date dateRet = Date.valueOf(dateRetour.getValue());
    		if (dateRet.before(dateRes)) {
    			showAlerts(AlertType.ERROR,"Erreur","La date de retour ne peut pas être inférieure à la date de réservation.");
    			return;
    		}
    		 String casaOuNon = ((RadioButton) destination.getSelectedToggle()).getText().toLowerCase();
    		 String prefecture = "";
    		 String villle ="";
    		 if (casaOuNon.equals("oui")) {
    			 prefecture = préfecture.getValue();
    			 villle = "Casablanca";
    		 }
    		 else {
    			 if (ville.getValue().equals("Autre..")) {
    				 villle = autreVille.getText();
    			 }
    			 else {
    				 villle= ville.getValue();
    			 }
    			 
    		 }
    		 boolean mutualisée = ((RadioButton) type.getSelectedToggle()).getText().equals("Mutualisée");
    		 Vehicule vehicule = null;
    		 if (mutualisée) {
    			 vehicule= vehiculeDR.getVehiculePartageable(prefecture,villle,dateRes);
    			 if (vehicule == null) {
 					vehicule = vehiculeDR.getVehiculeDisponiblePourDestination();
 				}
    		 }else {
    			 vehicule = vehiculeDR.getVehiculeDisponiblePourDestination();
    		 }
    		 
    		if (vehicule != null) {
    			Reservation reservation = new Reservation(vehicule.getMatricule(), casaOuNon, villle, prefecture, dateRes, dateRet, mutualisée);
    			boolean success = vehiculeDR.reserverVehicule(reservation);
                if (success) {
                	String message = "Réservation réussie pour le véhicule :\n" +
                            "Matricule : " + vehicule.getMatricule() + "\n" +
                            "Marque : " + vehicule.getMarque() + "\n" +
                            "Modèle : " + vehicule.getModele();
                	showAlerts(AlertType.INFORMATION, "Succès", message);
                }
                else {
                    showAlerts(AlertType.ERROR, "Erreur", "Un problème est survenu lors de la réservation.");
                }
    		}else {
    			showAlerts(AlertType.WARNING,"Aucun véhicule disponible","Aucun véhicule n'est disponible pour la réservation.");
    		}
    	}catch(Exception exp) {
    		exp.printStackTrace();
    		showAlerts(AlertType.ERROR,"Erreur","Un problème a été survenue lors de la réservation, veuillez réssayer");
    	}
    	
    }
    
}




