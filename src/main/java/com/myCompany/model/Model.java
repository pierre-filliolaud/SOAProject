package com.myCompany.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Model {
	
	int id;
	int i;
	
	@Id
	@javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	
	
	

}
