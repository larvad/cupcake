package dat.startcode.model.persistence;

import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;

public interface IUserMapper
{
    public User login(String email, String kodeord) throws DatabaseException;
    public User createUser(String username, String password, String email, boolean isAdmin) throws DatabaseException;
    public User updateUser(int userId, String password, String email, String newUsername, boolean isAdmin) throws DatabaseException;
    public int updateUserBalance(User user, int amountToAdd) throws DatabaseException;
    public int getUserId(User user) throws DatabaseException;

    Object getUsers();

    User getUserById(int id);
}
