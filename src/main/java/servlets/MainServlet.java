package servlets;

import DAO.PatientDAO;
import DAO.PatientDAOImpl;
import model.Patient;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/hello")
public class MainServlet extends HttpServlet {
    Patient patient = new Patient(100, "BOBBBR", "AAAAAAAAA", 20, "tdyuj", true);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PatientDAO patientDAO = new PatientDAOImpl();
        req.setAttribute("patients", patientDAO.getAllPatients());
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/main.jsp");
        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("check");

        if (action.equals("delete")) {
            String[] idList = req.getParameterValues("id");
            //TODO реализвать нормально удаление нескольких эелементов и редактирование ( по одному )
            if (idList != null && idList.length > 0) {
                PatientDAO patientDAO = new PatientDAOImpl();
                for (int i = 0; i < idList.length; i++) {
                    patientDAO.deletePatient(Integer.parseInt(idList[i]));
                }
            }
            doGet(req, resp);
        } else if (action.equals("edit")) {
            Patient patient1 = null;
            String[] idList = req.getParameterValues("id");
            if (idList != null && idList.length > 0) {
                PatientDAO patientDAO = new PatientDAOImpl();
                for (int i = 0; i < idList.length; i++) {
                    patient1 = patientDAO.getById(Integer.parseInt(idList[i]));
                }
            }
            req.setAttribute("editPatient", patient1);
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/EditServlet");
            requestDispatcher.forward(req, resp);
        }

    }
}
