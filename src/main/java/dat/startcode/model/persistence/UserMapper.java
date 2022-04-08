package dat.startcode.model.persistence;

import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserMapper implements IUserMapper
{
    ConnectionPool connectionPool;

    public UserMapper(ConnectionPool connectionPool)
    {
        this.connectionPool = connectionPool;
    }

    @Override
    public User login(String username, String password) throws DatabaseException
    {
        Logger.getLogger("web").log(Level.INFO, "");

        User user = null;

        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setString(1, username);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next())
                {
                    int id = rs.getInt("user_id");
                    boolean isAdmin = rs.getBoolean("isAdmin");
                    String role = isAdmin ? "admin" : "user";  // yep, "if else" in one line (look up "tertiary operators")
                    String email = rs.getString("email");
                    int balance = rs.getInt("balance");
                    user = new User(username, password, email, role, balance);
                } else
                {
                    throw new DatabaseException("Wrong username or password");
                }
            }
        } catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Error logging in. Something went wrong with the database");
        }
        return user;
    }

    @Override
    public User createUser(String username, String password, String email, boolean isAdmin) throws DatabaseException
    {
        Logger.getLogger("web").log(Level.INFO, "");
        User user;
        String sql = "insert into user (username, password, email, isAdmin) values (?,?,?,?)";
        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setString(1, username);
                ps.setString(2, password);
                ps.setString(3, email);
                ps.setBoolean(4, isAdmin);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1)
                {
                    String role = isAdmin ? "admin" : "user";
                    user = new User(username, password, email, role, 0);
                } else
                {
                    throw new DatabaseException("The user with username = " + username + " could not be inserted into the database");
                }
            }
        }
        catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Could not insert username into database");
        }
        return user;
    }

    @Override
    public int updateUserBalance(User user, int amountToAdd) throws DatabaseException {
        int result = 0;
        String sql = "UPDATE user " +
                     "SET balance = balance + ? " +
                     "WHERE email = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, amountToAdd);
                ps.setString(2, user.getEmail());
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1) {
                    String sql2 = "SELECT balance FROM user WHERE email = ?";
                    try (PreparedStatement ps2 = connection.prepareStatement(sql2)) {
                        ps2.setString(1, user.getEmail());
                        ResultSet rs = ps2.executeQuery();
                        if (rs.next()) {
                            result = rs.getInt(1);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("User doesn't exist");
        }
        return result;
    }
}
