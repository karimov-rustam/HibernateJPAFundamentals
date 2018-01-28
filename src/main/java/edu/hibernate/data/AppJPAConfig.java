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
            factory = Persistence.createEntityManagerFactory("infinite-finances");
            em = factory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            //save entity
            Bank bank = MyUtil.createBank();
            em.persist(bank);

            //retrieve entity
            Bank dbBank = em.find(Bank.class, 1L);
            System.out.println(em.contains(dbBank));
            System.out.println(dbBank.getName());

            Bank dbBank2 = em.getReference(Bank.class, 1234L);
            System.out.println(em.contains(dbBank2));
            System.out.println(dbBank2.getName());

            dbBank.setName("Update Name");

            em.remove(dbBank);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
            factory.close();
        }
    }
}
