package edu.hibernate.data;

import edu.hibernate.data.entities.AccountType;
import org.hibernate.cfg.Configuration;

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
                        "jdbc:/mysql://localhost:3306/ifinances");
            }
        });
    }
}
