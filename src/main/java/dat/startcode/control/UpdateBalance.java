package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.UserMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "UpdateBalance", urlPatterns = {"/UpdateBalance"})
public class UpdateBalance extends HttpServlet {

    private UserMapper userMapper;
    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException {
        this.connectionPool = ApplicationStart.getConnectionPool();
        userMapper = new UserMapper(connectionPool);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int amt = Integer.parseInt(request.getParameter("amt"));
        User user = userMapper.getUserById(Integer.parseInt(request.getParameter("userid")));

        try {
            userMapper.updateUserBalance(user, amt);
            request.getSession().setAttribute("userList", userMapper.getUsers());
            request.getRequestDispatcher("admin.jsp").forward(request, response);
        } catch (DatabaseException e) {
            Logger.getLogger("web").log(Level.SEVERE, e.getMessage());
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
