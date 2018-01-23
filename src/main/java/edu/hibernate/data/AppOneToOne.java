package edu.hibernate.data;

import edu.hibernate.data.entities.Credential;
import edu.hibernate.data.entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;

public class AppOneToOne {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            Transaction transaction = session.beginTransaction();

            User user = new User();
            user.setFirstName("Rustam");
            user.setLastName("Karimov");
            user.setAge(34);
            user.setBirthDate(new Date());
            user.setCreatedDate(new Date());
            user.setCreatedBy("Rustam");
            user.setLastUpdateby("Rustam Karimov");
            user.setLastUpdateDate(new Date());
            user.setCreatedDate(new Date());
            user.setEmailAddress("test@test.com");

            Credential credential = new Credential();
            credential.setUsername("rustam");
            credential.setPassword("pass");
            credential.setUser(user);

            user.setCredential(credential);

            session.save(credential);
            transaction.commit();

            User dbUser = (User) session.get(User.class, credential.getUser().getUserId());
            System.out.println(dbUser.getFirstName()  );

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            HibernateUtil.getSessionFactory().close();
        }
    }
}
