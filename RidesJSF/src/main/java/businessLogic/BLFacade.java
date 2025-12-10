package businessLogic;

import java.util.Date;
import java.util.List;


//import domain.Booking;
import domain.Ride;
import domain.Transaction;
import domain.Traveler;
import domain.Balorazioa;
import domain.Driver;
import domain.Erreklamazioa;
import domain.Erreserba;
import exceptions.RideMustBeLaterThanTodayException;
import exceptions.RideAlreadyExistException;


 
/**
 * Interface that specifies the business logic.
 */

public interface BLFacade  {
	  
	/**
	 * This method returns all the cities where rides depart 
	 * @return collection of cities
	 */
	 public List<String> getDepartCities();
	
	/**
	 * This method returns all the arrival destinations, from all rides that depart from a given city  
	 * 
	 * @param from the depart location of a ride
	 * @return all the arrival destinations
	 */
	 public List<String> getDestinationCities(String from);

	 public List<Transaction> lortuTransakT(Traveler t);
	 public List<Transaction> lortuTransakD(Driver t);
	 public Erreserba erreserbaSortu(Ride r, int place, Traveler t);
	 public List<domain.Erreserba> getAllErreserbakT(Traveler traveler,boolean b);
	 public List<domain.Erreserba> getAllErreserbakD(Driver d);
	public void erreserbaEzabatu(Erreserba er, Traveler t);
	public void removeRide(String s);
	public void erreserbaOnartu(Erreserba er);
	public boolean erreserbaDauka(Ride r);
	public void bukatuRide(String s);
	public Erreklamazioa badagoErreklamazioa(Erreserba err);
public void createReview(Balorazioa b, int r);
	
public void createErreklamazioa(Erreklamazioa e, int r);
	/**
	 * This method creates a ride for a driver
	 * 
	 * @param from the origin location of a ride
	 * @param to the destination location of a ride
	 * @param date the date of the ride 
	 * @param nPlaces available seats
	 * @param driver to which ride is added
	 * 
	 * @return the created ride, or null, or an exception
	 * @throws RideMustBeLaterThanTodayException if the ride date is before today 
 	 * @throws RideAlreadyExistException if the same ride already exists for the driver
	 */
    public Ride createRide( String from, String to, Date date, int nPlaces, float price, String driverEmail) throws RideMustBeLaterThanTodayException, RideAlreadyExistException;
    public Balorazioa badagoBalorazioa(Erreserba err);
    public void ezabatuBal(int b,Long id);
     public void createDriver(Driver d);
     public List<Balorazioa> motz(Ride r);
     public boolean erreserbaEzOnartDauka(Ride r);
     public List<Balorazioa> getAllBalorazioak(Driver d);
     public List<Erreklamazioa> getAllErreklamazioak(Driver d);
	 public void createTraveler(Traveler t);
	
	 public Driver badagoDriver(String d);
	
	 public Traveler badagoTraveler(String t);
	 
	 public void sartuDiruaD(double money, Driver d);
		
     public void sartuDiruaT(double money, Traveler t);

 	public void addTransactionT(Transaction trans, Traveler t);
 	
 	public void addTransactionD(Transaction trans, Driver d);
 	
	public void onartuErreklamazioa(Erreklamazioa e);
	
	public void baztertuErreklamazioa(Erreklamazioa e);

	
	/**
	 * This method retrieves the rides from two locations on a given date 
	 * 
	 * @param from the origin location of a ride
	 * @param to the destination location of a ride
	 * @param date the date of the ride 
	 * @return collection of rides
	 */
 public List<Ride> getRides(String from, String to, Date date);
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * @param from the origin location of a ride
	 * @param to the destination location of a ride 
	 * @param date of the month for which days with rides want to be retrieved 
	 * @return collection of rides
	 */
 public List<Date> getThisMonthDatesWithRides(String from, String to, Date date);
	
	/**
	 * This method calls the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	 public void initializeBD();

	
}
