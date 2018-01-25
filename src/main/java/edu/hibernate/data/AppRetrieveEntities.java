package edu.hibernate.data;

import edu.hibernate.data.entities.Bank;
import org.hibernate.Session;

public class AppRetrieveEntities {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            org.hibernate.Transaction transaction = session.beginTransaction();
            Bank bank1 = (Bank) session.get(Bank.class, 1L);
            System.out.println("Method Executed");
            System.out.println(bank1.getName());

            Bank bank2 = (Bank) session.load(Bank.class, 1L);
            System.out.println("Method Executed");
            System.out.println(bank2.getName());

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            HibernateUtil.getSessionFactory().close();
        }
    }


}
