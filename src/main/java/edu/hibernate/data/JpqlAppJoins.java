package edu.hibernate.data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

public class JpqlAppJoins {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        EntityManagerFactory factory = null;
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            factory = Persistence
                    .createEntityManagerFactory("infinite-finances");
            em = factory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            Query query = em.createQuery("select distinct t.account.name, " +
                    "concat(concat(t.account.bank.name, ' '), t.account.bank.address.state) " +
                    "from Transaction t " +
                    "where t.amount > 500 and  t.transactionType = 'Deposit'");

            Query query1 = em.createNamedQuery("Account.byWithdrawAmount");
            query1.setParameter("amount", new BigDecimal("99"));

            List<Object[]> accounts = query1.getResultList();

            for (Object[] a : accounts) {
                System.out.println(a[0]);
                System.out.println(a[1]);
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
            factory.close();
        }
    }
}
