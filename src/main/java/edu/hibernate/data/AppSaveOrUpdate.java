package edu.hibernate.data;

import edu.hibernate.data.entities.Bank;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AppSaveOrUpdate {
    public static void main(String[] args) {

        try {
            Session session1 = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction1 = session1.beginTransaction();
            Bank detachedBank = (Bank) session1.get(Bank.class, 1L);
            transaction1.commit();
            session1.close();

            Bank transientBank = MyUtil.createBank();

            Session session2 = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction2 = session2.beginTransaction();

            session2.saveOrUpdate(transientBank);
            session2.saveOrUpdate(detachedBank);
            detachedBank.setName("Test Bank 2");
            transaction2.commit();
            session2.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.getSessionFactory().close();
        }
    }
}
