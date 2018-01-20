package edu.hibernate.data;

import edu.hibernate.data.entities.TimeTest;
import edu.hibernate.data.entities.User;
import org.hibernate.Session;

import java.util.Calendar;
import java.util.Date;

public class Application {

    public static void main(String[] args) {

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
        }

        try {
            session.getTransaction().begin();

            User user = new User();
            user.setBirthDate(getMyBirthday());
            user.setCreatedDate(new Date());
            user.setCreatedBy("Rustam");
            user.setEmailAddress("test@test.com");
            user.setFirstName("Rustam");
            user.setLastName("Karimov");
            user.setLastUpdateby("Rustam");
            user.setLastUpdateDate(new Date());

            session.save(user);
            session.getTransaction().commit();

            session.refresh(user);
            System.out.println(user.getAge());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            HibernateUtil.getSessionFactory().close();
        }
    }

    private static Date getMyBirthday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 1984);
        calendar.set(Calendar.MONTH, 6);
        calendar.set(Calendar.DATE, 19);
        return calendar.getTime();
    }
}
