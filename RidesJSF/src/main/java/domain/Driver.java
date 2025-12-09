package domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlIDREF;

@Entity
public class Driver implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id 
	private String email;
	private String password; 
    private double money;
	

	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
	private List<Transaction> transactions = new Vector<Transaction>();
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private List<Ride> rides=new Vector<Ride>();

	public Driver() {
		super();
	}

	public Driver(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public List<Ride> getRides() {
		return rides;
	}

	public void setRides(List<Ride> rides) {
		this.rides = rides;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String name) {
		this.password = name;
	}
	public List<Ride> getPastRides() {
	    Date today = new Date(); 
	    
	    return rides.stream().filter(r -> r.getDate().before(today) && !r.isBukatuta()).collect(Collectors.toList());
	}
	
	
	public String toString(){
		return email+";"+password+rides;
	}
	
	/**
	 * This method creates a bet with a question, minimum bet ammount and percentual profit
	 * 
	 * @param question to be added to the event
	 * @param betMinimum of that question
	 * @return Bet
	 */
	public Ride addRide(String from, String to, Date date, int nPlaces, float price)  {
        Ride ride=new Ride(from,to,date,nPlaces,price, this);
        rides.add(ride);
        return ride;
	}
	public void removeRide(Ride ride) {
		rides.remove(ride);
	}
	public boolean doesRideExists(String from, String to, Date date)  {	
		boolean b=false;
		for (Ride r:rides) {
			if ( (from.equals(r.getFroma())) && (to.equals(r.getToa())) && (date.equals(r.getDate())) )
			 b=true;
		}
		return b;
	}
	public Ride obtainRide(Long rnm)  {	
		Ride id= null;;
		for (Ride r:rides) {
			if (rnm == r.getRideNumber() )
			 id=r;
		}
		return id;
	}
	public Ride removeRide(String froma, String toa, Date date) {
		boolean found=false;
		int index=0;
		Ride r=null;
		while (!found && index<rides.size()) {
			r=rides.get(index);
			if ( (froma.equals(r.getFroma())) && (toa.equals(r.getToa())) && (date.equals(r.getDate())) ) {
				found=true;
			}
			index=index+1;
			
			
		}
			
		if (found) {
			rides.remove(index-1);
			return r;
		} else  return null;
	}
	/**
	 * This method checks if the ride already exists for that driver
	 * 
	 * @param from the origin location 
	 * @param to the destination location 
	 * @param date the date of the ride 
	 * @return true if the ride exists and false in other case
	 */
	
		
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Driver other = (Driver) obj;
		if (email != other.email)
			return false;
		return true;
	}

	
	public void addMoney(double mon) {
		this.money+=mon;
	}
	
	public void addTransaction(Transaction trans) {
		this.transactions.add(trans);
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
}
