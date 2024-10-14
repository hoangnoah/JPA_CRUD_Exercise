package vn.iotstar.config;

import jakarta.persistence.*;

@PersistenceContext
public class JpaConfig {
	 public static EntityManager getEntityManager() {
	 EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-hibernate-mysql");
	 return factory.createEntityManager();
	 }
}