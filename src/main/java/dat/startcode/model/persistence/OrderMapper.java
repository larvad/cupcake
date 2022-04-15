package dat.startcode.model.persistence;

import dat.startcode.model.dtos.CupcakeDTO;
import dat.startcode.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderMapper implements IOrderMapper {

    ConnectionPool connectionPool;

    public OrderMapper(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public void setLines(List<CupcakeDTO> list, int orderId) {

        Logger.getLogger("web").log(Level.INFO, "");

        String sql = "INSERT INTO order_line (order_id, quantity, top_id, bottom_id) values (? , ? , ?, ?)";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                for (CupcakeDTO cupcakeDTO : list) {

                    ps.setInt(1, 1);
                    ps.setInt(2, cupcakeDTO.getQuantity());
                    ps.setInt(3, cupcakeDTO.getTopDTO().getId());
                    ps.setInt(4, cupcakeDTO.getBotDTO().getId());
                    ps.executeUpdate();
                }


            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }
}
