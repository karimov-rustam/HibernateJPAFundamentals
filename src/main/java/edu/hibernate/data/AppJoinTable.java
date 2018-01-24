package edu.hibernate.data;

import edu.hibernate.data.entities.Account;
import edu.hibernate.data.entities.Budget;
import org.hibernate.Session;

import java.math.BigDecimal;

import static edu.hibernate.data.MyUtil.createNewAccount;
import static edu.hibernate.data.MyUtil.createNewBeltPurchase;
import static edu.hibernate.data.MyUtil.createNewShoePurchase;

public class AppJoinTable {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            org.hibernate.Transaction transaction = session.beginTransaction();

            Account account = createNewAccount("Rustam", "Savings Account");

            Budget budget = new Budget();
            budget.setGoalAmount(new BigDecimal("10000.0"));
            budget.setName("Emergency fund");
            budget.setPeriod("Yearly");

            budget.getTransactions().add(createNewBeltPurchase(account));
            budget.getTransactions().add(createNewShoePurchase(account));

            session.save(budget);
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            HibernateUtil.getSessionFactory().close();
        }
    }
}
