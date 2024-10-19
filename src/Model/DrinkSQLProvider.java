package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The DrinkSQLProvider class provides SQL database operations for the Drink entity,
 * including inserting, retrieving, updating, and deleting drinks in the database.
 * It interacts with the Drinks table in the database.
 */
public class DrinkSQLProvider {

    // Connection to the database
    private final Connection connection;

    // Logger for logging messages and errors
    private static final Logger logger = Logger.getLogger(DrinkSQLProvider.class.getName());

    /**
     * Initializes the DrinkSQLProvider with a database connection.
     *
     * @param connection the database connection to be used for SQL operations.
     */
    public DrinkSQLProvider(Connection connection) {
        this.connection = connection;
    }

    /**
     * Inserts a new drink into the database.
     *
     * @param drink the Drink object containing the information to be inserted.
     */
    public void insertDrink(Drink drink) {
        String query = "INSERT INTO Drinks (name, isAlcoholic) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, drink.getName());
            stmt.setBoolean(2, drink.isAlcoholic());
            stmt.executeUpdate();
            logger.info("Drink inserted successfully: " + drink.getName());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to insert drink: " + drink.getName(), e);
        }
    }

    /**
     * Retrieves a drink by its ID.
     *
     * @param id the ID of the drink to be retrieved.
     * @return the Drink object corresponding to the specified ID, or null if not found.
     */
    public Drink getDrinkById(int id) {
        String query = "SELECT * FROM Drinks WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Drink drink = new Drink(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getBoolean("isAlcoholic")
                    );
                    logger.info("Drink retrieved: " + drink.getName());
                    return drink;
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to retrieve drink with ID: " + id, e);
        }
        return null;
    }

    /**
     * Retrieves all drinks from the database.
     *
     * @return a list of Drink objects representing all drinks in the database.
     */
    public List<Drink> getAllDrinks() {
        String query = "SELECT * FROM Drinks";
        List<Drink> drinks = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Drink drink = new Drink(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getBoolean("isAlcoholic")
                );
                drinks.add(drink);
            }
            logger.info("All drinks retrieved successfully");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to retrieve all drinks", e);
        }
        return drinks;
    }

    /**
     * Updates an existing drink in the database.
     *
     * @param drink the Drink object containing the updated information.
     */
    public void updateDrink(Drink drink) {
        String query = "UPDATE Drinks SET name = ?, isAlcoholic = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, drink.getName());
            stmt.setBoolean(2, drink.isAlcoholic());
            stmt.setInt(3, drink.getId());
            stmt.executeUpdate();
            logger.info("Drink updated successfully: " + drink.getName());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to update drink: " + drink.getName(), e);
        }
    }

    /**
     * Deletes a drink by its ID.
     *
     * @param id the ID of the drink to be deleted.
     */
    public void deleteDrink(int id) {
        String query = "DELETE FROM Drinks WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            logger.info("Drink deleted with ID: " + id);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to delete drink with ID: " + id, e);
        }
    }
}
