package eredua.bean;

import java.io.Serializable;
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

@Named("tranad")
@RequestScoped

public class TranaDBean implements Serializable{
	
	
	
	private BLFacade facadeBL=FacadeBean.getBusinessLogic();


	private Driver driver;
	private List<Transaction> transak;
	public TranaDBean() {
		 driver=LoginBean.getDd();
		 transak=facadeBL.lortuTransakD(driver);
		 Collections.reverse(transak);
	
		 
	}
	
	
	public List<Transaction> getTransak() {
		return transak;
	}

	public void setPrezioa(List<Transaction> transak) {
		this.transak = transak;
	}
	
	public String tranad() {
		return "tranad";
	}

	
	   
	    
	
	
	
	
	
	
	
}