package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/db_users";
    private Connection connection = null;
    private Statement statement = null;

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