package edu.hibernate.data;

import edu.hibernate.data.entities.Account;
import edu.hibernate.data.entities.Transaction;
import org.hibernate.Session;

public class AppSaveEntities {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Account account = MyUtil.createNewAccount("Rustam", "Savings account");

        Transaction trans1 = MyUtil.createNewBeltPurchase(account);
        Transaction trans2 = MyUtil.createNewShoePurchase(account);
        account.getTransactions().add(trans1);
        account.getTransactions().add(trans2);

        System.out.println(session.contains(account));
        System.out.println(session.contains(trans1));
        System.out.println(session.contains(trans2));

        try {
            org.hibernate.Transaction transaction = session.beginTransaction();

            session.save(account);

            System.out.println(session.contains(account));
            System.out.println(session.contains(trans1));
            System.out.println(session.contains(trans2));

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            HibernateUtil.getSessionFactory().close();
        }
    }


}
