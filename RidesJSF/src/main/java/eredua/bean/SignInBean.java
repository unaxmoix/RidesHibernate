package eredua.bean;

import java.io.Serializable;

import businessLogic.BLFacade;
import domain.*;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named("signIn")
@SessionScoped
public class SignInBean implements Serializable{
	private BLFacade facadeBL=FacadeBean.getBusinessLogic();
	private String mota;
	private String pasahitza;
	private String posta;
	
	public SignInBean(){
	}
	
	public String getMota() {
		return mota;
	}
	public void setMota(String mota) {
		this.mota = mota;
	}
	public String getPasahitza() {
		return pasahitza;
	}
	public void setPasahitza(String pasahitza) {
		this.pasahitza = pasahitza;
	}
	public String getPosta() {
		return posta;
	}
	public void setPosta(String posta) {
		this.posta = posta;
	}
	
	public String login() {
		return "signInlogin";
	}
	public void signIn() {
	
		if(mota.equals("Gidaria")) {
			if (facadeBL.badagoDriver(this.posta)==null) {
				Driver d = new Driver(this.posta, this.pasahitza);
				facadeBL.createDriver(d);
			    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "[✔] Gidari kontua sortu da: "+ posta, null));
			}else {
				 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "[⚠️] Posta elektronikoa jada existitzen da Gidaria motarako!", null));
			}
		}else if(mota.equals("Bidaiaria")) {
			if (facadeBL.badagoTraveler(this.posta)==null) {
				Traveler t = new Traveler(this.posta, this.pasahitza);
				facadeBL.createTraveler(t);
			    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "[✔] Bidaiari kontua sortu da: "+ posta, null));
			}else {
				 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "[⚠️] Posta elektronikoa jada existitzen da Bidaiaria motarako!", null));
			}
		}
	}
	
}
