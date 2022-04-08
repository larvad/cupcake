package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.dtos.CupcakeDTO;
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

    @Override
    public void init() throws ServletException {
        this.connectionPool = ApplicationStart.getConnectionPool();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        int refresh = Integer.parseInt(request.getParameter("refresh"));

        HttpSession session = request.getSession();
        if (session != null) {

            if (refresh == 0) {
                //Hvis der trykkes remove all knap
                List<CupcakeDTO> cartCupcakes = (List<CupcakeDTO>) session.getAttribute("cartCupcakes");
                cartCupcakes = new ArrayList<>();
                session.setAttribute("cartCupcakes", cartCupcakes);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }


            List<CupcakeDTO> cartCupcakes = (List<CupcakeDTO>) session.getAttribute("cartCupcakes");

            if (refresh > 0 && refresh < 100) {
                CupcakeDTO tempCupcakeDTO = null;
                for (CupcakeDTO cartCupcake : cartCupcakes) {
                    if (cartCupcake.getCupcakeID() == refresh) {
                        tempCupcakeDTO = cartCupcake;
                    }
                }
                int quantityRefresh = Integer.parseInt(request.getParameter("quantityRefresh"));
                tempCupcakeDTO.setQuantity(quantityRefresh);

            }
            else if (refresh==101) {
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
                        cartCupcake.setCupcakeID(i+1);
                    }
                }
            }
        session.setAttribute("cartCupcakes", cartCupcakes);
        request.getRequestDispatcher("index.jsp").forward(request, response);
        }

    }
}
