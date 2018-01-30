package edu.hibernate.data;

import edu.hibernate.data.entities.Account;
import edu.hibernate.data.entities.AccountType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AppEnumerations {

    public static void main(String[] args) {
        SessionFactory sessionFactory = null;
        Session session = null;
        org.hibernate.Transaction tx = null;

        try {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Account account = MyUtil.createNewAccount("Rustam", "Test");
            account.setAccountType(AccountType.SAVINGS);
            session.save(account);
            tx.commit();

            Account dbAccount = (Account) session.get(Account.class, account.getAccountId());
            System.out.println(dbAccount.getName());
            System.out.println(dbAccount.getAccountType());

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
