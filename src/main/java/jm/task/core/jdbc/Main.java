package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService service = new UserServiceImpl();
        service.createUsersTable();

        service.saveUser("Name_1", "Last_name_1", (byte) 18);
        service.saveUser("Name_2", "Last_name_2", (byte) 13);
        service.saveUser("Name_3", "Last_name_3", (byte) 42);
        service.saveUser("Name_4", "Last_name_4", (byte) 34);

        service.getAllUsers().stream().forEach(System.out::println);

        service.cleanUsersTable();
        service.dropUsersTable();
    }
}
