package edu.hibernate.data;

import edu.hibernate.data.entities.Bank;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;

public class ApplicationBank {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            Transaction transaction = session.beginTransaction();

            Bank bank = new Bank();
            bank.setName("Sberbank");
            bank.setAddressLine1("K.Marks st. 18");
            bank.setAddressLine2("Building 2");
            bank.setCity("Samara");
            bank.setState("SA");
            bank.setZipCode("12345");
            bank.setInternational(false);
            bank.setCreatedBy("Rustam");
            bank.setCreatedDate(new Date());
            bank.setLastUpdateBy("Rustam");
            bank.setLastUpdatedDate(new Date());
            bank.getContacts().put("MANAGER","Joe");
            bank.getContacts().put("TELLER","Mary");
            session.save(bank);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            HibernateUtil.getSessionFactory().close();
        }
    }
}
