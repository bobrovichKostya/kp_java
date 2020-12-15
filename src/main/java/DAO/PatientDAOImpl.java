package DAO;

import model.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAOImpl implements PatientDAO {
    private final String url = "jdbc:mysql://localhost:3306/test1?serverTimezone=Europe/Minsk&useSSL=false";
    private final String username = "root";
    private final String password = "root";

    public PatientDAOImpl() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<Patient>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT name, surname FROM t1");
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                int age = 20;
                String disease = "head";
                boolean type = false;

                patients.add(new Patient(name, surname, age, disease, type));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return patients;
    }

    public void addPatient(Patient patient) {

    }

    public void deletePatient(int id) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "delete from t1 where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int add = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void editPatient(Patient patient) {

    }
}
