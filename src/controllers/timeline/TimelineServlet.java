package controllers.timeline;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class TimelineServlet
 */
@WebServlet("/timeline/index")
public class TimelineServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimelineServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        EntityManager em = DBUtil.createEntityManager();
        Employee login_employee = (Employee)(request.getSession().getAttribute("login_employee"));

        int page;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        } catch(Exception e) {
            page = 1;
        }

        List<Report> followed_all_reports = em.createNamedQuery("getAllFollowedReports", Report.class)
                                              .setParameter("employee1", login_employee)
                                              .setFirstResult(15 * (page - 1))
                                              .setMaxResults(15)
                                              .getResultList();

        long followed_all_reports_count = (long)em.createNamedQuery("getAllFollowedReportsCount", Long.class)
                                            .setParameter("employee1", login_employee)
                                            .getSingleResult();

        em.close();

        request.setAttribute("f_a_r", followed_all_reports);
        request.setAttribute("f_a_r_c", followed_all_reports_count);
        request.setAttribute("page", page);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/timeline/index.jsp");
        rd.forward(request, response);


    }

}
