package controller;

import model.Patient;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/hello")
public class MainServlet extends HttpServlet {
    private final String url="jdbc:mysql://localhost:3306/test1?serverTimezone=Europe/Minsk&useSSL=false";
    private final String username="root";
    private final String password="root";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT name, surname FROM t1");

            List<Patient> patients = new ArrayList<Patient>();

            while (resultSet.next()){
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                int age = 20;
                String disease = "head";
                boolean type = false;

                patients.add(new Patient(name, surname, age, disease, type));
            }

            req.setAttribute("patients", patients);
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/main.jsp");
            requestDispatcher.forward(req, resp);
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }


    }
}
