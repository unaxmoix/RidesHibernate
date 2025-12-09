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

@Named("manage")
@RequestScoped

public class ManageReservesBean implements Serializable{
	private Driver driver;
	private BLFacade facadeBL=FacadeBean.getBusinessLogic();

	private String posta;
	private List<Erreserba> reservesList;
	public ManageReservesBean() {
		driver=LoginBean.getDd();
		reservesList= facadeBL.getAllErreserbakD(driver);
		posta = driver.getEmail();
		
		
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
	public void accept(Erreserba er) {
		if(!er.isOnartua()) {
			if(er.getRide().getnPlaces()<er.getPlaces()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "[⚠️] Ez daude eserleku nahikorik. Baztertu erreserba. ", null));

			}else {
				facadeBL.erreserbaOnartu(er);
				reservesList= facadeBL.getAllErreserbakD(driver);
				driver=facadeBL.badagoDriver(driver.getEmail());
				Ride r = driver.obtainRide(er.getRide().getRideNumber());
				LoginBean.setDd(driver);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "[✔] Erreserba onartu da.", null));
			}
			

		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "[⚠️] Erreserba dagoeneko onartuta dago. ", null));

		}
	}
	public void cancel(Erreserba er) {
		if(!er.isOnartua()) {
			facadeBL.erreserbaEzabatu(er, er.getTraveler());
			reservesList= facadeBL.getAllErreserbakD(driver);
			Transaction t= new Transaction(er.getPlaces()*er.getRide().getPrice(),facadeBL.badagoTraveler(er.getTraveler().getEmail()).getMoney(),"Erreserba baztertu eta dirua itzuli da.");
			facadeBL.addTransactionT(t, er.getTraveler());
		    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "[✔] Erreserba baztertu da.", null));

		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "[⚠️] Erreserba dagoeneko onartuta dago. ", null));

		}
	}
	
	
	
	
	

	
	public String manage() {
		
		return "manage";
	}
	
	
	
	
}