package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.persistence.*;

@Entity
public class Traveler implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id 
	private String email;
	private String password;
	private String name;
	private double money;
	


	public Traveler() {
		
	}

	public Traveler(String email, String password) {
		this.email = email;
		this.password = password;
		money = 0.0;
	}
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	



	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}
	

	//public String toString(){
		//return name+";"+email+";"+rides;
	//}
	
		
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Traveler other = (Traveler) obj;
		if (email != other.email)
			return false;
		return true;
	}
	@Override
	public int hashCode() {
	    return java.util.Objects.hash(email);
	}
	public void addMoney(double mon) {
		this.money+=mon;
	}

	
}
