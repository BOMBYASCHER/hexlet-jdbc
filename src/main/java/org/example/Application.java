package org.example;

import org.example.dao.UserDAO;
import org.example.model.User;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Application {
    public static void main(String[] args) throws SQLException {
        try (var conn = DriverManager.getConnection("jdbc:h2:mem:hexlet_test")) {

            var sql = "CREATE TABLE users (id BIGINT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(255), phone VARCHAR(255))";
            try (var statement = conn.createStatement()) {
                statement.execute(sql);
            }
            var userDAO = new UserDAO(conn);
            var user0 = new User("admin", "000000000");
            var user1 = new User("tommy", "123456789");
            var user2 = new User("kyle", "987654321");
            userDAO.save(user0);
            userDAO.save(user1);
            userDAO.save(user2);
            System.out.println("All users:\n" + userDAO.entries());
            System.out.println("Find admin (id = 1):\n" + userDAO.find(1L));
            userDAO.delete(user0.getId());
            System.out.println("Try to find admin after delete (by id = 1):\n" + userDAO.find(1L));
            System.out.println("All users:\n" + userDAO.entries());
        }
    }
}
