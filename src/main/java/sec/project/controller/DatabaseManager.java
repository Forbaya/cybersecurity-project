package sec.project.controller;

import org.h2.tools.RunScript;
import sec.project.domain.Signup;

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
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM Signup");
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
