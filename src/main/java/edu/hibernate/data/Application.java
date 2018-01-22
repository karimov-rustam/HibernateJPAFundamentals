package edu.hibernate.data;

import edu.hibernate.data.entities.Address;
import edu.hibernate.data.entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;

public class Application {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            Transaction transaction = session.beginTransaction();

            User user = new User();
            Address address = new Address();

            user.setAge(22);
            user.setBirthDate(new Date());
            user.setCreatedBy("Rustam");
            user.setCreatedDate(new Date());
            user.setEmailAddress("test@test.com");
            user.setFirstName("Rustam");
            user.setLastName("Karimov");
            user.setLastUpdateby("Rustam");
            user.setLastUpdateDate(new Date());

            address.setAddressLine1("line 1");
            address.setAddressLine2("line 2");
            address.setCity("Samara");
            address.setState("Sa");
            address.setZipCode("44308");

            user.setAddress(address);
            session.save(user);

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            HibernateUtil.getSessionFactory().close();
        }
    }
}
