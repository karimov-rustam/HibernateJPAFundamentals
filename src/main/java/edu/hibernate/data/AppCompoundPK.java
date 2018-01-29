package edu.hibernate.data;

import edu.hibernate.data.entities.Currency;
import edu.hibernate.data.entities.ids.CurrencyId;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AppCompoundPK {
    public static void main(String[] args) {

        SessionFactory sessionFactory = null;
        Session session = null;
        Session session2 = null;
        org.hibernate.Transaction tx = null;
        org.hibernate.Transaction tx2 = null;

        try {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Currency currency = new Currency();
            currency.setCountryName("United States");
            currency.setName("Dollar");
            currency.setSymbol("$");

            session.persist(currency);
            tx.commit();

            session2 = sessionFactory.openSession();
            tx2 = session2.beginTransaction();

            Currency dbCurrency = (Currency) session2.get(Currency.class,
                    new CurrencyId("Dollar", "United States"));

            System.out.println(dbCurrency.getName());

            tx2.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            if (tx2 != null) {
                tx2.rollback();
            }
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
