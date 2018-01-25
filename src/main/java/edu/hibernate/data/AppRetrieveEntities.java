package edu.hibernate.data;

import edu.hibernate.data.entities.Bank;
import org.hibernate.Session;

import java.util.Date;

public class AppRetrieveEntities {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            org.hibernate.Transaction transaction = session.beginTransaction();
            Bank bank1 = (Bank) session.get(Bank.class, 1L);
            System.out.println("Method Executed");
            System.out.println(bank1.getName());

            Bank bank2 = (Bank) session.load(Bank.class, 1L);
            System.out.println("Method Executed");
            System.out.println(bank2.getName());

            bank1.setName("Sberbank");
            bank1.setLastUpdateBy("Rustam Karimov");
            bank1.setLastUpdatedDate(new Date());

            session.delete(bank1);
            System.out.println("Bank1 Deleted!");
            System.out.println(session.contains(bank1));

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            HibernateUtil.getSessionFactory().close();
        }
    }


}
