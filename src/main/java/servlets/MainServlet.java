package servlets;

import DAO.DoctorDAO;
import DAO.DoctorDAOImpl;
import DAO.PatientDAO;
import DAO.PatientDAOImpl;
import model.Doctor;
import model.Patient;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet(urlPatterns = {"/main", "/new", "/edit", "/update", "/delete", "/insert", "/persPatient"})
public class MainServlet extends HttpServlet {
    private String url = "jdbc:mysql://localhost:3306/test1?serverTimezone=Europe/Minsk&useSSL=false";
    private String username = "root";
    private String password = "root";
    private PatientDAO patientDAO;
    private DoctorDAO doctorDAO;


    @Override
    public void init() {
        patientDAO = new PatientDAOImpl(url, username, password);
        doctorDAO = new DoctorDAOImpl(url, username, password);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        try{
            switch (action){
                case "/new":
                    showNewForm(req, resp);
                    break;
                case "/edit":
                    showEditForm(req, resp, false);
                    break;
                case "/insert":
                    addPatient(req, resp);
                    break;
                case "/update":
                    editPatient(req, resp);
                    break;
                case "/delete":
                    deletePatient(req, resp);
                    break;
                case "/persPatient":
                    showEditForm(req, resp, true);
                    break;
                case "/main":
                    allPations(req, resp);
                    break;
            }
        } catch (SQLException e){
            System.err.println(e);
        }
    }


    private void addPatient(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        int age = Integer.parseInt(req.getParameter("age"));
        String disease = req.getParameter("disease");
        boolean type = Boolean.parseBoolean(req.getParameter("type"));
        int docId = Integer.parseInt(req.getParameter("doc"));

        Patient patient = new Patient(name, surname, age, disease, type, docId);
        patientDAO.addPatient(patient);
        resp.sendRedirect("/main");
    }

    private void deletePatient(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        patientDAO.deletePatient(id);
        resp.sendRedirect("/main");
    }

    private void allPations(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        List<Patient> patientList = patientDAO.getAllPatients();
        req.setAttribute("patients", patientList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/main.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void allDoctors(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        List<Doctor> doctorList = doctorDAO.getAllDoctors();
        req.setAttribute("doctors", doctorList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/main.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        List<Doctor> doctorList = doctorDAO.getAllDoctors();
        req.setAttribute("doctors", doctorList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/edit.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp, boolean showFullPage) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        Patient currentPatient = patientDAO.getById(id);
        List<Doctor> doctorList = doctorDAO.getAllDoctors();
        req.setAttribute("doctors", doctorList);
        req.setAttribute("patient", currentPatient);
        RequestDispatcher requestDispatcher = null;
        if(showFullPage){
            requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/personalPatient.jsp");
        } else{
             requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/edit.jsp");
        }

        requestDispatcher.forward(req, resp);
    }

    private void editPatient(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        int age = Integer.parseInt(req.getParameter("age"));
        String disease = req.getParameter("disease");
        boolean type = Boolean.parseBoolean(req.getParameter("type"));
        int docId = Integer.parseInt(req.getParameter("doc"));

        Patient patient = new Patient(id, name, surname, age, disease, type, docId);
        patientDAO.editPatient(patient);
        resp.sendRedirect("/main");
    }
}
