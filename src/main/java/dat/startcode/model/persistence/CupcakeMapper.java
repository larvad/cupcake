package dat.startcode.model.persistence;

import dat.startcode.model.dtos.BotDTO;
import dat.startcode.model.dtos.LineDTO;
import dat.startcode.model.dtos.TopDTO;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CupcakeMapper implements ICupcakeMapper {


    ConnectionPool connectionPool;

    public CupcakeMapper(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<TopDTO> getCupcakesTop() throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        List<TopDTO> cupcakeTopList = new ArrayList<>();

        String sql = "SELECT t.top_id ID, t.flavor Topping, t.price topPrice FROM " +
                "top t";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String topFlavor = rs.getString("Topping");
                    int topPrice = rs.getInt("topPrice");
                    TopDTO topDTO = new TopDTO(id, topFlavor, topPrice);
                    cupcakeTopList.add(topDTO);

                }

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cupcakeTopList;
    }

    @Override
    public List<BotDTO> getCupcakesBot() throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        List<BotDTO> cupcakeBotList = new ArrayList<>();

        String sql = "SELECT b.bottom_id id, b.flavor Bottom, b.price botPrice FROM " +
                "bottom b";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String botFlavor = rs.getString("Bottom");
                    int botPrice = rs.getInt("botPrice");
                    BotDTO botDTO = new BotDTO(id, botFlavor, botPrice);
                    cupcakeBotList.add(botDTO);

                }

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cupcakeBotList;
    }

    @Override
    public BotDTO findCupcakeBot(int id) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        BotDTO botDTO = null;

        String sql = "SELECT b.bottom_id id, b.flavor Bottom, b.price botPrice FROM " +
                "bottom b WHERE bottom_id = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1,id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String botFlavor = rs.getString("Bottom");
                    int botPrice = rs.getInt("botPrice");
                    botDTO = new BotDTO(id, botFlavor, botPrice);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return botDTO;
    }


    @Override
    public int createOrder(User user, int totalPrice) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        int orderId = 0;

        String sql = "INSERT INTO `order`(user_id, price_total) VALUES (?,?)";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, user.getId());
                ps.setInt(2, totalPrice);
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    orderId = rs.getInt(1);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return orderId;
    }

    @Override
    public void setCupcakeLines(int orderID, int quantity, int top_id, int bot_id, int line_price) throws DatabaseException {

        Logger.getLogger("web").log(Level.INFO, "");
        boolean result = false;


        String sql = "INSERT INTO order_line (order_id, quantity, top_id, bottom_id, line_price) values (? , ? , ?, ?, ?)";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, orderID);
                ps.setInt(2, quantity);
                ps.setInt(3, top_id);
                ps.setInt(4, bot_id);
                ps.setInt(5, line_price);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1) {
                    result = true;
                }
                else {
                    throw new DatabaseException("kunne ikke oprettes i databasen");
                }

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public TopDTO findCupcakeTop(int id) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        TopDTO topDTO = null;

        String sql = "SELECT t.top_id ID, t.flavor Topping, t.price topPrice FROM " +
                "top t WHERE top_id = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String topFlavor = rs.getString("Topping");
                    int topPrice = rs.getInt("topPrice");
                    topDTO = new TopDTO(id, topFlavor, topPrice);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return topDTO;
    }

    public List<LineDTO> getOrderLines (User user) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        int userId = user.getId();
        List<LineDTO> lineDTOS = new ArrayList<>();
        String sql = "SELECT * FROM cupcake.order INNER JOIN order_line USING (order_id) WHERE user_id = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {

                    int orderId = rs.getInt("order_id");
                    int totalPrice = rs.getInt("price_total");
                    int quantity = rs.getInt("quantity");
                    int topId = rs.getInt("top_id");
                    int botId = rs.getInt("bottom_id");

                    lineDTOS.add(new LineDTO(orderId, totalPrice, quantity, topId, botId));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lineDTOS;
    }
}