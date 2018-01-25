package edu.hibernate.data;

import edu.hibernate.data.entities.Bank;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AppReattach {
    public static void main(String[] args) {

        try {
            Session session1 = HibernateUtil.getSessionFactory().openSession();
            org.hibernate.Transaction transaction1 = session1.beginTransaction();
            Bank bank = (Bank) session1.get(Bank.class, 1L);
            transaction1.commit();
            session1.close();

            Session session2 = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction2 = session2.beginTransaction();

            System.out.println(session2.contains(bank));
            session2.update(bank);
            bank.setName("Test Bank");
            System.out.println("Method invoked");
            System.out.println(session2.contains(bank));
            session2.update(bank);

            transaction2.commit();
            session2.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.getSessionFactory().close();
        }
    }
}
