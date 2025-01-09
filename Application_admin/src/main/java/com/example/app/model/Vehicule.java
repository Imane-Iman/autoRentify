package com.example.app.model;

import java.util.Date;

public class Vehicule {
    private String matricule;
    private String marque;
    private String modele;
    private int nombre_de_places;
    private boolean réservé;
    private Date date_retour;

    public Vehicule(String matricule, String marque, String modele, int nombre_de_places, boolean réservé, Date date_retour) {
        this.matricule = matricule;
        this.marque = marque;
        this.modele = modele;
        this.nombre_de_places = nombre_de_places;
        this.réservé = réservé;
        this.date_retour = date_retour;
    }
    public Vehicule() {
    	matricule = " ";
    	marque = " ";
    	modele  = " ";
    	nombre_de_places = 0;
    	réservé = true;
    }

    // Getters
    public String getMatricule() {
        return matricule;
    }

    public String getMarque() {
        return marque;
    }

    public String getModele() {
        return modele;
    }

    public int getNombre_de_places() {
        return nombre_de_places;
    }

    public boolean isRéservé() {
        return réservé;
    }

    public Date getDate_retour() {
        return date_retour;
    }

    // Setters
    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public void setNombre_de_places(int nombre_de_places) {
        this.nombre_de_places = nombre_de_places;
    }

    public void setRéservé(boolean disponible) {
        this.réservé = disponible;
    }

    public void setDate_retour(Date date_retour) {
        this.date_retour = date_retour;
    }
}

