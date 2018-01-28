package edu.hibernate.data;

import edu.hibernate.data.entities.Bank;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class AppJPAConfig {
    public static void main(String[] args) {
        EntityManagerFactory factory = null;
        EntityManager em = null;
        EntityTransaction tx = null;


        try {
            factory = Persistence.createEntityManagerFactory("inifinite-finances");
            em = factory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            Bank bank = MyUtil.createBank();
            em.persist(bank);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
            factory.close();
        }
    }
}
