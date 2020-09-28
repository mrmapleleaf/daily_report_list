package controllers.follows;

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
import models.Follow;
import utils.DBUtil;

/**
 * Servlet implementation class FollowedIndexServlet
 */
@WebServlet("/follows/followedIndex")
public class FollowedIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowedIndexServlet() {
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

        List<Follow> followed_list = em.createNamedQuery("getAllFollowed", Follow.class)
                                       .setParameter("employee2", login_employee)
                                       .setFirstResult(15 * (page - 1))
                                       .setMaxResults(15)
                                       .getResultList();

        long followed_list_count = em.createNamedQuery("getAllFollowedCount", Long.class)
                                     .setParameter("employee2", login_employee)
                                     .getSingleResult();

        em.close();

        request.setAttribute("followed_list", followed_list);
        request.setAttribute("followed_list_count", followed_list_count);
        request.setAttribute("page", page);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/followed/index.jsp");
        rd.forward(request, response);

    }

}
