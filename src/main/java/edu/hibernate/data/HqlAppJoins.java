package edu.hibernate.data;

import edu.hibernate.data.entities.Account;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class HqlAppJoins {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        SessionFactory factory = null;
        Session session = null;
        org.hibernate.Transaction tx = null;

        try {
            factory = HibernateUtil.getSessionFactory();
            session = factory.openSession();
            tx = session.beginTransaction();

            Query query = session.createQuery("select distinct t.account from Transaction t " +
                    "where t.amount > 500 and lower(t.transactionType) = 'deposit'");

            Query query1 = session.getNamedQuery("Account.largeDeopsits");

            List<Account> accounts = query1.list();
            System.out.println("Query has been executed. ");

            for (Account a : accounts) {
                System.out.println(a.getName());
                System.out.println(a.getBank().getName());
            }

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
            factory.close();
        }
    }
}
