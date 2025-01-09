package com.example.app.models;

import java.util.Date;

public class Reservation {
	private String matricule;
	private String casaOuNon;
	private String ville;
	private String prefecture;
	private Date dateReservation;
	private Date dateRetour;
	private boolean mutualisée;
	public Reservation() {
		matricule="";
		casaOuNon="";
		ville="";
		prefecture="";
	}
	public Reservation(String matricule, String casaOuNon, String ville, String region, Date dateReservation, Date dateRetour, boolean mutualisée) {
		this.matricule= matricule;
		this.casaOuNon=casaOuNon;
		this.ville=ville;
		this.prefecture=region;
		this.dateReservation=dateReservation;
		this.dateRetour=dateRetour;
		this.mutualisée= mutualisée;
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
	public boolean getMutualisée() {
		return mutualisée;
	}
	
	//Setters
	public void setMatricule(String matricule) {
		this.matricule= matricule;
	}
	public void setCasaOuNon(String casaOuNon) {
		this.casaOuNon= casaOuNon;
	}
	public void setVille(String ville) {
		this.ville= ville;
	}
	public void setRegion(String prefecture) {
		this.prefecture=prefecture;
	}
	public void setDateReservation(Date dateReservation) {
		this.dateReservation=dateReservation;
	}
	public void setDateRetour(Date dateRetour) {
		this.dateRetour= dateRetour;
	}
	public void setMutualisée(boolean mutualisée) {
		this.mutualisée=mutualisée;
	}
}

