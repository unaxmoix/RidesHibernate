package domain;

import java.io.*;
import java.util.Date;
import java.util.ResourceBundle;

import javax.persistence.*;





@Entity
public class Erreserba implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer erreserbaNumber;
	private Ride ride;
	private int places;
	private boolean onartua;
	
	private Traveler traveler;  
	
	public Erreserba(Ride ride, int places, Traveler traveler) {
		this.ride=ride;
		this.places=places;
		this.traveler = traveler;
		this.onartua = false;
	}
	
	public Erreserba() {
		
	}

	
	public Integer getErreserbaNumber() {
		return erreserbaNumber;
	}

	
	
	public void setErreserbaNumber(Integer erreserbaNumber) {
		this.erreserbaNumber = erreserbaNumber;
	}

	public int getPlaces() {
		return places;
	}

	public void setPlaces(int places) {
		this.places = places;
	}

	public Traveler getTraveler() {
		return traveler;
	}

	public void setTraveler(Traveler traveler) {
		this.traveler = traveler;
	}
	
	public boolean isOnartua() {
		return onartua;
	}

	public void setOnartua(boolean onartua) {
		this.onartua = onartua;
	}

	public Ride getRide() {
		return ride;
	}

	public void setRide(Ride ride) {
		this.ride = ride;
	}
	private static String etik = "Etiquetas";
	
}
