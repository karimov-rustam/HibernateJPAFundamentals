package edu.hibernate.data;

import edu.hibernate.data.entities.Bond;
import edu.hibernate.data.entities.Investment;
import edu.hibernate.data.entities.Portfolio;
import edu.hibernate.data.entities.Stock;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AppSingleTable {
    public static void main(String[] args) {
        SessionFactory sessionFactory = null;
        Session session = null;
        org.hibernate.Transaction  tx = null;

        try {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Portfolio portfolio = new Portfolio();
            portfolio.setName("First Investments");

            Stock stock = MyUtil.createStock();
            stock.setPortfolio(portfolio);

            Bond bond = MyUtil.createBond();
            bond.setPortfolio(portfolio);

            portfolio.getInvestments().add(stock);
            portfolio.getInvestments().add(bond);

            session.save(stock);
            session.save(bond);

            tx.commit();

            Portfolio dbPortfolio = (Portfolio) session.get(Portfolio.class, portfolio.getPortfolioId());
            session.refresh(dbPortfolio);

            for (Investment i : dbPortfolio.getInvestments()) {
                System.out.println(i.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
