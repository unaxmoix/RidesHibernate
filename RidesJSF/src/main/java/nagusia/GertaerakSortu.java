package nagusia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import eredua.JPAUtil;
import domain.*;
import configuration.*;

import java.util.*;

public class GertaerakSortu {
	public GertaerakSortu() {
	}

	private void createAndStoreDriver(String email, String pass) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			Driver d = new Driver(email, pass);
			em.persist(d);
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw e;
		} finally {
			em.close();
		}
	}
	private void createAndStoreRide(String email, String pass) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			Driver d = new Driver(email, pass);
			em.persist(d);
			Ride r = new Ride("a", "b", UtilDate.newDate(2025,12,7),3,3,d);
			em.persist(r);
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw e;
		} finally {
			em.close();
		}
	}

	
	private void createAndStoreTraveler(String email, String pass) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			Traveler d = new Traveler(email, pass);
			em.persist(d);
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw e;
		} finally {
			em.close();
		}
	}

	private Driver loginDriver(String email, String pass) {
		
		EntityManager em = JPAUtil.getEntityManager();
		Driver d = null;
		try {
			em.getTransaction().begin();
			d = em.find(Driver.class, email);
			if(!d.getPassword().equals(pass)) {
				d=null;
			}
			em.getTransaction().commit();
			return d;
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();}
			return null;
		} finally {em.close();
		} }
	
	private Traveler loginTraveler(String email, String pass) {
		EntityManager em = JPAUtil.getEntityManager();
		Traveler d = null;
		try {
			em.getTransaction().begin();
			d = em.find(Traveler.class, email);
			if(!d.getPassword().equals(pass)) {
				d=null;
			}
			em.getTransaction().commit();
			return d;
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();}
			return null;
		} finally {em.close();
		} }

	public static void main(String[] args) {
		GertaerakSortu e = new GertaerakSortu();
		System.out.println("Gertaeren sorkuntza:");
		e.createAndStoreRide("bbb","a");
		//e.createAndStoreDriver("a","a");
		//e.createAndStoreTraveler("b","a");
		//e.createAndStoreDriver("a1","a");
		
		
	}
}