package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.dtos.CupcakeDTO;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.CupcakeMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CartRefresh", value = "/CartRefresh")
public class CartRefresh extends HttpServlet {
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


        int refresh = Integer.parseInt(request.getParameter("refresh"));
        int total_price = 0;

        try {
            HttpSession session = request.getSession();
            if (session != null) {

                List<CupcakeDTO> cartCupcakes = (List<CupcakeDTO>) session.getAttribute("cartCupcakes");


                //Hvis Update

                if (refresh > 0 && refresh < 100) {
                    CupcakeDTO tempCupcakeDTO = null;
                    for (CupcakeDTO cartCupcake : cartCupcakes) {
                        if (cartCupcake.getCupcakeID() == refresh) {
                            tempCupcakeDTO = cartCupcake;
                        }
                    }
                    int quantityRefresh = Integer.parseInt(request.getParameter("quantityRefresh"));
                    tempCupcakeDTO.setQuantity(quantityRefresh);
                    for (CupcakeDTO cartCupcake : cartCupcakes) {
                        total_price = total_price + cartCupcake.getTotalPrice();
                    }
                    session.setAttribute("totalPrice", total_price);
                    session.setAttribute("cartCupcakes", cartCupcakes);
                    request.getRequestDispatcher("index.jsp").forward(request, response);

                }

                // Hvis enkelt cupcake fjernes

                if (refresh == 101) {
                    int cupcakeToRemove = Integer.parseInt(request.getParameter("cupcakeToRemove"));
                    CupcakeDTO tempCupcakeDTO = null;
                    for (CupcakeDTO cartCupcake : cartCupcakes) {
                        if (cartCupcake.getCupcakeID() == cupcakeToRemove) {
                            tempCupcakeDTO = cartCupcake;
                        }

                    }
                    cartCupcakes.remove(tempCupcakeDTO);

                    // Setting new id of cupcakes
                    if (cartCupcakes.size() != 0) {
                        for (CupcakeDTO cartCupcake : cartCupcakes) {
                            int i = cartCupcakes.indexOf(cartCupcake);
                            cartCupcake.setCupcakeID(i + 1);
                        }
                        for (CupcakeDTO cartCupcake : cartCupcakes) {
                            total_price = total_price + cartCupcake.getTotalPrice();
                        }
                        session.setAttribute("totalPrice", total_price);
                        session.setAttribute("cartCupcakes", cartCupcakes);
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }
                }

                //Hvis remove all

                if (refresh == 102) {
                    //Hvis der trykkes remove all knap
                    cartCupcakes = new ArrayList<>();
                    session.setAttribute("cartCupcakes", cartCupcakes);
                    for (CupcakeDTO cartCupcake : cartCupcakes) {
                        total_price = total_price + cartCupcake.getTotalPrice();
                    }
                    session.setAttribute("totalPrice", total_price);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }


                // Send to checkout page
                if (refresh == 103) {

                    int orderId = cupcakeMapper.getOrderId();
                    session.setAttribute("orderId", orderId);
                    for (CupcakeDTO cartCupcake : cartCupcakes) {
                        total_price = total_price + cartCupcake.getTotalPrice();
                    }
                    session.setAttribute("totalPrice", total_price);
                    request.getRequestDispatcher("checkout.jsp").forward(request, response);
                }


            }

        } catch (IOException | DatabaseException e) {
            e.printStackTrace();
        }
    }
}
