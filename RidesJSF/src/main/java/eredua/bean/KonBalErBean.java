package eredua.bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import businessLogic.BLFacade;
import domain.*;
import exceptions.*;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named("konbaler")
@RequestScoped

public class KonBalErBean implements Serializable{
	
	
	
	private BLFacade facadeBL=FacadeBean.getBusinessLogic();
	private double tot;

	private Driver driver;
	private List<Transaction> transak;
	
	private boolean showBalorazioak = true;
    private boolean showErreklamazioak = true;

    private List<Balorazioa> balList;
    private List<Erreklamazioa> errList;
    
    
    public KonBalErBean() {
		 driver=LoginBean.getDd();
		 balList=facadeBL.getAllBalorazioak(driver);
		 errList=facadeBL.getAllErreklamazioak(driver);
		 tot=driver.getMoney();
		 
	}
    


    public double getTot() {
		return tot;
	}



	public void setTot(double tot) {
		this.tot = tot;
	}



	public boolean isShowErreklamazioak() { return showErreklamazioak; }
    public void setShowErreklamazioak(boolean showErreklamazioak) { this.showErreklamazioak = showErreklamazioak; }
	public boolean isShowBalorazioak() {
		return showBalorazioak;
	}
	public void setShowBalorazioak(boolean showBalorazioak) {
		this.showBalorazioak = showBalorazioak;
	}
	public List<Balorazioa> getBalList() {
		return balList;
	}
	public void setBalList(List<Balorazioa> balList) {
		this.balList = balList;
	}
	public List<Erreklamazioa> getErrList() {
		return errList;
	}
	public void setErrList(List<Erreklamazioa> errList) {
		this.errList = errList;
	}
	public List<Transaction> getTransak() {
		return transak;
	}

	public void setPrezioa(List<Transaction> transak) {
		this.transak = transak;
	}
	
	public void onartuErr(Erreklamazioa r) {
		facadeBL.onartuErreklamazioa(r);
		errList=facadeBL.getAllErreklamazioak(driver);
		
		Float money = r.getErreserba().getPlaces()*r.getErreserba().getRide().getPrice();
		facadeBL.sartuDiruaD(Double.parseDouble("-"+money), driver);
		driver =facadeBL.badagoDriver(driver.getEmail());
		Transaction trana= new Transaction(Double.parseDouble("-"+money),driver.getMoney(),"Erreklamazio bat onartu da.");
		facadeBL.addTransactionD(trana, driver);
		driver =facadeBL.badagoDriver(driver.getEmail());
		LoginBean.setDd(driver);
		tot=driver.getMoney();

		Traveler traveler=facadeBL.badagoTraveler(r.getEmail());
		facadeBL.sartuDiruaT(Double.parseDouble(money+""), traveler);
		traveler=facadeBL.badagoTraveler(traveler.getEmail());
		trana= new Transaction(Double.parseDouble(money+""),traveler.getMoney(),"Erreklamazio bat onartu da.");
		facadeBL.addTransactionT(trana, traveler);
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "[✔] Erreklamazioa onartu da! Itzulitako dirua: "+ money + "€", null));
	}

	public void baztertuErr(Erreklamazioa r) {
		facadeBL.baztertuErreklamazioa(r);
		 errList=facadeBL.getAllErreklamazioak(driver);
	}
	
	public String konbaler() {
		return "konbaler";
	}

	
	
	    
	
	
	
	
	
	
	
}