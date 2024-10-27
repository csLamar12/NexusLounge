package Model;

import java.util.List;


/**
 * This interface defines the service layer for performing CRUD operations on User objects.
 * It provides methods for inserting, updating, deleting, and retrieving users.
 */
public interface IUsersSVC {
    public void insertUser(Users user);
    public void updateUser(Users user);
    public void deleteUser(int id);
    public Users getUserById(int id);
    public List<Users> getAllUsers();
}
