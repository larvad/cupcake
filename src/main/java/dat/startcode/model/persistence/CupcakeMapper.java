package dat.startcode.model.persistence;

import dat.startcode.model.dtos.BotDTO;
import dat.startcode.model.dtos.CupcakeDTO;
import dat.startcode.model.dtos.TopDTO;
import dat.startcode.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

        String sql = "SELECT t.flavor Topping, t.price topPrice FROM " +
                "top t";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {

                    String topFlavor = rs.getString("Topping");
                    int topPrice = rs.getInt("topPrice");
                    TopDTO topDTO = new TopDTO(topFlavor, topPrice);
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

        String sql = "SELECT b.flavor Bottom, b.price botPrice FROM " +
                "bottom b";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {

                    String botFlavor = rs.getString("Bottom");
                    int botPrice = rs.getInt("botPrice");
                    BotDTO botDTO = new BotDTO(botFlavor, botPrice);
                    cupcakeBotList.add(botDTO);

                }

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cupcakeBotList;
    }
}