package controllers.reports;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Likes;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsCountLikeServlet
 */
@WebServlet("/reports/ReportsCountLikeServlet")
public class ReportsCountLikeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsCountLikeServlet() {
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
        Likes l = new Likes();

        int count = r.getCount_like();
        count++;
        r.setCount_like(count);

        l.setEmployee((Employee)(request.getSession().getAttribute("login_employee")));
        l.setReport(r);
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        l.setCreated_at(currentTime);
        l.setUpdated_at(currentTime);

        em.getTransaction().begin();
        em.persist(l);
        em.getTransaction().commit();
        em.close();

        request.getSession().setAttribute("flush", "日報にいいねしました!");
        response.sendRedirect(request.getContextPath() + "/reports/index");
    }
}
