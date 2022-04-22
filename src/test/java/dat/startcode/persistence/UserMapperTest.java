package dat.startcode.persistence;

import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.UserMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {
    private final static String USER = "root";
    private final static String PASSWORD = "root";
    private final static String URL = "jdbc:mysql://localhost:3306/cupcake_test?serverTimezone=CET&allowPublicKeyRetrieval=true&useSSL=false";

    private static ConnectionPool connectionPool;
    private static UserMapper userMapper;

    @BeforeAll
    public static void setUpClass() {
        connectionPool = new ConnectionPool(USER, PASSWORD, URL);
        userMapper = new UserMapper(connectionPool);
    }

    @BeforeEach
    void setUp() {
        try (Connection testConnection = connectionPool.getConnection()) {
            try (Statement stmt = testConnection.createStatement()) {
                // Remove all rows from all tables
                stmt.execute("delete from user");
                // Indsæt et par brugere
                stmt.execute("insert into user (username, password, email, isAdmin) " +
                        "values ('user','1234','u@u.dk',false),('admin','1234','a@a.dk',true), ('ben','1234','b@b.dk',false)");
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            fail("Database connection failed");
        }
    }

    @Test
    void testConnection() throws SQLException {
        Connection connection = connectionPool.getConnection();
        assertNotNull(connection);
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    void login() throws DatabaseException {

        User actualUser = userMapper.login("u@u.dk", "1234");
        User expectedUser = new User("user", "1234", "u@u.dk", "user", 0);
        expectedUser.setId(actualUser.getId());
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void invalidPasswordLogin() {
        assertThrows(DatabaseException.class, () -> userMapper.login("user", "123"));
    }

    @Test
    void invalidUserNameLogin() {
        assertThrows(DatabaseException.class, () -> userMapper.login("bob", "1234"));
    }

    @Test
    void createUser() throws DatabaseException {
        User newUser = userMapper.createUser("jill", "1234", "j@j.dk", false);
        User logInUser = userMapper.login("j@j.dk", "1234");
        User expectedUser = new User("jill", "1234", "j@j.dk", "user", 0);
        expectedUser.setId(newUser.getId());

        assertEquals(expectedUser, newUser);
        assertEquals(expectedUser, logInUser);
    }

    @Test
    void addUserBalance() throws DatabaseException {
        User user = userMapper.login("a@a.dk", "1234");
        int actualBalance = userMapper.updateUserBalance(user, 10);
        int expectedBalance = 10;
        assertEquals(expectedBalance, actualBalance);
    }

    @Test
    void subUserBalance() throws DatabaseException {
        User user = userMapper.login("a@a.dk", "1234");
        user.setBalance(userMapper.updateUserBalance(user, -10));
        int actualBalance = user.getBalance();
        int expectedBalance = -10;
        assertEquals(expectedBalance, actualBalance);
    }

    @Test
    void getUsers() throws DatabaseException {
        List<User> actualList = userMapper.getUsers();
        List<User> expectedList = new ArrayList<>();
        User user1 = new User("user", "1234", "u@u.dk", "user", 0);
        user1.setId(userMapper.login("u@u.dk", "1234").getId());
        User user2 = new User("admin", "1234", "a@a.dk", "admin", 0);
        user2.setId(userMapper.login("a@a.dk", "1234").getId());
        User user3 = new User("ben", "1234", "b@b.dk", "user", 0);
        user3.setId(userMapper.login("b@b.dk", "1234").getId());
        expectedList.add(user1);
        expectedList.add(user2);
        expectedList.add(user3);

        assertEquals(expectedList, actualList);
    }

    @Test
    void getUserById() throws DatabaseException {
        User actualUser = userMapper.getUserById(
                userMapper.login("u@u.dk", "1234").getId());
        User expectedUser = new User("user", "1234", "u@u.dk", "user", 0);
        expectedUser.setId(actualUser.getId());

        assertEquals(expectedUser, actualUser);
    }

    @Test
    void updateUser() throws DatabaseException {
        int userId = userMapper.login("u@u.dk", "1234").getId();
        User actualUser = userMapper.updateUser(userId, "12345", "u5@u.dk", "userTest", true);
        User expectedUser = new User("userTest", "12345", "u5@u.dk", "admin", 0);
        expectedUser.setId(actualUser.getId());

        assertEquals(expectedUser, actualUser);
    }
}
