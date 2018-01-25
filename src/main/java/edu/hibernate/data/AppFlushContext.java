package edu.hibernate.data;

import edu.hibernate.data.entities.Bank;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AppFlushContext {
    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try {
            Bank bank = (Bank) session.get(Bank.class, 1L);
            bank.setName("New Bank");
            System.out.println("Calling flush");
            session.flush();

            bank.setAddressLine1("Another Address Line");
            System.out.println("Calling commit");
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            HibernateUtil.getSessionFactory().close();
        }
    }
}
