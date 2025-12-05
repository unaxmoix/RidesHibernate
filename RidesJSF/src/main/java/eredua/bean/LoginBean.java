package eredua.bean;

import java.io.Serializable;

import businessLogic.BLFacade;
import domain.Driver;
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
	private BLFacade facadeBL=FacadeBean.getBusinessLogic();
	
	public LoginBean(){
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
		if (this.mota.equals("Gidaria")) {
			Driver d=facadeBL.badagoDriver(this.posta);
			if(d !=null) {
				if(d.getPassword())
			}else {
				 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "[⚠️] Posta elektronikoa ez dago erregistratuta Gidaria motarako!", null));
			}
			
		}else if (this.mota.equals("Bidaiaria")) {
			
		}
	}
	public String signIn() {
		return "loginsignIn";
	}
	
}
