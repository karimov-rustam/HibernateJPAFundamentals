package edu.hibernate.data;

import edu.hibernate.data.entities.Bank;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class AppJPAConfig {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("infinite-finances");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Bank bank = MyUtil.createBank();
        em.persist(bank);

        tx.commit();
        em.close();
        emf.close();
    }
}
