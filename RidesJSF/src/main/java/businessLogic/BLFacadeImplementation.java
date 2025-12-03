package businessLogic;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;



import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Ride;
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
	
	
	public void close() {
		DataAccess dB4oManager=new DataAccess(JPAUtil.getEntityManager());

		dB4oManager.close();

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

