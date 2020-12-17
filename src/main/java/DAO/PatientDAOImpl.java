package DAO;

import model.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAOImpl implements PatientDAO {
    String url;
    String username;
    String password;
    Connection connection;

    public PatientDAOImpl(String url, String username, String password){
        this.url = url;
        this.username = username;
        this.password = password;
    }

    private void connect() throws SQLException {
        if(connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            } catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
                e.printStackTrace();
            }
            connection = DriverManager.getConnection(url, username, password);
        }
    }

    private void disconnect() throws SQLException{
        if(connection != null && !connection.isClosed()){
            connection.close();
        }
    }

    public List<Patient> getAllPatients() throws SQLException {
        List<Patient> patients = new ArrayList<Patient>();
        String sql = "SELECT id ,name, surname FROM t1";

        connect();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                int age = 20;
                String disease = "head";
                boolean type = false;

                patients.add(new Patient(id, name, surname, age, disease, type));
            }

        resultSet.close();
        statement.close();

        disconnect();
        return patients;
    }

    public void addPatient(Patient patient) throws SQLException {
        String sql = "insert into t1 (name, surname) values(?, ?)";

        connect();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, patient.getName());
        preparedStatement.setString(2, patient.getSurname());
        int add = preparedStatement.executeUpdate();

        preparedStatement.close();
        disconnect();

        /*try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "insert into t1 (name, surname) values(?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, patient.getName());
            preparedStatement.setString(2, patient.getSurname());
            int add = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/
    }

    public void deletePatient(int id) throws SQLException {
        String sql = "delete from t1 where id=?";

        connect();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        int add = preparedStatement.executeUpdate();

        preparedStatement.close();
        disconnect();

    }

    public void editPatient(Patient patient) throws SQLException {
        String sql = "update test1 set name = ?, surname = ? where id = ?";

        connect();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, patient.getName());
        preparedStatement.setString(2, patient.getSurname());
        preparedStatement.setString(3, String.valueOf(patient.getId()));

        int i = preparedStatement.executeUpdate();

        preparedStatement.close();
        disconnect();
    }

    public Patient getById(int id) throws SQLException {
        Patient patient = new Patient();
        patient.setId(id);
        String sql = "select name, surname from t1 where id = ?";

        connect();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            patient.setName(resultSet.getString("name"));
            patient.setSurname(resultSet.getString("name"));
            patient.setAge(20);
            patient.setDisease("head");
            patient.setType(false);
        }

        resultSet.close();
        preparedStatement.close();
        disconnect();

        return patient;
    }

}
