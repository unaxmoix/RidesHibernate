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
	
	public String konbaler() {
		return "konbaler";
	}

	
	
	    
	
	
	
	
	
	
	
}