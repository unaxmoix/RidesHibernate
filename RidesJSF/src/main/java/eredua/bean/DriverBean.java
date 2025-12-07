package eredua.bean;

import java.io.Serializable;

import domain.Driver;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named("driver")
@RequestScoped
public class DriverBean implements Serializable{
	private  String posta;
	private  double money;
	private static Driver driver;
	public DriverBean(){
		driver=LoginBean.getDd();
		money=driver.getMoney();
		posta=driver.getEmail();
		
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public  String getPosta() {
		return posta;
	}

	public void setPosta(String posta) {
		this.posta = posta;
	}

	public Driver getDriver() {
		return driver;
	}
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	public String itzuli() {
		return "gidarimenu";
	}
	
}
