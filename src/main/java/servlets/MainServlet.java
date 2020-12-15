package servlets;

import DAO.PatientDAO;
import DAO.PatientDAOImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/hello")
public class MainServlet extends HttpServlet {
    private final String url="jdbc:mysql://localhost:3306/test1?serverTimezone=Europe/Minsk&useSSL=false";
    private final String username="root";
    private final String password="root";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PatientDAO patientDAO = new PatientDAOImpl();
        req.setAttribute("patients", patientDAO.getAllPatients());
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/main.jsp");
        requestDispatcher.forward(req, resp);

    }
}
