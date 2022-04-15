package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.CupcakeMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "orderReturn", value = "/orderReturn")
public class orderReturn extends HttpServlet {
    private ConnectionPool connectionPool;

    public void init() throws ServletException
    {
        this.connectionPool = ApplicationStart.getConnectionPool();

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        if (session != null) {



            request.getRequestDispatcher("index.jsp").forward(request, response);
        }


        }
}
