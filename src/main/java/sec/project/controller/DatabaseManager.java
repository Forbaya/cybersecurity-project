package sec.project.controller;

import org.h2.tools.RunScript;
import sec.project.domain.Signup;
import sec.project.domain.User;
import sec.project.domain.UserRole;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    public Connection createConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:h2:file:./database", "sa", "");
            RunScript.execute(connection, new FileReader("src/main/resources/Schema.sql"));
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM User");
            if (!resultSet.first()) {
                RunScript.execute(connection, new FileReader("src/main/resources/Import.sql"));
            }
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public void insertSignup(String name, String address) {
        try {
            Connection connection = createConnection();
            connection.createStatement().   executeUpdate("INSERT INTO Signup(Name, Address) VALUES ('" + name + "', '" + address + "');");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList();

        try {
            Connection connection = createConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM User;");

            while (resultSet.next()) {
                Long id = resultSet.getLong("Id");
                String username = resultSet.getString("Username");
                String email = resultSet.getString("Email");
                String password = resultSet.getString("Password");
                User user = new User(id, username, email, password);

                users.add(user);
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public User findUserByUsername(String username, List<User> users) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }

        return null;
    }

    public List<UserRole> getUserRoles() {
        List<UserRole> userRoles = new ArrayList();

        try {
            Connection connection = createConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM UserRole;");

            while (resultSet.next()) {
                Long id = resultSet.getLong("Id");
                Long userId = resultSet.getLong("UserId");
                String role = resultSet.getString("Role");
                UserRole userRole = new UserRole(id, userId, role);

                userRoles.add(userRole);
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userRoles;
    }

    public List<String> findUserRolesByUser(Long userId) {
        List<String> userRoles = new ArrayList();

        List<UserRole> allUserRoles = getUserRoles();
        for (UserRole userRole  : allUserRoles) {
            if (userRole.getUserId() == userId) {
                userRoles.add(userRole.getRole());
            }
        }

        return userRoles;
    }

    public List<Signup> getSignups() {
        List<Signup> signups = new ArrayList();

        try {
            Connection connection = createConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM Signup;");

            while (resultSet.next()) {
                Long id = resultSet.getLong("Id");
                String name = resultSet.getString("Name");
                String address = resultSet.getString("Address");
                Signup signup = new Signup(id, name, address);

                signups.add(signup);
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return signups;
    }
}
