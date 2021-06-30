package rse.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import engine.logic.Engine;
import engine.users.UsersManager;
public class LoginServlet extends HttpServlet {

    String dashboard_url = "/../../../pages/dashboard/dashboard.html";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String isAdmin  = request.getParameter("is_admin");
        boolean admin = isAdmin==null ? false:true;
        out.print("<h1>Hi " +username +", Admin: " + admin +"</h1>");
        UsersManager um = Engine.getInstance().getUsersManager();
        if(um.isExists(username))
        {
            int x=15;
            //TODO: ERROR Message
        } else {
            try {
                um.addUser(username, (isAdmin.equals("admin") ? true : false));
                out.print("<br><h1>Success!</h1>");
                response.sendRedirect(dashboard_url);

            } catch (IllegalArgumentException e) {
                response.sendError(600, e.getMessage());
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request,response);
    }

}
