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
            ResultSet resultSet = statement.executeQuery("SELECT id ,name, surname FROM t1");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                int age = 20;
                String disease = "head";
                boolean type = false;

                patients.add(new Patient(id, name, surname, age, disease, type));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return patients;
    }

    public void addPatient(Patient patient) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "insert into t1 (name, surname) values(?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, patient.getName());
            preparedStatement.setString(2, patient.getSurname());
            int add = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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

    public void editPatient(int id, Patient patient) {

    }

    public Patient getById(int id) {
        Patient patient = new Patient();
        try(Connection connection = DriverManager.getConnection(url, username, password)){
            String sql = "select name, surname from t1 where id =?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
                patient.setName(resultSet.getString("name"));
                patient.setSurname(resultSet.getString("name"));
                patient.setAge(20);
                patient.setDisease("head");
                patient.setType(false);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return patient;
    }

}
