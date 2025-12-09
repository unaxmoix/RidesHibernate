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

@Named("finish")
@RequestScoped

public class FinishBean implements Serializable{
	private Driver driver;
	private String departCity;
	private List<String> departCities;
	private List<String> destCities;
	private String destCity;
	private String posta;
	private double tot;
	private Date data;
	private BLFacade facadeBL=FacadeBean.getBusinessLogic();
	private Date bihar;
	private List<Ride> ridesList;
	public FinishBean() {
		driver=LoginBean.getDd();
		ridesList= driver.getPastRides();
		posta=driver.getEmail();
		tot=driver.getMoney();
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
	public void finishRide(Ride r) {
		if(!facadeBL.erreserbaEzOnartDauka(r)) {
			facadeBL.bukatuRide(r.getRideNumber()+"");
			driver= facadeBL.badagoDriver(driver.getEmail());
			tot=driver.getMoney();
			ridesList=driver.getPastRides();
			LoginBean.setDd(driver);
		    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "[✔] Bidaia bukatu da: "+ r.toString()+" | Irabaziak: "+r.getIrabaziak()+"€", null));
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "[⚠️] Kudeatu gabeko erreserbak dituzu! ", null));

		}
		

	}
	
	
	
	public String getPosta() {
		return posta;
	}

	public void setPosta(String posta) {
		this.posta = posta;
	}

	public double getTot() {
		return tot;
	}

	public void setTot(double tot) {
		this.tot = tot;
	}

	public String finish() {
		return "finish";
	}
	
	
	
	
}