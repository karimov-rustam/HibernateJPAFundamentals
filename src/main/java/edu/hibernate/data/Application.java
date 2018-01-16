package edu.hibernate.data;

import edu.hibernate.data.entities.User;
import org.hibernate.Session;

import java.util.Date;

public class Application {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        session.getTransaction().begin();

        User user = new User();
        user.setBirthDate(new Date());
        user.setCreatedDate(new Date());
        user.setCreatedBy("Rustam");
        user.setEmailAddress("test@test.com");
        user.setFirstName("Rustam");
        user.setLastName("Karimov");
        user.setLastUpdateby("Rustam");
        user.setLastUpdateDate(new Date());

        session.save(user);
        session.getTransaction().commit();
        session.close();
    }
}
