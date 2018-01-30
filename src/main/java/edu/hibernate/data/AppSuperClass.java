package edu.hibernate.data;

import edu.hibernate.data.entities.Bond;
import edu.hibernate.data.entities.Stock;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AppSuperClass {

    public static void main(String[] args) {
        SessionFactory sessionFactory = null;
        Session session = null;
        org.hibernate.Transaction tx = null;

        try {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Stock stock = MyUtil.createStock();
            session.save(stock);

            Bond bond = MyUtil.createBond();
            session.save(bond);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
