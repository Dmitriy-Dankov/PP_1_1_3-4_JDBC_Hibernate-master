package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

//import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        UserDaoHibernateImpl userDao = new UserDaoHibernateImpl();
        //try (UserDaoJDBCImpl userDao = new UserDaoJDBCImpl()) {
            userDao.createUsersTable();

            userDao.saveUser("Name_1", "Last_name_1", (byte) 18);
            userDao.saveUser("Name_2", "Last_name_2", (byte) 13);
            userDao.saveUser("Name_3", "Last_name_3", (byte) 42);
            userDao.saveUser("Name_4", "Last_name_4", (byte) 34);

            userDao.getAllUsers().stream().forEach(System.out::println);

            userDao.cleanUsersTable();
            userDao.dropUsersTable();
        //} catch (Exception e) {
        //    System.out.println(e.getMessage());
        //}
    }
}
