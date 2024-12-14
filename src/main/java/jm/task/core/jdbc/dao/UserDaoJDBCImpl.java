package jm.task.core.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

public class UserDaoJDBCImpl implements UserDao, AutoCloseable {
    private final Util util = new Util();
    private Statement statement = null;    

    public UserDaoJDBCImpl() {
        statement = util.openConnection();
    }

    @Override
    public void createUsersTable() {
        try {
            final String query = "CREATE TABLE IF NOT EXISTS users "
                .concat("(id INTEGER UNSIGNED not NULL AUTO_INCREMENT, ")
                .concat("name VARCHAR(16) not NULL, ")
                .concat("last_name VARCHAR (16) not NULL, ")
                .concat("age INTEGER not NULL, ")
                .concat("PRIMARY KEY (id))");
            
            statement.executeUpdate(query);            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {        
        try {
            statement.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        if (name.isEmpty() || lastName.isEmpty() || age < 5 || age > 126) return;
        final String query = "INSERT INTO users (name, last_name, age) ".concat("VALUES ")
                .concat(String.format("('%s', '%s', %d)", name, lastName, age));
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {
        final String query = "DELETE FROM users WHERE id = " + id;
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> lsUsers = new ArrayList<>();
        try (ResultSet resultSet = statement.executeQuery("SELECT * FROM users")) {
            User user = new User();
            while (resultSet.next()) {
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge((byte) resultSet.getInt("age"));
                lsUsers.add(new User(user));
            }                  
        } catch (SQLException e) {
            e.printStackTrace();
        }        
        return lsUsers;
    }

    @Override
    public void cleanUsersTable() {
        try {
            statement.executeUpdate("DELETE FROM users");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void close() throws Exception {
        util.closeConnection();
    }
}
