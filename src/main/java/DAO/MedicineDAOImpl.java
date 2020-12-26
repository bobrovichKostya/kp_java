package DAO;

import model.Medicine;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicineDAOImpl implements MedicineDAO{
    String url;
    String username;
    String password;
    Connection connection;

    public MedicineDAOImpl(String url, String username, String password) {
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
    public List<Medicine> getAllMedicine() throws SQLException {
        List<Medicine> medicineList = new ArrayList<>();
        String sql = "select id, name, count, morning, noon, evening from medicine";

        connect();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int count = resultSet.getInt("count");
            boolean morning = resultSet.getBoolean("morning");
            boolean noon = resultSet.getBoolean("noon");
            boolean evening = resultSet.getBoolean("evening");

            medicineList.add(new Medicine(id, name, count, morning, noon, evening));
        }

        resultSet.close();
        statement.close();

        disconnect();

        return medicineList;
    }

    @Override
    public void addMedicine(Medicine medicine) throws SQLException {
        String sql = "insert into medicine (name, count, morning, noon, evening) values(?, ?, ?, ?, ?)";

        connect();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, medicine.getName());
        preparedStatement.setInt(2, medicine.getCount());
        preparedStatement.setBoolean(3, medicine.isMorning());
        preparedStatement.setBoolean(4, medicine.isNoon());
        preparedStatement.setBoolean(5, medicine.isEvening());
        int add = preparedStatement.executeUpdate();

        preparedStatement.close();
        disconnect();
    }

    @Override
    public void deleteMedicine(int id) throws SQLException {
        String sql = "delete from medicine where id=?";

        connect();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        int add = preparedStatement.executeUpdate();

        preparedStatement.close();
        disconnect();
    }

    @Override
    public void editMedicine(Medicine medicine) throws SQLException {
        String sql = "update medicine set name = ?, count = ?, morning = ?, noon = ?, evening = ? where id = ?";

        connect();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, medicine.getName());
        preparedStatement.setInt(2, medicine.getCount());
        preparedStatement.setBoolean(3, medicine.isMorning());
        preparedStatement.setBoolean(4, medicine.isNoon());
        preparedStatement.setBoolean(5, medicine.isEvening());
        preparedStatement.setInt(6, medicine.getId());
        int add = preparedStatement.executeUpdate();

        preparedStatement.close();
        disconnect();
    }

    @Override
    public Medicine getById(int id) throws SQLException {
        Medicine medicine = new Medicine();
        medicine.setId(id);

        String sql="select name, count, morning, noon, evening from medicine where id = ?";

        connect();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            medicine.setName(resultSet.getString("name"));
            medicine.setCount(resultSet.getInt("count"));
            medicine.setEvening(resultSet.getBoolean("morning"));
            medicine.setEvening(resultSet.getBoolean("noon"));
            medicine.setEvening(resultSet.getBoolean("evening"));
        }

        resultSet.close();
        preparedStatement.close();
        disconnect();

        return medicine;
    }
}
