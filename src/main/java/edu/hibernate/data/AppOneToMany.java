package edu.hibernate.data;

import edu.hibernate.data.entities.Account;
import edu.hibernate.data.entities.Transaction;
import org.hibernate.Session;

public class AppOneToMany {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            org.hibernate.Transaction transaction = session.beginTransaction();

            Account account = MyUtil.createNewAccount("Rustam", "OneToMany");
            account.getTransactions().add(MyUtil.createNewBeltPurchase(account));
            account.getTransactions().add(MyUtil.createNewShoePurchase(account));
            session.save(account);

            transaction.commit();

            Transaction dbTransaction = (Transaction) session.get(Transaction.class,
                    account.getTransactions().get(0).getTransactionId());
            System.out.println(dbTransaction.getAccount().getName());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            HibernateUtil.getSessionFactory().close();
        }
    }

}
