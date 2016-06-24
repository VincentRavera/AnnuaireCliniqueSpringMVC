package fr.treeptik.annuairecliniquespringmvc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Vaccin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private Date date;
	
	private Date dateRappel;
	
	@Column(nullable = false)
	private String type;
	
	private Boolean obligatoire;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Animal animal;

	
	public Vaccin() {
		// constructeur vide
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Date getDateRappel() {
		return dateRappel;
	}


	public void setDateRappel(Date dateRappel) {
		this.dateRappel = dateRappel;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Boolean getObligatoire() {
		return obligatoire;
	}


	public void setObligatoire(Boolean obligatoire) {
		this.obligatoire = obligatoire;
	}


	public Animal getAnimal() {
		return animal;
	}


	public void setAnimal(Animal animal) {
		this.animal = animal;
	}


	@Override
	public String toString() {
		return "Vaccin [id=" + id + ", date=" + date + ", dateRappel=" + dateRappel + ", type=" + type
				+ ", obligatoire=" + obligatoire + "]";
	}
	
}
