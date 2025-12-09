package eredua.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.primefaces.event.RateEvent;

import businessLogic.BLFacade;
import domain.*;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named("baler")
@RequestScoped

public class BalErBean implements Serializable{
	private Traveler traveler;
	private String departCity;
	private List<String> departCities;
	private List<String> destCities;
	private String destCity;
	private Date data;
	private BLFacade facadeBL=FacadeBean.getBusinessLogic();
	private Date bihar;
	private List<Erreserba> reservesList;
	private Erreserba auk;
    private String erTit;
    private String erDesk;
	public BalErBean() {
		traveler=LoginBean.getTt();
		reservesList= facadeBL.getAllErreserbakT(traveler,true);
		
	}
	
	public Date getData() {
		return data;
	}
	public Erreserba getAuk() {
		return auk;
	}

	public void setAuk(Erreserba auk) {
		this.auk = auk;
	}

	public String getErTit() {
		return erTit;
	}

	public void setErTit(String erTit) {
		this.erTit = erTit;
	}

	public String getErDesk() {
		return erDesk;
	}

	public void setErDesk(String erDesk) {
		this.erDesk = erDesk;
	}

	public void erreklamatu(Erreserba r) {
        this.auk = r;
        this.erTit = "";
        this.erDesk = "";
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
	public List<Erreserba> getReservesList() {
		return reservesList;
	}

	public void setReservesList(List<Erreserba> ridesList) {
		this.reservesList = ridesList;
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

	public void erBidali() {
		Erreklamazioa e=facadeBL.badagoErreklamazioa(auk);
		if(e==null) {
			 Erreklamazioa er = new Erreklamazioa(auk.getTraveler().getEmail(),erTit,erDesk,auk);
			 
			 facadeBL.createErreklamazioa(er, Integer.parseInt(auk.getRide().getRideNumber()+""));
			 traveler=facadeBL.badagoTraveler(traveler.getEmail());
			 LoginBean.setTt(traveler);
			 reservesList= facadeBL.getAllErreserbakT(traveler,true);
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "[✔] Erreklamazioa bidali da. ", null));
   
		}else if(e.isEginda() && e.isOnartua() ) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "[⚠️] Erreklamazioa arrakastarekin ebatzi da jada.", null));

		}else if(e.isEginda() && !e.isOnartua() ) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "[⚠️] Erreklamazioa arrakastarik gabe ebatzi da jada.", null));

		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "[⚠️] Erreklamazioa berrikuspen-egoeran jarraitzen du.", null));

		}
	   

	    
	}
	public void setBihar(Date bihar) {
		this.bihar = bihar;
	}
	public void baloratu(Erreserba er) {
		Balorazioa bb= facadeBL.badagoBalorazioa(er);
		if(bb!=null) {
			facadeBL.ezabatuBal(bb.getId(), er.getRide().getRideNumber());
		}
		int stars = er.getBal();
	    bb= new Balorazioa(traveler.getEmail(),er,stars,"ka","ak");
	    facadeBL.createReview(bb, Integer.parseInt(er.getRide().getRideNumber()+""));
	    
	}
	public String baler() {
		
		return "baler";
	}
	
	
	
	
}