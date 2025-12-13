package eredua.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import businessLogic.BLFacade;
import domain.*;
import exceptions.*;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named("diruat")
@RequestScoped

public class DiruaTBean implements Serializable{
	
	private BLFacade facadeBL=FacadeBean.getBusinessLogic();
	private String money;
	private double tot;
	private Traveler tt;
	public DiruaTBean() {
		 tt=LoginBean.getTt();
		 tot=tt.getMoney();
		 
	}
	public double getTot() {
		return tot;
	}
	public void setTot(double tot) {
		this.tot = tot;
	}
	public String getMoney() {
		return money;
	}


	public void setMoney(String money) {
		this.money = money;
	}
	
	
	
	
	public String itzuli() {
		return "itzuli";
	}
	public void sartuDirua() {
		facadeBL.sartuDiruaT(Double.parseDouble(money), tt);
		Traveler teta=facadeBL.badagoTraveler(tt.getEmail());
		Transaction trana= new Transaction(Double.parseDouble(money),teta.getMoney(),"Dirua sartu da.");
		facadeBL.addTransactionT(trana, tt);
		LoginBean.setTt(teta);
		tt=teta;
		tot=teta.getMoney();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sartutako dirua: "+ money + "€", null));
	}
	public String diruat() {
		System.out.println("e");		
		return "diruat";
	}
	
	
	
}