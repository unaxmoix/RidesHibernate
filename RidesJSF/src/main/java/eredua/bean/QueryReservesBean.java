package eredua.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import businessLogic.BLFacade;
import domain.*;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named("queryreserve")
@RequestScoped

public class QueryReservesBean implements Serializable{
	private Traveler traveler;
	private BLFacade facadeBL=FacadeBean.getBusinessLogic();
	private double tot;
	private String posta;
	private List<Erreserba> reservesList;
	public QueryReservesBean() {
		traveler=LoginBean.getTt();
		reservesList= facadeBL.getAllErreserbakT(traveler,false);
		posta = traveler.getEmail();
		tot=traveler.getMoney();
		
	}
	public double getTot() {
		return tot;
	}
	public void setTot(double tot) {
		this.tot = tot;
	}
	public String getPosta() {
		return posta;
	}
	public void setPosta(String posta) {
		this.posta = posta;
	}
	public List<Erreserba> getReservesList() {
		return reservesList;
	}
	public void setReservesList(List<Erreserba> reservesList) {
		this.reservesList = reservesList;
	}
	public void removeReserve(Erreserba er) {
		if(!er.isOnartua()) {
			facadeBL.erreserbaEzabatu(er, traveler);
			traveler=facadeBL.badagoTraveler(traveler.getEmail());
			LoginBean.setTt(traveler);
			reservesList= facadeBL.getAllErreserbakT(traveler,false);
			tot=traveler.getMoney();
			Transaction t= new Transaction(er.getPlaces()*er.getRide().getPrice(),traveler.getMoney(),"Erreserbaren dirua itzuli da.");
			facadeBL.addTransactionT(t, traveler);
		    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erreserba bertan behera utzi da.", null));

		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreserba dagoeneko onartuta dago. ", null));

		}
	}
	
	
	
	
	

	
	public String queryreserve() {
		
		return "queryreserve";
	}
	
	
	
	
}