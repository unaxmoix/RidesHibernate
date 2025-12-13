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

@Named("diruaD")
@RequestScoped

public class DiruaDBean implements Serializable{
	
	
	private BLFacade facadeBL=FacadeBean.getBusinessLogic();

	private DriverBean menP=new DriverBean();
	private Driver dd;
	private String money;
	private double tot;
	public DiruaDBean() {
		 dd=LoginBean.getDd();	
		 
		 tot=dd.getMoney();
		
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
	public String diruaD() {
		return "diruaD";
	}
	
	public void ateraDirua() {
		if(dd.getMoney()<Double.parseDouble(money)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Ez duzu diru nahikorik", null));
		}else {
			facadeBL.sartuDiruaD(Double.parseDouble("-"+money), dd);
			Driver teta=facadeBL.badagoDriver(dd.getEmail());
			Transaction trana= new Transaction(Double.parseDouble("-"+money),teta.getMoney(),"Dirua atera da.");
			facadeBL.addTransactionD(trana, dd);
			LoginBean.setDd(teta);
			dd=teta;
			tot=teta.getMoney();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ateratako dirua: "+ money + "€", null));
		}
		
	}

	
	
	
	
	
	
	
	
}