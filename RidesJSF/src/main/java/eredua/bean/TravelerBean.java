package eredua.bean;

import java.io.Serializable;

import domain.*;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named("traveler")
@RequestScoped
public class TravelerBean implements Serializable{
	private static Traveler traveler;
	private String posta;
	private double money;
	public TravelerBean(){
		traveler=LoginBean.getTt();
		posta= traveler.getEmail();
		money=traveler.getMoney();
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public Traveler getTraveler() {
		return traveler;
	}
	
	public String getPosta() {
		return posta;
	}

	public void setPosta(String posta) {
		this.posta = posta;
	}

	public static void setTraveler(Traveler traveler) {
		
		TravelerBean.traveler = traveler;
	}

	public String itzuli() {
		return "bidaiarimenu";
	}
	
}
