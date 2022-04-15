package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.dtos.CupcakeDTO;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.OrderMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "order", value = "/order")
public class order extends HttpServlet {

    private ConnectionPool connectionPool;
    private OrderMapper orderMapper;

    @Override
    public void init() throws ServletException {
        this.connectionPool = ApplicationStart.getConnectionPool();
        orderMapper = new OrderMapper(connectionPool);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        if (session != null) {
            List<CupcakeDTO> cartCupcakes = (List<CupcakeDTO>) session.getAttribute("cartCupcakes");

            orderMapper.setLines(cartCupcakes, 1);

            request.getRequestDispatcher("checkoutComplete.jsp").forward(request, response);
        }
    }
}
