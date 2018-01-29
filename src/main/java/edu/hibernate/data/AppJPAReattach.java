package edu.hibernate.data;

import edu.hibernate.data.entities.Bank;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class AppJPAReattach {

    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            emf = Persistence.createEntityManagerFactory("infinite-finances");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            Bank bank = em.find(Bank.class, 1L);
//            em.clear();
            em.detach(bank);
            System.out.println(em.contains(bank));

            bank.setName("New name");
            Bank bank2 = em.merge(bank);

            bank.setName("New second name");

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
