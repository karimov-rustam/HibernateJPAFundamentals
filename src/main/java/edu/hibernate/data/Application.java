package edu.hibernate.data;

import edu.hibernate.data.entities.AccountType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.Date;
import java.util.Properties;

public class Application {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(AccountType.class);

        configuration.setProperties(new Properties() {
            {
                put("hibernate.connection.username", "lxuser");
                put("hibernate.connection.password", "mypass");
                put("hibernate.connection.driver_class",
                        "com.mysql.jdbc.Driver");
                put("hibernate.connection.url",
                        "jdbc:mysql://localhost:3306/ifinances");
            }
        });

        SessionFactory sessionFactory = configuration.buildSessionFactory(
                new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build());

        Session session = sessionFactory.openSession();

        session.beginTransaction();
        AccountType type = new AccountType();
        type.setName("Checking");
        type.setCreatedDate(new Date());
        type.setLastUpdatedDate(new Date());
        type.setCreatedBy("rustamkarimov");
        type.setLastUpdatedBy("rustamkarimov");
        session.save(type);
        session.getTransaction().commit();
        session.close();

    }
}
