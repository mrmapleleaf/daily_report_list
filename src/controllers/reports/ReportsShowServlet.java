package controllers.reports;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsShowServlet
 */
@WebServlet("/reports/show")
public class ReportsShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        EntityManager em = DBUtil.createEntityManager();

        Report r = em.find(Report.class, Integer.parseInt(request.getParameter("id")));
        Employee e = r.getEmployee();

        Employee login_employee = (Employee)request.getSession().getAttribute("login_employee");

        long Liked_Already_Count = (long)em.createNamedQuery("checkLikedAlready", Long.class)
                                           .setParameter("employee", login_employee)
                                           .setParameter("report", r)
                                           .getSingleResult();

        long Followed_Already_Count = (long)em.createNamedQuery("checkFollowedAlready", Long.class)
                                              .setParameter("employee1", login_employee)
                                              .setParameter("employee2", e)
                                              .getSingleResult();

        em.close();

        request.setAttribute("report", r);
        request.setAttribute("Liked_Already_Count", Liked_Already_Count);
        request.setAttribute("Followed_Already_Count", Followed_Already_Count);
        request.setAttribute("_token", request.getSession().getId());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/show.jsp");
        rd.forward(request, response);
    }

}
