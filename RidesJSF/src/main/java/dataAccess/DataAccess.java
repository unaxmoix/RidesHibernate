package dataAccess;

import java.io.File;
import java.net.NoRouteToHostException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Driver;
import domain.Ride;
import domain.Transaction;
import domain.Traveler;
import eredua.JPAUtil;
import exceptions.RideAlreadyExistException;
import exceptions.RideMustBeLaterThanTodayException;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess  {
	private  EntityManager  db;
	private  EntityManagerFactory emf;


	

	/*
	 * public DataAccess() { if (c.isDatabaseInitialized()) { String
	 * fileName=c.getDbFilename();
	 * 
	 * File fileToDelete= new File(fileName); if(fileToDelete.delete()){ File
	 * fileToDeleteTemp= new File(fileName+"$"); fileToDeleteTemp.delete();
	 * 
	 * System.out.println("File deleted"); } else {
	 * System.out.println("Operation failed"); } }
	 * 
	 * if (c.isDatabaseInitialized())initializeDB();
	 * 
	 * System.out.println("DataAccess created => isDatabaseLocal: "+c.
	 * isDatabaseLocal()+" isDatabaseInitialized: "+c.isDatabaseInitialized());
	 * 
	 * close();
	 * 
	 * }
	 */
     
    public DataAccess(EntityManager db) {
    	this.db=db;
    }

	
	
	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){
		
		db.getTransaction().begin();

		try {

		   Calendar today = Calendar.getInstance();
		   
		   int month=today.get(Calendar.MONTH);
		   int year=today.get(Calendar.YEAR);
		   if (month==12) { month=1; year+=1;}  
	    
		   
		    //Create drivers 
			Driver driver1=new Driver("driver1@gmail.com","Aitor Fernandez");
			Driver driver2=new Driver("driver2@gmail.com","Ane Gaztañaga");
			Driver driver3=new Driver("driver3@gmail.com","Test driver");

			
			//Create rides
			driver1.addRide("Donostia", "Bilbo", UtilDate.newDate(year,month,15), 4, 7);
			driver1.addRide("Donostia", "Gazteiz", UtilDate.newDate(year,month,6), 4, 8);
			driver1.addRide("Bilbo", "Donostia", UtilDate.newDate(year,month,25), 4, 4);

			driver1.addRide("Donostia", "Iruña", UtilDate.newDate(year,month,7), 4, 8);
			
			driver2.addRide("Donostia", "Bilbo", UtilDate.newDate(year,month,15), 3, 3);
			driver2.addRide("Bilbo", "Donostia", UtilDate.newDate(year,month,25), 2, 5);
			driver2.addRide("Eibar", "Gasteiz", UtilDate.newDate(year,month,6), 2, 5);

			driver3.addRide("Bilbo", "Donostia", UtilDate.newDate(year,month,14), 1, 3);

			
						
			db.persist(driver1);
			db.persist(driver2);
			db.persist(driver3);

	
			db.getTransaction().commit();
			System.out.println("Db initialized");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * This method returns all the cities where rides depart 
	 * @return collection of cities
	 */
	public List<String> getDepartCities(){
		try {
			TypedQuery<String> query = db.createQuery("SELECT DISTINCT r.froma FROM Ride r ORDER BY r.froma", String.class);
			List<String> cities = query.getResultList();
			return cities;
		}catch (Exception e){
			return null;
		}
			
		
	}
	/**
	 * This method returns all the arrival destinations, from all rides that depart from a given city  
	 * 
	 * @param froma the depart location of a ride
	 * @return all the arrival destinations
	 */
	public List<String> getArrivalCities(String froma){
		try {
			TypedQuery<String> query = db.createQuery("SELECT DISTINCT r.toa FROM Ride r WHERE r.froma=?1 ORDER BY r.toa",String.class);
			query.setParameter(1, froma);
			List<String> arrivingCities = query.getResultList(); 
			return arrivingCities;
		}catch (Exception e){
			return null;
		}
		
		
	}
	/**
	 * This method creates a ride for a driver
	 * 
	 * @param from the origin location of a ride
	 * @param to the destination location of a ride
	 * @param date the date of the ride 
	 * @param nPlaces available seats
	 * @param driverEmail to which ride is added
	 * 
	 * @return the created ride, or null, or an exception
	 * @throws RideMustBeLaterThanTodayException if the ride date is before today 
 	 * @throws RideAlreadyExistException if the same ride already exists for the driver
	 */
	public Ride createRide(String froma, String toa, Date date, int nPlaces, float price, String driverEmail) throws  RideAlreadyExistException, RideMustBeLaterThanTodayException {
		System.out.println(">> DataAccess: createRide=> froma= "+froma+" to= "+toa+" driver="+driverEmail+" date "+date);
		try {
			if(new Date().compareTo(date)>0) {
				throw new RideMustBeLaterThanTodayException(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.ErrorRideMustBeLaterThanToday"));
			}
			db.getTransaction().begin();
			
			Driver driver = db.find(Driver.class, driverEmail);
			if (driver.doesRideExists(froma, toa, date)) {
				db.getTransaction().commit();
				throw new RideAlreadyExistException(ResourceBundle.getBundle("Etiquetas").getString("DataAccess.RideAlreadyExist"));
			}
			System.out.println("crea addRide");
			
			Ride ride = driver.addRide(froma, toa, date, nPlaces, price);
			//next instruction can be obviated
			db.persist(ride);
			db.persist(driver); 
			db.getTransaction().commit();

			return ride;
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			db.getTransaction().commit();
			return null;
		}
		
		
	}
	
	/**
	 * This method retrieves the rides froma two locations on a given date 
	 * 
	 * @param froma the origin location of a ride
	 * @param toa the destination location of a ride
	 * @param date the date of the ride 
	 * @return collection of rides
	 */
	public List<Ride> getRides(String froma, String toa, Date date) {
		System.out.println(">> DataAccess: getRides=> froma= "+froma+" toa= "+toa+" date "+date);

		List<Ride> res = new ArrayList<Ride>();	
		TypedQuery<Ride> query = db.createQuery("SELECT r FROM Ride r WHERE r.froma=?1 AND r.toa=?2 AND r.date=?3",Ride.class);   
		query.setParameter(1, froma);
		query.setParameter(2, toa);
		query.setParameter(3, date);
		List<Ride> rides = query.getResultList();
	 	 for (Ride ride:rides){
		   res.add(ride);
		  }
	 	return res;
	}
	
	/**
	 * This method retrieves froma the database the dates a month for which there are events
	 * @param froma the origin location of a ride
	 * @param toa the destination location of a ride 
	 * @param date of the month for which days with rides want toa be retrieved 
	 * @return collection of rides
	 */
	public List<Date> getThisMonthDatesWithRides(String froma, String toa, Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		List<Date> res = new ArrayList<Date>();	
		
		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);
				
		
		TypedQuery<Date> query = db.createQuery("SELECT DISTINCT r.date FROM Ride r WHERE r.froma=?1 AND r.toa=?2 AND r.date BETWEEN ?3 and ?4",Date.class);   
		
		query.setParameter(1, froma);
		query.setParameter(2, toa);
		query.setParameter(3, firstDayMonthDate);
		query.setParameter(4, lastDayMonthDate);
		List<Date> dates = query.getResultList();
	 	 for (Date d:dates){
		   res.add(d);
		  }
	 	return res;
	}
	public List<Transaction> lortuTransakT(Traveler t){
		db.getTransaction().begin();
		
		Traveler traveler = db.find(Traveler.class, t.getEmail());
		List<Transaction> listTansak=traveler.getTransactions();
		 
		db.getTransaction().commit();
		
		return listTansak;
		}
	public List<Transaction> lortuTransakD(Driver t){
		db.getTransaction().begin();
		Driver driver = db.find(Driver.class, t.getEmail());
		List<Transaction> listTansak=driver.getTransactions();
		db.getTransaction().commit();
		return listTansak;
		}
	public void addTransaction(Transaction trans, Traveler t) {
		Traveler traveler = badagoTraveler(t.getEmail());
		db.getTransaction().begin();
		traveler.addTransaction(trans);
		db.getTransaction().commit();
	}

	public void addTransaction(Transaction trans, Driver d) {
		Driver driver = badagoDriver(d.getEmail());
		db.getTransaction().begin();
		driver.addTransaction(trans);
		db.getTransaction().commit();
	}
	public void sartuDirua(double money, Driver d) {
		Driver driver = badagoDriver(d.getEmail());
		db.getTransaction().begin();
		driver.addMoney(money);
		db.getTransaction().commit();
	}

	public void sartuDirua(double money, Traveler t) {
		Traveler traveler = badagoTraveler(t.getEmail());
		db.getTransaction().begin();
		traveler.addMoney(money);
		db.getTransaction().commit();
	}
	
	public Driver badagoDriver(String d) {
		
		Driver ema;
		db.getTransaction().begin();
		ema = db.find(Driver.class, d);
		db.getTransaction().commit();
		
		return ema;
	}

	public Traveler badagoTraveler(String t) {
		Traveler ema;
		db.getTransaction().begin();
		ema = db.find(Traveler.class, t);
		db.getTransaction().commit();
		return ema;
	}
	public void createDriver(Driver d) {
		// try{
		db.getTransaction().begin();
		db.persist(d);
		db.getTransaction().commit();
		// } catch (Exception e) {
		// throw new EmailAlreadyExistsException("Email already in use");
		// }
	}

	public void createTraveler(Traveler t) {
		// try{
		db.getTransaction().begin();
		db.persist(t);
		db.getTransaction().commit();
		// } catch (Exception e) {
		// throw new EmailAlreadyExistsException("Email already in use");
		// }
	}

public void open(){
		

			db = JPAUtil.getEntityManager();
		
	

		
	}

	public void close(){
		db.close();
		System.out.println("DataAcess closed");
	}
	
}
