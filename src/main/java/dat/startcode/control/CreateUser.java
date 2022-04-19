package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.UserMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "createuser", urlPatterns = {"/createuser"} )
public class CreateUser extends HttpServlet
{
    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        // You shouldn't end up here with a GET-request, thus you get sent back to frontpage
        doPost(request, response);
        response.sendRedirect("index.jsp");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        session.setAttribute("user", null); // adding empty user object to session scope
        UserMapper userMapper = new UserMapper(connectionPool);
        User user = null;

        String username = request.getParameter("username");

        String password = "";
        String password1 = request.getParameter("password1"); // første indtastede password
        String password2 = request.getParameter("password2"); // andet indtastede password
        if (!password1.equals(password2))
        {
            request.setAttribute("errormsg", "You failed to confirm your password.");
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        }
        if (password1.equals(password2))
        {
            password = request.getParameter("email1");
        }

        String email = "";
        String email1 = request.getParameter("email1");
        String email2 = request.getParameter("email2");
        if (!email1.equals(email2)) {
            request.setAttribute("errormsg", "you failed to confirm your email.");
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        }
        if (email1.equals(email2)) {
            email = request.getParameter("email1");
        }

        boolean isAdmin = false;

        try
        {
            user = userMapper.createUser(username, password, email, isAdmin);
            session = request.getSession();
            session.setAttribute("user", user);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        catch (DatabaseException e)
        {
            Logger.getLogger("web").log(Level.SEVERE, e.getMessage());
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }


}
