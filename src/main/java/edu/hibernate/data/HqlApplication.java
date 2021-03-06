package edu.hibernate.data;

import edu.hibernate.data.entities.Transaction;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class HqlApplication {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        SessionFactory sessionFactory = null;
        Session session = null;
        org.hibernate.Transaction tx = null;

        try {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Query query = session.createQuery("select t from Transaction t " +
                    "where t.amount > :amount and t.transactionType = 'Withdrawl'");
            System.out.println("Please specify an amount: ");

            query.setParameter("amount", new BigDecimal(scanner.next()));

            List<Transaction> transactions = query.list();

            for (Transaction t : transactions) {
                System.out.println(t.getTitle());
            }

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
