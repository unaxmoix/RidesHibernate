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

@Named("create")
@RequestScoped

public class CreateBean implements Serializable{
	private String nora= null;
	private String nondik= null;
	private String eserKop;
	private String prezioa;
	private Date data;
	private Date bihar;
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	private BLFacade facadeBL=FacadeBean.getBusinessLogic();

	private DriverBean menP=new DriverBean();
	private Driver driver;
	public CreateBean() {
		 driver=LoginBean.getDd();
		 Calendar c = Calendar.getInstance();
		 c.add(Calendar.DAY_OF_YEAR, 1);  
		 this.bihar= c.getTime();
	}
	
	public String getNora() {
		return nora;
	}
	public void setNora(String nora) {
		this.nora = nora;
	}
	public String getNondik() {
		return nondik;
	}
	public void setNondik(String nondik) {
		this.nondik = nondik;
	}
	
	public String getEserKop() {
		return eserKop;
	}

	public void setEserKop(String eserKop) {
		this.eserKop = eserKop;
	}

	public String getPrezioa() {
		return prezioa;
	}

	public void setPrezioa(String prezioa) {
		this.prezioa = prezioa;
	}
	
	public String create() {
		return "create";
	}

	public void createRide() throws RideMustBeLaterThanTodayException{
		
		try {
		Ride r=facadeBL.createRide(nondik, nora, data, Integer.parseInt(eserKop), Float.parseFloat(prezioa),driver.getEmail());
		LoginBean.setDd(facadeBL.badagoDriver(driver.getEmail()));
		nondik = null;
	    nora = null;
	    eserKop = null;
	    prezioa = null;
	    data = null;
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "[✔] Bidaia sortu da: "+ r.toString(), null));
		}catch(Exception e) {
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "[⚠️] Ez da sortu bidaia ", null));
		}
	   
	    
	}

	public Date getBihar() {
		return bihar;
	}

	public void setBihar(Date bihar) {
		this.bihar = bihar;
	}
	
	
	
	
	
	
	
	
}