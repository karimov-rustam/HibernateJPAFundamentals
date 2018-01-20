package edu.hibernate.data;

import edu.hibernate.data.entities.TimeTest;
import org.hibernate.Session;

import java.util.Date;

public class Application {

    public static void main(String[] args) {

//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.getTransaction().begin();
//
//        User user = new User();
//        user.setBirthDate(new Date());
//        user.setCreatedDate(new Date());
//        user.setCreatedBy("Rustam");
//        user.setEmailAddress("test@test.com");
//        user.setFirstName("Rustam");
//        user.setLastName("Karimov");
//        user.setLastUpdateby("Rustam");
//        user.setLastUpdateDate(new Date());
//
//        session.save(user);
//        session.getTransaction().commit();
//        session.close();

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.getTransaction().begin();
            TimeTest test = new TimeTest(new Date());
            session.save(test);
            session.getTransaction().commit();
            session.refresh(test);
            System.out.println(test.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            HibernateUtil.getSessionFactory().close();
        }
    }
}
