package edu.hibernate.data;

import edu.hibernate.data.entities.Transaction;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;

public class JpaApplication {

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

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Transaction> criteriaQuery = cb.createQuery(Transaction.class);

            Root<Transaction> root = criteriaQuery.from(Transaction.class);
            Path<BigDecimal> amountPath = root.get("amount");
            Path<String> transactionTypePath = root.get("transactionType");

            criteriaQuery.select(root).where(cb.and(cb.le(amountPath, new BigDecimal("20.0")),
                    cb.equal(transactionTypePath, "Withdrawl")));

            TypedQuery<Transaction> query = em.createQuery(criteriaQuery);
            List<Transaction> transactions = query.getResultList();

            for (Transaction t :
                    transactions) {
                System.out.println(t.getTitle());
            }

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
            factory.close();
        }
    }
}
