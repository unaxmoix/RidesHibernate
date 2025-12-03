package eredua.bean;

import java.io.Serializable;

import domain.Driver;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named("choice")
@SessionScoped
public class ChoiceBean implements Serializable{
	private static Driver driver=new Driver("driver3@gmail.com","Test Driver");
	public ChoiceBean(){
	}

	public Driver getDriver() {
		return driver;
	}
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	public String itzuli() {
		return "menu";
	}
	
}
