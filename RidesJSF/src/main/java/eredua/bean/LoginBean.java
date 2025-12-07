package eredua.bean;

import java.io.Serializable;

import businessLogic.BLFacade;
import domain.*;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named("login")
@SessionScoped
public class LoginBean implements Serializable{
	private String mota;
	private String pasahitza;
	private String posta;
	private static Driver dd;
	private static Traveler tt;
	private BLFacade facadeBL=FacadeBean.getBusinessLogic();
	
	public LoginBean(){
	}

	public  String  getMota() {
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
		if (this.mota.equals("Gidaria")) {
			Driver d=facadeBL.badagoDriver(this.posta);
			if(d !=null) {
				
				if(d.getPassword().equals(pasahitza)) {
					dd=d;
					return "gidarimenu";
				}else {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "[⚠️] Pasahitz okerra!", null));
					
				}
			}else {
				 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "[⚠️] Posta elektronikoa ez dago erregistratuta Gidaria motarako!", null));
			}
			
		}else if (this.mota.equals("Bidaiaria")) {
			Traveler t= facadeBL.badagoTraveler(this.posta);
			if(t !=null) {
				if(t.getPassword().equals(pasahitza)) {
					tt=t;
					return "bidaiarimenu";
				}else {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "[⚠️] Pasahitz okerra!", null));
					
				}
			}else {
				 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "[⚠️] Posta elektronikoa ez dago erregistratuta Bidaiaria motarako!", null));
			}
		}
		return null;
	}
	
	public static Driver getDd() {
		return dd;
	}

	public static void setDd(Driver d) {
		dd = d;
	}

	public static Traveler getTt() {
		return tt;
	}

	public static void setTt(Traveler t) {
		tt = t;
	}
	public String logout() {
		return "logout";
	}
	public String signIn() {
		return "loginsignIn";
	}
	
}
