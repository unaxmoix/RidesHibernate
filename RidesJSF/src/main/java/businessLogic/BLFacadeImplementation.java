package businessLogic;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;


import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Ride;
import domain.Transaction;
import domain.Traveler;
import eredua.JPAUtil;
import domain.Driver;
import exceptions.RideMustBeLaterThanTodayException;
import exceptions.RideAlreadyExistException;

/**
 * It implements the business logic as a web service.
 */


public class BLFacadeImplementation  implements BLFacade {
	DataAccess dbManager;

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		
		
		    dbManager=new DataAccess(JPAUtil.getEntityManager());
		    
		//dbManager.close();

		
	}
	
    public BLFacadeImplementation(DataAccess da)  {
		
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c=ConfigXML.getInstance();
		
		dbManager=da;		
	}
    public List<Transaction> lortuTransakT(Traveler t){
    	dbManager.open();
    	List<Transaction> list=dbManager.lortuTransakT(t);
		
		dbManager.close();
		return list;
    }
    public List<Transaction> lortuTransakD(Driver t){
    	dbManager.open();
    	List<Transaction> list=dbManager.lortuTransakD(t);
		
		dbManager.close();
		return list;
    }
    public void createDriver(Driver d) {
		dbManager.open();
		dbManager.createDriver(d);
		dbManager.close();
	}
    public void createTraveler(Traveler t) {
		dbManager.open();
		dbManager.createTraveler(t);
		dbManager.close();
	}
	
	
	public Driver badagoDriver(String d) {
		dbManager.open();
		Driver ema = dbManager.badagoDriver(d);
		dbManager.close();
		return ema;
	}
	
	
	public Traveler badagoTraveler(String t) {
		dbManager.open();
		Traveler ema = dbManager.badagoTraveler(t);
		dbManager.close();
		return ema;
	}
	
    
    /**
     * {@inheritDoc}
     */
    public List<String> getDepartCities(){
    	dbManager.open();	
		
		 List<String> departLocations=dbManager.getDepartCities();		

		dbManager.close();
		
		return departLocations;
    	
    }
    /**
     * {@inheritDoc}
     */
	public List<String> getDestinationCities(String froma){
		dbManager.open();	
		
		 List<String> targetCities=dbManager.getArrivalCities(froma);		

		dbManager.close();
		
		return targetCities;
	}

	/**
	 * {@inheritDoc}
	 */
  
   public Ride createRide( String froma, String toa, Date date, int nPlaces, float price, String driverEmail ) throws RideMustBeLaterThanTodayException, RideAlreadyExistException{
	   
		dbManager.open();
		Ride ride=dbManager.createRide(froma, toa, date, nPlaces, price, driverEmail);		
		dbManager.close();
		return ride;
   };
	
   /**
    * {@inheritDoc}
    */
	 
	public List<Ride> getRides(String froma, String toa, Date date){
		dbManager.open();
		List<Ride>  rides=dbManager.getRides(froma, toa, date);
		dbManager.close();
		return rides;
	}

    
	/**
	 * {@inheritDoc}
	 */
	 
	public List<Date> getThisMonthDatesWithRides(String froma, String toa, Date date){
		dbManager.open();
		List<Date>  dates=dbManager.getThisMonthDatesWithRides(froma, toa, date);
		dbManager.close();
		return dates;
	}

	public void sartuDiruaD(double money, Driver d) {
		dbManager.open();
		dbManager.sartuDirua(money, d);
		dbManager.close();
	}
	

	public void sartuDiruaT(double money, Traveler t) {
		dbManager.open();
		dbManager.sartuDirua(money, t);
		dbManager.close();
	}
	
	public void close() {
		DataAccess dB4oManager=new DataAccess(JPAUtil.getEntityManager());

		dB4oManager.close();

	}
	public void addTransactionT(Transaction trans, Traveler t) {
		dbManager.open();
		dbManager.addTransaction(trans, t);
		dbManager.close();
	}
	

	public void addTransactionD(Transaction trans, Driver d) {
		dbManager.open();
		dbManager.addTransaction(trans, d);
		dbManager.close();
	}

	/**
	 * {@inheritDoc}
	 */
  	
	 public void initializeBD(){
    	dbManager.open();
		dbManager.initializeDB();
		dbManager.close();
	}

}

