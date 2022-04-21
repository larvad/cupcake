package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.dtos.CupcakeDTO;
import dat.startcode.model.dtos.LineDTO;
import dat.startcode.model.dtos.OrderDTO;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.CupcakeMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
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

        User user = (User) request.getSession().getAttribute("user");

        HttpSession session = request.getSession();
        if (session != null) {

            List<LineDTO> cupcakeLines = new ArrayList<>();

            try {
                cupcakeLines = cupcakeMapper.getOrderLines(user);
            } catch (DatabaseException e) {
                e.printStackTrace();
            }

            List<OrderDTO> cupcakeOrders = new ArrayList<>();
            List<CupcakeDTO> cupcakeDTOs = new ArrayList<>();
            LineDTO tempLineDTO = null;
            int cupcakeeId = 1;

            for (LineDTO cupcakeLine : cupcakeLines) {

                if (cupcakeDTOs.size() != 0) {

                    if (cupcakeLine.getOrderId() != tempLineDTO.getOrderId()) {

                        cupcakeOrders.add(new OrderDTO(tempLineDTO.getOrderId(), tempLineDTO.getTotal_price(), cupcakeDTOs));
                        cupcakeDTOs = new ArrayList<>();
                        cupcakeeId = 1;
                    }

                }

                try {
                    cupcakeDTOs.add(new CupcakeDTO(cupcakeMapper.findCupcakeBot(cupcakeLine.getBot_id()),
                            cupcakeMapper.findCupcakeTop(cupcakeLine.getTop_id()), cupcakeLine.getQuantity(), cupcakeeId++ ));
                } catch (DatabaseException e) {
                    e.printStackTrace();
                }


                tempLineDTO = cupcakeLine;
                if (cupcakeLines.indexOf(cupcakeLine)+1 == cupcakeLines.size()) {
                    cupcakeOrders.add(new OrderDTO(cupcakeLine.getOrderId(), cupcakeLine.getTotal_price(), cupcakeDTOs));
                }

            }


            session.setAttribute("cupcakeOrders", cupcakeOrders);
            request.getRequestDispatcher("myOrders.jsp").forward(request, response);


        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {




    }
}
