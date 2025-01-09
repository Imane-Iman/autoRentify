package com.example.app.model;

import java.util.Date;

public class Reservation {
    private String matricule;
    private String casaOuNon;
    private String ville;
    private String prefecture;
    private Date dateReservation;
    private Date dateRetour;
    private boolean mutualisee;

    public Reservation() {
        this.matricule = "";
        this.casaOuNon = "";
        this.ville = "";
        this.prefecture = "";
    }

    public Reservation(String matricule, String casaOuNon, String ville, String prefecture, Date dateReservation, Date dateRetour, boolean mutualisee) {
        this.matricule = matricule;
        this.casaOuNon = casaOuNon;
        this.ville = ville;
        this.prefecture = prefecture;
        this.dateReservation = dateReservation;
        this.dateRetour = dateRetour;
        this.mutualisee = mutualisee;
    }

    // Getters
    public String getMatricule() {
        return matricule;
    }

    public String getCasaOuNon() {
        return casaOuNon;
    }

    public String getVille() {
        return ville;
    }

    public String getPrefecture() {
        return prefecture;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public Date getDateRetour() {
        return dateRetour;
    }

    public boolean isMutualisee() {
        return mutualisee;
    }

    // Setters
    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setCasaOuNon(String casaOuNon) {
        this.casaOuNon = casaOuNon;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setPrefecture(String prefecture) {
        this.prefecture = prefecture;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }

    public void setMutualisee(boolean mutualisee) {
        this.mutualisee = mutualisee;
    }
}
