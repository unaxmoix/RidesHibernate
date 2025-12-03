package eredua.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import businessLogic.BLFacade;
import domain.Ride;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named("query")
@SessionScoped

public class QueryBean implements Serializable{
	
	private String departCity;
	private List<String> departCities;
	private List<String> destCities;
	private String destCity;
	private Date data;
	private BLFacade facadeBL=FacadeBean.getBusinessLogic();
	private Date bihar;
	private List<Ride> ridesList;
	public QueryBean() {
		Calendar c = Calendar.getInstance();
		 c.add(Calendar.DAY_OF_YEAR, 1);  
		 this.bihar= c.getTime();
		departCities=facadeBL.getDepartCities();
		destCities=facadeBL.getDestinationCities(departCity);
	}
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	
	public List<String> getDestCities() {
		return destCities;
	}
	public void setDestCities(List<String> destCities) {
		this.destCities = destCities;
	}
	public String getDestCity() {
		return destCity;
	}
	public void setDestCity(String destCity) {
		this.destCity = destCity;
	}
	public void refresh() {
		departCities=facadeBL.getDepartCities();
		this.destCities=facadeBL.getDestinationCities(departCity);
		
	}
	public String getDepartCity() {
		return departCity;
	}
	public void setDepartCity(String departCity) {
		this.departCity = departCity;
	}
	public List<Ride> getRidesList() {
		return ridesList;
	}

	public void setRidesList(List<Ride> ridesList) {
		this.ridesList = ridesList;
	}

	public List<String> getDepartCities() {
		return departCities;
	}
	public void setDepartCities(List<String> departCities) {
		this.departCities = departCities;
	}
	
	public Date getBihar() {
		return bihar;
	}
	
	public void getRides() {
		ridesList= facadeBL.getRides(departCity, destCity, data);
	}

	public void setBihar(Date bihar) {
		this.bihar = bihar;
	}
	public String query() {
		refresh();
		departCity=null;
		destCity=null;
		data=null;
		return "query";
	}
	
	
	
	
}