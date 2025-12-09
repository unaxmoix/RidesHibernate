package domain;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;



@Entity
public class Balorazioa implements Serializable{
	@ManyToOne
	private Erreserba erreserba;
	private String email; 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	private int balorazioa;
	private String title;
	private String description;
	public Balorazioa() {}
	public Balorazioa(String email, Erreserba e,int balorazioa, String title, String description) {
		
		this.email = email;
		this.balorazioa = balorazioa;
		this.title = title;
		this.description = description;
		this.erreserba=e;
	}
	
	public Erreserba getErreserba() {
		return erreserba;
	}

	public void setErreserba(Erreserba erreserba) {
		this.erreserba = erreserba;
	}

	public int getBalorazioa() {
		return balorazioa;
	}

	public void setBalorazioa(int balorazioa) {
		this.balorazioa = balorazioa;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	private static String etik = "Etiquetas";
	@Override
	public String toString() {
		String bal;
		String t;
		String d;
		if(balorazioa==1) bal = "★☆☆☆☆";
		else if(balorazioa==2) bal = "★★☆☆☆";
		else if(balorazioa==3) bal = "★★★☆☆";
		else if(balorazioa==4) bal = "★★★★☆";
		else bal = "★★★★★";
		if (title.equals("")) t=ResourceBundle.getBundle(etik).getString("BaloratuGUI.DefaultTitle");
		else t=title;
		if (description.equals("")) d=ResourceBundle.getBundle(etik).getString("BaloratuGUI.DefaultDescription");
		else d=description;
		return ResourceBundle.getBundle(etik).getString("Bal") + " | " + email + ": " + bal + " " + t + ": " + d;
	}
	
	
	
}
