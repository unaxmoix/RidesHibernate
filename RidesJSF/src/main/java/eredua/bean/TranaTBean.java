package eredua.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
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

@Named("tranat")
@RequestScoped

public class TranaTBean implements Serializable{
	
	
	
	private BLFacade facadeBL=FacadeBean.getBusinessLogic();


	private Traveler traveler;
	private List<Transaction> transak;
	public TranaTBean() {
		 traveler=LoginBean.getTt();
		 
		 transak=facadeBL.lortuTransakT(traveler);
		 Collections.reverse(transak);
		 System.out.println(transak);
		 
	}
	
	
	public List<Transaction> getTransak() {
		return transak;
	}

	public void setTransak(List<Transaction> transak) {
		this.transak = transak;
	}
	
	public String tranat() {
		return "tranat";
	}

	
	   
	    
	
	
	
	
	
	
	
}