package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.dtos.LineDTO;
import dat.startcode.model.entities.User;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.CupcakeMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "myOrders", value = "/myOrders")
public class myOrders extends HttpServlet {
    private ConnectionPool connectionPool;
    private CupcakeMapper cupcakeMapper;


    @Override
    public void init() throws ServletException {
        this.connectionPool = ApplicationStart.getConnectionPool();
        cupcakeMapper = new CupcakeMapper(connectionPool);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        User user = (User) request.getSession().getAttribute("user");

        HttpSession session = request.getSession();
        if (session != null) {



        }

    }
}
