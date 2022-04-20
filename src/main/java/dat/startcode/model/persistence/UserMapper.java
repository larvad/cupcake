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
    public User login(String email, String password) throws DatabaseException
    {
        Logger.getLogger("web").log(Level.INFO, "");

        User user = null;

        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setString(1, email);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next())
                {
                    int id = rs.getInt("user_id");
                    boolean isAdmin = rs.getBoolean("isAdmin");
                    String role = isAdmin ? "admin" : "user";  // yep, "if else" in one line (look up "tertiary operators")
                    String username = rs.getString("username");

                    int balance = rs.getInt("balance");
                    user = new User(username, password, email, role, balance);
                    user.setId(id); // this is where the magic happens

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
        user.setId(this); // this is where the magic happens
        return user;
    }

    @Override
    public User updateUser(String currentEmail, String password, String email, String newUsername, boolean isAdmin) throws DatabaseException
    {
        Logger.getLogger("web").log(Level.INFO, "");
        User user;

        String sql = "UPDATE `cupcake`.`user` SET `username` = ?, `password` = ?, `email` = ?, `isAdmin` = ? WHERE (`email` = ?);";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setString(1, newUsername);
                ps.setString(2, password);
                ps.setString(3, email);
                ps.setBoolean(4, isAdmin);
                ps.setString(5, currentEmail);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1)
                {
                    String role = isAdmin ? "admin" : "user";
                    user = new User(currentEmail, password, email, role);
                    user.setId(this);
                } else
                {
                    throw new DatabaseException("The user with email = " + currentEmail + " could not be inserted into the database");
                }
            }
        }
        catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Could not insert username into database");
        }
        return user;
    }

    public int updateUserBalance(User user, int amountToAdd) throws DatabaseException {
        int result = 0;
        String sql = "UPDATE user " +
                     "SET balance = balance + ? " +
                     "WHERE user_id = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, amountToAdd);
                ps.setInt(2, user.getId());
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1) {
                    String sql2 = "SELECT balance FROM user WHERE user_id = ?";
                    try (PreparedStatement ps2 = connection.prepareStatement(sql2)) {
                        ps2.setInt(1, user.getId());
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

    @Override
    public int getUserId(User user) { // is only called on user object creation
        int userId = 0;               // so the user actually gets their id :)
        String sql = "SELECT user_id FROM user " +
                     "WHERE email = ?"; // uses email to get user_id, as it should be unique

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, user.getEmail());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    userId = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
    }
}
