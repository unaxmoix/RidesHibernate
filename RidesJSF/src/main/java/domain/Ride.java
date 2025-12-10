package domain;
import businessLogic.*;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.persistence.*;


import eredua.bean.FacadeBean;



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
	private boolean bukatuta;
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
	private List<Balorazioa> balorazioList = new Vector<Balorazioa>();
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
	private List<Erreklamazioa> erreklamazioList = new Vector<Erreklamazioa>();


	private float price;
	@ManyToOne(targetEntity=Driver.class, cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
	private Driver driver;  
	private double irabaziak;
	
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
		this.irabaziak=0.0;
	}
	
	/**
	 * Get the  number of the ride
	 * 
	 * @return the ride number
	 */
	public Long  getRideNumber() {
		return rideNumber;
	}

	public void removeBalorazioa(Balorazioa b) {
		balorazioList.remove(b);
	}
	public List<Balorazioa> getBalorazioList() {
		return balorazioList;
	}

	public void setBalorazioList(List<Balorazioa> balorazioList) {
		this.balorazioList = balorazioList;
	}

	public List<Erreklamazioa> getErreklamazioList() {
		return erreklamazioList;
	}

	public void setErreklamazioList(List<Erreklamazioa> erreklamazioList) {
		this.erreklamazioList = erreklamazioList;
	}

	/**
	 * Set the ride number toa a ride
	 * 
	 * @param ride Number toa be set	 */
	
	public void setRideNumber(Long  rideNumber) {
		this.rideNumber = rideNumber;
	}
	
	public double irabaziak() {
		int p=0;
		BLFacade facade=FacadeBean.getBusinessLogic();
		for(Erreserba er: facade.getAllErreserbakD(driver)) {
			if(er.getRide().getRideNumber()==rideNumber && er.isOnartua()) {
				p+=er.getPlaces();
			}
		}
		return p*price;
		 
	}
	public void setnPlaces(int nPlaces) {
		this.nPlaces = nPlaces;
	}

	public void setIrabaziak(double irabaziak) {
		this.irabaziak = irabaziak;
	}

	public double getIrabaziak() {
		return irabaziak;
	}

	/**
	 * Get the origin  of the ride
	 * 
	 * @return the origin location
	 */

	public String getFroma() {
		return froma;
	}

	public boolean isBukatuta() {
		return bukatuta;
	}

	public void setBukatuta(boolean bukatuta) {
		this.bukatuta = bukatuta;
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

	public void addBalorazioa(Balorazioa b) {
		balorazioList.add(b);
	}
	
	public void addErreklamazioa(Erreklamazioa e) {
		erreklamazioList.add(e);
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

	
	public int getnPlaces() {
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
		return rideNumber+": "+froma+" --> "+toa+" | "+date + " | " + nPlaces +" | "+ String.format("%.2f", price) +"€";  	}




	
}
