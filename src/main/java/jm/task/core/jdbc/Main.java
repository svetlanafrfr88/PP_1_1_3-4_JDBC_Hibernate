package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Олег", "Петров", (byte) 33);
        userService.saveUser("Павел", "Сидоров", (byte) 55);
        userService.saveUser("Игорь", "Лисов", (byte) 45);
        userService.saveUser("Николай", "Волков", (byte) 66);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
