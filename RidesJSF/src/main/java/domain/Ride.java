package domain;

import java.io.*;
import java.util.Date;

import javax.persistence.*;



@SuppressWarnings("serial")

@Entity
public class Ride implements Serializable {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rideNumber;
	private String froma;
	private String toa;
	private int nPlaces;
	private Date date;
	private float price;
	@ManyToOne(targetEntity=Driver.class, cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
	private Driver driver;  
	
	public Ride(){
		super();
	}
	
	public Ride(Long rideNumber, String froma, String toa, Date date, int nPlaces, float price, Driver driver) {
		super();
		this.rideNumber = rideNumber;
		this.froma = froma;
		this.toa = toa;
		this.nPlaces = nPlaces;
		this.date=date;
		this.price=price;
		this.driver = driver;
	}

	

	public Ride(String froma, String toa,  Date date, int nPlaces, float price, Driver driver) {
		super();
		this.froma = froma;
		this.toa = toa;
		this.nPlaces = nPlaces;
		this.date=date;
		this.price=price;
		this.driver = driver;
	}
	
	/**
	 * Get the  number of the ride
	 * 
	 * @return the ride number
	 */
	public Long  getRideNumber() {
		return rideNumber;
	}

	
	/**
	 * Set the ride number toa a ride
	 * 
	 * @param ride Number toa be set	 */
	
	public void setRideNumber(Long  rideNumber) {
		this.rideNumber = rideNumber;
	}


	/**
	 * Get the origin  of the ride
	 * 
	 * @return the origin location
	 */

	public String getFroma() {
		return froma;
	}


	/**
	 * Set the origin of the ride
	 * 
	 * @param origin toa be set
	 */	
	
	public void setFroma(String origin) {
		this.froma = origin;
	}

	/**
	 * Get the destination  of the ride
	 * 
	 * @return the destination location
	 */

	public String getToa() {
		return toa;
	}


	/**
	 * Set the origin of the ride
	 * 
	 * @param destination to be set
	 */	
	public void setToa(String destination) {
		this.toa = destination;
	}

	/**
	 * Get the free places of the ride
	 * 
	 * @return the available places
	 */
	
	/**
	 * Get the date  of the ride
	 * 
	 * @return the ride date 
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * Set the date of the ride
	 * 
	 * @param date toa be set
	 */	
	public void setDate(Date date) {
		this.date = date;
	}

	
	public float getnPlaces() {
		return nPlaces;
	}

	/**
	 * Set the free places of the ride
	 * 
	 * @param  nPlaces places to be set
	 */

	public void setBetMinimum(int nPlaces) {
		this.nPlaces = nPlaces;
	}

	/**
	 * Get the driver associated to the ride
	 * 
	 * @return the associated driver
	 */
	public Driver getDriver() {
		return driver;
	}

	/**
	 * Set the driver associated to the ride
	 * 
	 * @param driver to associate to the ride
	 */
	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}



	public String toString(){
		return rideNumber+";"+";"+froma+";"+toa+";"+date;  
	}




	
}
