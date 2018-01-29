package edu.hibernate.data;

import edu.hibernate.data.entities.Currency;
import edu.hibernate.data.entities.Market;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AppCompoundJoinColumns {
    public static void main(String[] args) {
        SessionFactory sessionFactory = null;
        Session session = null;
        org.hibernate.Transaction tx = null;

        try {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Currency currency = new Currency();
            currency.setCountryName("United Kingdom");
            currency.setName("Pound");
            currency.setSymbol("Pound Sign");

            Market market = new Market();
            market.setMarketName("London Stock Exchange");
            market.setCurrency(currency);

            session.persist(market);
            tx.commit();

            Market dbMarket = (Market) session.get(Market.class, market.getMarketId());
            System.out.println(dbMarket.getCurrency().getName());

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
