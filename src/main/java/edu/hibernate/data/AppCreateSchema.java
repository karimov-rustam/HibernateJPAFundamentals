package edu.hibernate.data;

import edu.hibernate.data.entities.Bank;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AppCreateSchema {
    public static void main(String[] args) {
        SessionFactory sessionFactory = null;
        Session session = null;
        org.hibernate.Transaction tx = null;

        try {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Bank bank = MyUtil.createBank();
            session.save(bank);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
