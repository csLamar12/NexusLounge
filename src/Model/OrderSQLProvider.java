package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles SQL operations related to the Order entity, such as inserting, retrieving,
 * updating, and deleting orders in the database.
 */
public class OrderSQLProvider {

    // Connection to the database
    private final Connection connection;
    // Logger for logging information and errors
    private static final Logger logger = Logger.getLogger(OrderSQLProvider.class.getName());

    /**
     * Initializes the OrderSQLProvider with a database connection.
     *
     * @param connection the database connection
     */
    public OrderSQLProvider(Connection connection) {
        this.connection = connection;
    }

    /**
     * Inserts a new order into the Orders table.
     *
     * @param order the Order object containing the order details to be inserted
     */
    public void insertOrder(Order order) {
        String query = "INSERT INTO Orders (guestId, bartenderId, orderDate, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, order.getGuestId());
            stmt.setInt(2, order.getBartenderId());
            stmt.setDate(3, new java.sql.Date(order.getOrderDate().getTime()));
            stmt.setBoolean(4, order.getStatus());
            stmt.executeUpdate();
            logger.info("Order inserted successfully with guest ID: " + order.getGuestId());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to insert order", e);
        }
    }

    /**
     * Retrieves an order by its ID.
     *
     * @param id the ID of the order to be retrieved
     * @return the Order object corresponding to the specified ID, or null if not found
     */
    public Order getOrderById(int id) {
        String query = "SELECT * FROM Orders WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Order order = new Order(
                            rs.getInt("id"),
                            rs.getInt("guestId"),
                            rs.getInt("bartenderId"),
                            rs.getDate("orderDate"),
                            rs.getBoolean("status")
                    );
                    logger.info("Order retrieved with ID: " + id);
                    return order;
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to retrieve order with ID: " + id, e);
        }
        return null;
    }

    /**
     * Retrieves all orders from the Orders table.
     *
     * @return a list of Order objects representing all orders in the database
     */
    public List<Order> getAllOrders() {
        String query = "SELECT * FROM Orders";
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Order order = new Order(
                        rs.getInt("id"),
                        rs.getInt("guestId"),
                        rs.getInt("bartenderId"),
                        rs.getDate("orderDate"),
                        rs.getBoolean("status")
                );
                orders.add(order);
            }
            logger.info("All orders retrieved successfully");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to retrieve all orders", e);
        }
        return orders;
    }

    /**
     * Updates an existing order in the database.
     *
     * @param order the Order object containing the updated order information
     */
    public void updateOrder(Order order) {
        String query = "UPDATE Orders SET guestId = ?, bartenderId = ?, orderDate = ?, status = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, order.getGuestId());
            stmt.setInt(2, order.getBartenderId());
            stmt.setDate(3, new java.sql.Date(order.getOrderDate().getTime()));
            stmt.setBoolean(4, order.getStatus());
            stmt.setInt(5, order.getOrderId());
            stmt.executeUpdate();
            logger.info("Order updated successfully with ID: " + order.getOrderId());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to update order", e);
        }
    }
}
