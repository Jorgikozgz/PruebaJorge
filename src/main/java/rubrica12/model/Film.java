package rubrica12.model;

import java.io.Serializable;
import java.sql.Date;

public class Film implements Serializable {
	
	private int idFilm;
	private String nameFilm;
	private Date dateOfPremier;
	
	public int getIdFilm() {
		return idFilm;
	}
	public void setIdFilm(int idFilm) {
		this.idFilm = idFilm;
	}
	public String getNameFilm() {
		return nameFilm;
	}
	public void setNameFilm(String nameFilm) {
		this.nameFilm = nameFilm;
	}
	public Date getDateOfPremier() {
		return dateOfPremier;
	}
	public void setDateOfPremier(Date dateOfPremier) {
		this.dateOfPremier = dateOfPremier;
	}
	
	

}
