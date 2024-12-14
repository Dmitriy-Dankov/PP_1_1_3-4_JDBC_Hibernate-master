package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import jm.task.core.jdbc.model.User;

public class Util {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/db_users";
    private Connection connection = null;
    private Statement statement = null;

    private static SessionFactory sessionFactory = null;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Properties properties = new Properties();
            properties.put("hibernate.connection.driver_class", DRIVER);
            properties.put("dialect", "org.hibernate.dialect.MySQL5Dialect");
            properties.put("hibernate.connection.url", URL);
            properties.put("hibernate.connection.username", USER_NAME);
            properties.put("hibernate.connection.password", PASSWORD);
            properties.put("hibernate.connection.pool_size", "3");
            properties.put("hibernate.connection.autocommit", "true");
            //properties.put("hibernate.hbm2ddl.auto", "create-drop");

            properties.put("hibernate.show_sql", "true");
            //properties.put("hibernate.format_sql", "true");
            //properties.put("hibernate.use_sql_comments", "true");

            try {
                Configuration configuration = new Configuration();
                configuration.addAnnotatedClass(User.class);
                configuration.setProperties(properties);

                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
                builder.applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
                System.out.println("////////////////////////////////////////////////////////////");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return sessionFactory;
    }

    public Statement openConnection() {
        System.out.println("connect...");
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("connection failed");
            System.exit(1);
        }        

        System.out.println("Executing statement...");
        try {
            statement = connection.createStatement();
        } catch (Exception e) {
            System.out.println("Statment not formed");
            System.exit(1);
        }
        return statement;
    }

    public void closeConnection() {
        try {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            System.out.println("Error closing statement or connection");
            System.exit(1);
        }
    }
}