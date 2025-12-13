package eredua.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import businessLogic.BLFacade;
import domain.Driver;
import domain.Ride;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named("query")
@RequestScoped

public class QueryBean implements Serializable{
	private Driver driver;
	private String departCity;
	private List<String> departCities;
	private List<String> destCities;
	private String destCity;
	private Date data;
	private BLFacade facadeBL=FacadeBean.getBusinessLogic();
	private Date bihar;
	private List<Ride> ridesList;
	public QueryBean() {
		driver=LoginBean.getDd();
		ridesList= driver.getRides();
		
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
	
	
	public void removeRide(Ride r) {
		if(facadeBL.erreserbaDauka(r)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Ezin da ezabatu bidaia, erreserbak dauzka. ", null));
		}else {
			facadeBL.removeRide(r.getRideNumber()+"");
			driver=facadeBL.badagoDriver(driver.getEmail());
			LoginBean.setDd(driver);
			ridesList=driver.getRides();
		    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bidaia ezabatu da.", null));


		}
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