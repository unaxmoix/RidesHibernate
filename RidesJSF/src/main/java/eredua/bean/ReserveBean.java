package eredua.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import businessLogic.BLFacade;
import domain.*;
import domain.Ride;
import jakarta.faces.view.ViewScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named("reserve")
@ViewScoped

public class ReserveBean implements Serializable{
	private Traveler traveler;
	private String departCity;
	private List<String> departCities;
	private List<String> destCities;
	private String places;
	private double tot;
	private String posta;

	

	public String getPosta() {
		return posta;
	}

	public void setPosta(String posta) {
		this.posta = posta;
	}
	private String destCity;
	private Date data;
	private BLFacade facadeBL=FacadeBean.getBusinessLogic();
	private Date bihar;
	private List<Ride> ridesList;
	public ReserveBean() {
		traveler=LoginBean.getTt();
		Calendar c = Calendar.getInstance();
		 c.add(Calendar.DAY_OF_YEAR, 1);  
		 this.bihar= c.getTime();
		departCities=facadeBL.getDepartCities();
		destCities=facadeBL.getDestinationCities(departCity);
		tot=traveler.getMoney();
		posta=traveler.getEmail();
		
	}
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public String getPlaces() {
		return places;
	}

	public void setPlaces(String places) {
		this.places = places;
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
	public double getTot() {
		return tot;
	}

	public void setTot(double tot) {
		this.tot = tot;
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
		
		ridesList=facadeBL.getRides(departCity, destCity, data);
		
	}

	public void setBihar(Date bihar) {
		this.bihar = bihar;
	}
	public String reserve() {
		refresh();
		departCity=null;
		destCity=null;
		data=null;
		return "reserve";
	}
	public void reserveRide(Ride r) {
		if(r.getPrice()* Integer.parseInt(places) >traveler.getMoney()) {
			double falta = r.getPrice() * Integer.parseInt(places) - traveler.getMoney();
			String faltahobetua = String.format("%.2f", falta);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Ez duzu nahiko diru. Hau falta da: "+faltahobetua+"€", null));

		}else if(Integer.parseInt(places)> r.getnPlaces()){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Ez daude nahiko eserleku. Eskuragarri dauden eserleku kopurua: "+r.getnPlaces(), null));
			
		}else {
			Erreserba er=facadeBL.erreserbaSortu(r, Integer.parseInt(places), traveler);
			float d=r.getPrice()* Integer.parseInt(places);
			traveler=facadeBL.badagoTraveler(traveler.getEmail());
			tot=traveler.getMoney();
			LoginBean.setTt(traveler);
			Transaction trana= new Transaction(-d,traveler.getMoney(),"Erreserba eskaera gauzatu da.");
			facadeBL.addTransactionT(trana, traveler);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erreserba eskaera gauzatu da: "+ er.toString(), null));

		}
		
		
	}
	
	
	
	
}