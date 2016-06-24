package fr.treeptik.annuairecliniquespringmvc.model;

import java.io.Serializable;

public class Stat implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long nbClient;
	
	private Long nbAnimal;
	
	private static Stat stat;

	
	private Stat() {
		//constructeur vide
	}
	
	public static Stat getInstance() {
		if (stat == null) {
			stat = new Stat();
		}
		return stat;
	}

	public Long getNbClient() {
		return nbClient;
	}

	public void setNbClient(Long nbClient) {
		this.nbClient = nbClient;
	}

	public Long getNbAnimal() {
		return nbAnimal;
	}

	public void setNbAnimal(Long nbAnimal) {
		this.nbAnimal = nbAnimal;
	}

}
