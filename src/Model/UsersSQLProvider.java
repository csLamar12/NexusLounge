package Model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The UsersSQLProvider class provides SQL database operations for the Users entity,
 * including inserting, retrieving, updating and deleting users in the database.
 * This class also interacts with the Users table in the database.
 */
public class UsersSQLProvider extends SQLProvider implements IUsersSVC {

    //Logger for logging messages and errors
    private static final Logger LOGGER = LogManager.getLogger(UsersSQLProvider.class);

    private String query;

    /**
     * Initializes the UsersSQLProvider with a database connection.
     */
    public UsersSQLProvider(){
        super();
    }

    /**
     * Inserts a new user into the database.
     *
     * @param user the Users object to be inserted.
     */
    @Override
    public void insertUser(Users user) {
        query = "INSERT INTO Users (Name, Username, Password, Role) VALUES (?, ?, ?, ?)";
        try{
            ps = conn.prepareStatement(query);
            ps.setString(1, user.getName());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole());
            ps.executeUpdate();
            LOGGER.info("User inserted successfully: {}", user.getName());
        } catch (SQLException e) {
            LOGGER.error("Failed to insert a user: {}", user.getName());
        }
    }

    /**
     * Updates an existing user in the database.
     *
     * @param user the Users object containing the updated information.
     */
    @Override
    public void updateUser(Users user) {
        query = "UPDATE Users SET Name = ?, Username = ?, Password = ?, Role = ? WHERE Id = ?";
        try{
            ps = conn.prepareStatement(query);
            ps.setString(1, user.getName());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole());
            ps.executeUpdate();
            LOGGER.info("User inserted successfully: {}", user.getName());
        }
        catch(SQLException e){
            LOGGER.error("Failed to update user: {}", user.getName());
        }
    }

    /**
     * Deletes a user based on their ID.
     *
     * @param id the ID of the user to be deleted.
     */
    @Override
    public void deleteUser(int id) {
        query = "DELETE FROM Users WHERE Id = ?";
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            LOGGER.info("User deleted with ID: {}", id);
        } catch (SQLException e) {
            LOGGER.error("Failed to delete user with ID: {}", id, e);
        }
    }

    /**
     * Retrieves a user from the database based on the user ID.
     *
     * @param id the unique ID of the user to retrieve
     * @return the user object corresponding to the given ID, or null if no such user exists
     */
    @Override
    public Users getUserById(int id) {
        query = "SELECT * FROM Users WHERE id = ?";
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Users user = new Users(
                        rs.getInt("Id"),
                        rs.getString("Name"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Role")
                );
                LOGGER.info("User retrieved: {}", user.getName());
                return user;
            }
        } catch (SQLException e) {
            LOGGER.error("Failed to retrieve user with ID: {}", id, e);
        }
        return null;
    }

    /**
     * Retrieves all users from the database.
     *
     * @return a list of User objects representing all users in the database.
     */
    @Override
    public List<Users> getAllUsers() {
        query = "SELECT * FROM Users";
        List<Users> users = new ArrayList<>();
        try {
            stat = conn.createStatement();
            rs = stat.executeQuery(query);
            while (rs.next()) {
                Users user = new Users(
                        rs.getInt("Id"),
                        rs.getString("Name"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Role")
                );
                users.add(user);
            }
            LOGGER.info("All users were retrieved successfully");
        } catch (SQLException e) {
            LOGGER.error("Failed to retrieve all users", e);
        }
        return users;
    }
}
