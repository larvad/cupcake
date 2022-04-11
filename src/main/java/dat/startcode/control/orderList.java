package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.dtos.BotDTO;
import dat.startcode.model.dtos.CupcakeDTO;
import dat.startcode.model.dtos.TopDTO;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.CupcakeMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@WebServlet(name = "orderList", value = "/orderList")
public class orderList extends HttpServlet {

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

        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int cupcakeTop = Integer.parseInt(request.getParameter("cupcakeTop"));
        int cupcakeBot = Integer.parseInt(request.getParameter("cupcakeBot"));

        try
        {
            BotDTO botDTO = cupcakeMapper.findCupcakeBot(cupcakeBot);
            TopDTO topDTO = cupcakeMapper.findCupcakeTop(cupcakeTop);

            HttpSession session = request.getSession();
            if (session != null) {
                List<CupcakeDTO> cartCupcakes = (List<CupcakeDTO>) session.getAttribute("cartCupcakes");
                int cupcakeId = 0;
                if (cartCupcakes.size()>=1){
                    cupcakeId = cartCupcakes.size()+1;
                }
                if (cartCupcakes.size()<1) {
                   cupcakeId = 1;
               }

                int total_price = 0;
                cartCupcakes.add(new CupcakeDTO(botDTO, topDTO, quantity, cupcakeId));
                for (CupcakeDTO cartCupcake : cartCupcakes) {

                    total_price = total_price + cartCupcake.getTotalPrice();
                }
                session.setAttribute("totalPrice", total_price);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }

        }
        catch (DatabaseException e)
        {
            Logger.getLogger("web").log(Level.SEVERE, e.getMessage());
            request.setAttribute("fejlbesked", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }




    }
}
