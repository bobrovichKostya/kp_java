package DAO;

import model.Doctor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAOImpl implements DoctorDAO {
    String url;
    String username;
    String password;
    Connection connection;

    public DoctorDAOImpl(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    private void connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            } catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
                e.printStackTrace();
            }
            connection = DriverManager.getConnection(url, username, password);
        }
    }

    private void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Override
    public List<Doctor> getAllDoctors() throws SQLException {
        List<Doctor> patients = new ArrayList<Doctor>();
        String sql = "SELECT id ,name, surname FROM doctors";

        connect();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");

            patients.add(new Doctor(id, name, surname));
        }

        resultSet.close();
        statement.close();

        disconnect();
        return patients;
    }
}
