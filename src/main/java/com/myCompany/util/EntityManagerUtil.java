package com.myCompany.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {

	EntityManager entityManager;
	
	public EntityManagerUtil(String persistentUnitName) {
		super();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistentUnitName);
  		entityManager = emf.createEntityManager();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
}
