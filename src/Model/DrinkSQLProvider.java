//Author: Danielle Johns
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

// The DrinkSQLProvider class provides SQL database operations for the Drink entity.
public class DrinkSQLProvider {

    // Connection to the database
    private final Connection connection;

    // Logger for logging messages and errors
    private static final Logger logger = Logger.getLogger(DrinkSQLProvider.class.getName());

    // Constructor that takes a Connection object to interact with the database
    public DrinkSQLProvider(Connection connection) {
        this.connection = connection;
    }

    // Method to insert a new drink into the database
    public void insertDrink(Drink drink) {
        // SQL query to insert a new drink record into the Drinks table
        String query = "INSERT INTO Drinks (name, isAlcoholic) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            // Set the parameters for the name and isAlcoholic fields
            stmt.setString(1, drink.getName());
            stmt.setBoolean(2, drink.isAlcoholic());

            // Execute the query to insert the new drink
            stmt.executeUpdate();

            // Log a success message
            logger.info("Drink inserted successfully: " + drink.getName());
        } catch (SQLException e) {
            // Log an error message in case of failure
            logger.log(Level.SEVERE, "Failed to insert drink: " + drink.getName(), e);
        }
    }

    // Method to retrieve a drink by its ID
    public Drink getDrinkById(int id) {
        // SQL query to select a drink by its ID
        String query = "SELECT * FROM Drinks WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            // Set the parameter for the drink ID
            stmt.setInt(1, id);

            // Execute the query and get the result set
            try (ResultSet rs = stmt.executeQuery()) {
                // If a drink is found, create a Drink object with the retrieved data
                if (rs.next()) {
                    Drink drink = new Drink(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getBoolean("isAlcoholic")
                    );

                    // Log a success message
                    logger.info("Drink retrieved: " + drink.getName());
                    return drink;  // Return the Drink object
                }
            }
        } catch (SQLException e) {
            // Log an error message in case of failure
            logger.log(Level.SEVERE, "Failed to retrieve drink with ID: " + id, e);
        }
        return null;  // Return null if no drink is found or if an error occurs
    }

    // Method to retrieve all drinks from the database
    public List<Drink> getAllDrinks() {
        // SQL query to select all drinks
        String query = "SELECT * FROM Drinks";
        List<Drink> drinks = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            // Iterate through the result set and create Drink objects for each row
            while (rs.next()) {
                Drink drink = new Drink(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getBoolean("isAlcoholic")
                );
                drinks.add(drink);  // Add each Drink object to the list
            }

            // Log a success message
            logger.info("All drinks retrieved successfully");
        } catch (SQLException e) {
            // Log an error message in case of failure
            logger.log(Level.SEVERE, "Failed to retrieve all drinks", e);
        }
        return drinks;  // Return the list of drinks
    }

    // Method to update an existing drink in the database
    public void updateDrink(Drink drink) {
        // SQL query to update a drink's information
        String query = "UPDATE Drinks SET name = ?, isAlcoholic = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            // Set the parameters for the name, isAlcoholic fields, and the drink ID
            stmt.setString(1, drink.getName());
            stmt.setBoolean(2, drink.isAlcoholic());
            stmt.setInt(3, drink.getId());

            // Execute the query to update the drink
            stmt.executeUpdate();

            // Log a success message
            logger.info("Drink updated successfully: " + drink.getName());
        } catch (SQLException e) {
            // Log an error message in case of failure
            logger.log(Level.SEVERE, "Failed to update drink: " + drink.getName(), e);
        }
    }

    // Method to delete a drink by its ID
    public void deleteDrink(int id) {
        // SQL query to delete a drink by its ID
        String query = "DELETE FROM Drinks WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            // Set the parameter for the drink ID
            stmt.setInt(1, id);

            // Execute the query to delete the drink
            stmt.executeUpdate();

            // Log a success message
            logger.info("Drink deleted with ID: " + id);
        } catch (SQLException e) {
            // Log an error message in case of failure
            logger.log(Level.SEVERE, "Failed to delete drink with ID: " + id, e);
        }
    }
}
