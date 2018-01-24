package edu.hibernate.data;

import edu.hibernate.data.entities.Account;
import edu.hibernate.data.entities.User;
import org.hibernate.Session;

public class AppManyToMany {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            org.hibernate.Transaction transaction = session.beginTransaction();

            Account account1 = MyUtil.createNewAccount("Rustam", "ManyToMany1");
            Account account2 = MyUtil.createNewAccount("Rustam", "ManyToMany2");
            User user1 = MyUtil.createUser();
            User user2 = MyUtil.createUser();

            account1.getUser().add(user1);
            account1.getUser().add(user2);
            account2.getUser().add(user1);
            account2.getUser().add(user2);

            session.save(account1);
            session.save(account2);

            transaction.commit();

            Account dbAccount = (Account) session.get(Account.class, account1.getAccountId());
            System.out.println(dbAccount.getUser().iterator().next().getEmailAddress());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            HibernateUtil.getSessionFactory().close();
        }
    }
}
