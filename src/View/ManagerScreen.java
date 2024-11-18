package View;

import Model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import java.util.List;
import java.util.ArrayList;

public class ManagerScreen extends JFrame {

    private static final Logger LOGGER = LogManager.getLogger(ManagerScreen.class);

    private final JPanel orderListContentPanel;
    private final JPanel servedOrdersContentPanel;
    private final JLabel noOrdersLabel;
    private final JLabel noServedOrdersLabel;
    private Client client;
    private Object[] OD;
    private JButton refreshButton;

    public ManagerScreen(Client client) {
        this.client = client;


        // Set up the frame
        setTitle("Manager Screen");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.decode("#d9b7b7"));  // Set background color

        // Menu Bar with "Drinks", "Users", and "Reports" menus
        JMenuBar menuBar = new JMenuBar();

        // Drinks menu with items
        JMenu drinksMenu = new JMenu("Drinks");
        JMenuItem addDrink = new JMenuItem("Add Drink");
        JMenuItem removeDrink = new JMenuItem("Remove Drink");
        JMenuItem editDrink = new JMenuItem("Edit Drink");
        drinksMenu.add(addDrink);
        drinksMenu.add(removeDrink);
        drinksMenu.add(editDrink);

        // Users menu with items
        JMenu usersMenu = new JMenu("Users");
        JMenuItem createUser = new JMenuItem("Create User");
        JMenuItem removeUser = new JMenuItem("Remove User");
        JMenuItem editUser = new JMenuItem("Edit User");
        usersMenu.add(createUser);
        usersMenu.add(removeUser);
        usersMenu.add(editUser);

        // Reports menu with items
        JMenu reportsMenu = new JMenu("Reports");
        JMenuItem generateReport = new JMenuItem("Generate Report");
        JMenu exportReport = new JMenu("Export Report");
        reportsMenu.add(generateReport);
        reportsMenu.add(exportReport);

        JMenuItem pdfReport = new JMenuItem("PDF Report");
        JMenuItem htmlReport = new JMenuItem("HTML Report");
        
        exportReport.add(pdfReport);
        exportReport.add(htmlReport);
        
        // Add menus to the menu bar
        menuBar.add(drinksMenu);
        menuBar.add(usersMenu);
        menuBar.add(reportsMenu);
        setJMenuBar(menuBar);

        // Center Panel for Orders and Served Orders using GridBagLayout for more control
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();

        // Left Container Panel for Order List
        JPanel orderListContainer = new JPanel(new BorderLayout());
        orderListContainer.setBackground(Color.WHITE);

        // Title for Order List with centered alignment
        JLabel orderListTitle = new JLabel("Order List", SwingConstants.CENTER);
        orderListTitle.setFont(new Font("Arial", Font.BOLD, 20));
        orderListTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        orderListContainer.add(orderListTitle, BorderLayout.NORTH);

        // Order List Content Panel (Initially Empty)
        orderListContentPanel = new JPanel();
        orderListContentPanel.setLayout(new BoxLayout(orderListContentPanel, BoxLayout.Y_AXIS));
        orderListContentPanel.setBackground(Color.WHITE);
        noOrdersLabel = new JLabel("No orders available", SwingConstants.CENTER);
        noOrdersLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        noOrdersLabel.setForeground(Color.GRAY);
        orderListContentPanel.add(noOrdersLabel);

        // Wrap orderListContentPanel in a JScrollPane
        JScrollPane orderListScrollPane = new JScrollPane(orderListContentPanel);
        orderListScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        orderListContainer.add(orderListScrollPane, BorderLayout.CENTER);

        // Right Container Panel for Served Orders
        JPanel servedOrdersContainer = new JPanel(new BorderLayout());
        servedOrdersContainer.setBackground(Color.WHITE);

        // Title for Served Orders with centered alignment
        JLabel servedOrdersTitle = new JLabel("Served Orders", SwingConstants.CENTER);
        servedOrdersTitle.setFont(new Font("Arial", Font.BOLD, 20));
        servedOrdersTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        servedOrdersContainer.add(servedOrdersTitle, BorderLayout.NORTH);

        // Served Orders Content Panel (Initially Empty)
        servedOrdersContentPanel = new JPanel();
        servedOrdersContentPanel.setLayout(new BoxLayout(servedOrdersContentPanel, BoxLayout.Y_AXIS));
        servedOrdersContentPanel.setBackground(Color.WHITE);
        noServedOrdersLabel = new JLabel("No served orders available", SwingConstants.CENTER);
        noServedOrdersLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        noServedOrdersLabel.setForeground(Color.GRAY);
        servedOrdersContentPanel.add(noServedOrdersLabel);

        // Wrap servedOrdersContentPanel in a JScrollPane
        JScrollPane servedOrdersScrollPane = new JScrollPane(servedOrdersContentPanel);
        servedOrdersScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        servedOrdersContainer.add(servedOrdersScrollPane, BorderLayout.CENTER);

        // GridBagLayout to control the sizes of the panels
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0; // Column for Order List
        gbc.gridy = 0;
        gbc.weightx = 0.65; // 70% width for Order List
        gbc.weighty = 1.0; // Fill the height
        gbc.insets = new Insets(0, 20, 20, 10);
        centerPanel.add(orderListContainer, gbc);

        gbc.gridx = 1; // Column for Served Orders
        gbc.weightx = 0.35; // 30% width for Served Orders
        gbc.insets = new Insets(0, 10, 20, 20);
        centerPanel.add(servedOrdersContainer, gbc);
        // Create the Refresh button
        refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    orderListContentPanel.removeAll();
                    orderListContentPanel.add(refreshButton);
                    for (Order order: client.getPendingOrders()){
                        addOrder(order);
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    LOGGER.error(ex);
                }
            }
        });
        orderListContentPanel.add(refreshButton);

        // Adding components to frame
        add(centerPanel, BorderLayout.CENTER);

        // Action listeners for menu items
        addDrink.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Add Drink functionality not implemented yet.");
        });

        removeDrink.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Remove Drink functionality not implemented yet.");
        });

        editDrink.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Edit Drink functionality not implemented yet.");
        });

        createUser.addActionListener(e -> {
            // Initialize variables to preserve user input
            String preservedUsername = "";
            String preservedPassword = "";
            String preservedRole = "Manager";
            Date preservedDOB = null;
            String preservedName = "";

            while (true) {
                // Create a panel for user input
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                // Fields for user input
                JTextField nameField = new JTextField(preservedName);
                JTextField usernameField = new JTextField(preservedUsername);
                JPasswordField passwordField = new JPasswordField(preservedPassword);
                JComboBox<String> roleComboBox = new JComboBox<>(new String[]{"Manager", "Bartender", "Guest"});
                roleComboBox.setSelectedItem(preservedRole);
                JTextField dobField = new JTextField();

                // If preservedDOB is not null, display it as a string in the DOB field
                if (preservedDOB != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                    dobField.setText(sdf.format(preservedDOB));
                }

                // Add components to the panel
                panel.add(new JLabel("Name:"));
                panel.add(nameField);
                panel.add(new JLabel("Username:"));
                panel.add(usernameField);
                panel.add(new JLabel("Password:"));
                panel.add(passwordField);
                panel.add(new JLabel("Role:"));
                panel.add(roleComboBox);

                // Add DOB label and field
                JLabel dobLabel = new JLabel("Date of Birth (MM/dd/yyyy):");
                dobLabel.setEnabled("Guest".equals(preservedRole));
                dobField.setEnabled("Guest".equals(preservedRole));
                panel.add(dobLabel);
                panel.add(dobField);

                // Update DOB field based on role selection
                roleComboBox.addActionListener(e1 -> {
                    boolean isGuest = "Guest".equals(roleComboBox.getSelectedItem());
                    dobLabel.setEnabled(isGuest);
                    dobField.setEnabled(isGuest);
                });

                // Show dialog for user creation
                int option = JOptionPane.showConfirmDialog(
                        null, panel, "Create User", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                // Exit if the user clicks "Cancel" or closes the dialog
                if (option == JOptionPane.CANCEL_OPTION || option == JOptionPane.CLOSED_OPTION) {
                    break;
                }

                // Get user inputs
                preservedName = nameField.getText().trim();
                preservedUsername = usernameField.getText().trim();
                preservedPassword = new String(passwordField.getPassword()).trim();
                preservedRole = (String) roleComboBox.getSelectedItem();

                // Parse and validate date of birth if needed
                String dobText = dobField.getText().trim();
                if ("Guest".equals(preservedRole) && !dobText.isEmpty()) {
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                        sdf.setLenient(false); // Strict date validation
                        preservedDOB = sdf.parse(dobText);

                        // Check if the date matches the input
                        if (!dobText.equals(sdf.format(preservedDOB))) {
                            throw new Exception("Invalid or non-existent date.");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(
                                null, "Invalid date format. Please use MM/dd/yyyy.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        continue; // Retry if invalid date
                    }
                } else if ("Guest".equals(preservedRole)) {
                    preservedDOB = null;
                }

                // Validate inputs
                if (preservedName.isEmpty() || preservedUsername.isEmpty() || preservedPassword.isEmpty() ||
                    ("Guest".equals(preservedRole) && preservedDOB == null)) {
                    JOptionPane.showMessageDialog(
                            null, "Please fill in all required fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Create user object and insert into database
                    if ("Guest".equals(preservedRole)) {
                        Guests user = new Guests(preservedName, preservedUsername, preservedPassword, preservedDOB);
                    } else {
                        Users user = new Users(preservedName, preservedUsername, preservedPassword, preservedRole);
                    }

                    System.out.println("call insert user function");
                    JOptionPane.showMessageDialog(null, "User successfully created!");
                    break;
                }
            }
        });

        removeUser.addActionListener(e -> {
            while (true) {
                // Create a panel to input user ID
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                // Create a text field for User ID input
                JTextField userIdField = new JTextField();

                // Add label and input field to the panel
                panel.add(new JLabel("Enter User ID to Remove User:"));
                panel.add(userIdField);

                // Show the confirmation dialog with "Confirm" and "Cancel" buttons
                int option = JOptionPane.showConfirmDialog(
                        null, panel, "Remove User", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                // Exit the loop if the user clicks "Cancel" or closes the dialog
                if (option == JOptionPane.CANCEL_OPTION || option == JOptionPane.CLOSED_OPTION) {
                    break; // Stop prompting and exit the action
                }

                // Get the User ID from the text field
                String userId = userIdField.getText().trim();

                // Check if the User ID is empty
                if (userId.isEmpty()) {
                    // Show an error if the user ID is empty
                    JOptionPane.showMessageDialog(null, "Please enter a User ID.", "Input Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        // Try to parse the user ID as an integer
                        int id = Integer.parseInt(userId);

                        // If valid, proceed with the user removal logic
                        System.out.println("call delete user function");


                        JOptionPane.showMessageDialog(null, "User with ID " + id + " has been removed.");
                        break; // Exit the loop after successful input
                    } catch (NumberFormatException ex) {
                        // Show an error if the input is not a valid number
                        JOptionPane.showMessageDialog(null, "Please enter a valid numeric User ID.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        editUser.addActionListener(e -> {
            while (true) {
                // Create a panel to input user ID
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                // Create a text field for User ID input
                JTextField userIdField = new JTextField();

                // Add label and input field to the panel
                panel.add(new JLabel("Enter User ID to Edit User:"));
                panel.add(userIdField);

                // Show the confirmation dialog with "Confirm" and "Cancel" buttons
                int option = JOptionPane.showConfirmDialog(
                        null, panel, "Find User", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                // Exit the loop if the user clicks "Cancel" or closes the dialog
                if (option == JOptionPane.CANCEL_OPTION || option == JOptionPane.CLOSED_OPTION) {
                    break; // Stop prompting and exit the action
                }

                // Get the User ID from the text field
                String userId = userIdField.getText().trim();

                // Check if the User ID is empty
                if (userId.isEmpty()) {
                    // Show an error if the user ID is empty
                    JOptionPane.showMessageDialog(null, "Please enter a User ID.", "Input Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        // Try to parse the user ID as an integer
                        int id = Integer.parseInt(userId);

                        // If valid, proceed with the user removal logic
                        System.out.println("find user to edit information");

                        // Initialize variables to preserve user input
                        String preservedName = ""; // New variable for name
                        String preservedUsername = "";
                        String preservedPassword = "";
                        String preservedRole = "Manager";
                        Date preservedDOB = null; // Change to Date

                        while (true) {
                            // Create a panel for user input
                            JPanel editPanel = new JPanel();
                            editPanel.setLayout(new BoxLayout(editPanel, BoxLayout.Y_AXIS));

                            // Fields for user input
                            JTextField nameField = new JTextField(preservedName); // Pre-fill with preserved value
                            JTextField usernameField = new JTextField(preservedUsername); // Pre-fill with preserved value
                            JPasswordField passwordField = new JPasswordField(preservedPassword); // Pre-fill with preserved value
                            JComboBox<String> roleComboBox = new JComboBox<>(new String[]{"Manager", "Bartender", "Guest"});
                            roleComboBox.setSelectedItem(preservedRole); // Pre-select preserved value
                            JTextField dobField = new JTextField(); // Date of Birth field

                            // If preservedDOB is not null, display it as a string in the DOB field
                            if (preservedDOB != null) {
                                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                                dobField.setText(sdf.format(preservedDOB)); // Format Date to String for display
                            }

                            // Add components to the panel
                            editPanel.add(new JLabel("Name:"));
                            editPanel.add(nameField); // Add name field to panel
                            editPanel.add(new JLabel("Username:"));
                            editPanel.add(usernameField);
                            editPanel.add(new JLabel("Password:"));
                            editPanel.add(passwordField);
                            editPanel.add(new JLabel("Role:"));
                            editPanel.add(roleComboBox);

                            // Add DOB label and field
                            JLabel dobLabel = new JLabel("Date of Birth (MM/dd/yyyy):");
                            dobLabel.setEnabled("Guest".equals(preservedRole)); // Enable based on preserved role
                            dobField.setEnabled("Guest".equals(preservedRole)); // Enable based on preserved role

                            editPanel.add(dobLabel);
                            editPanel.add(dobField);

                            // Role selection action listener
                            roleComboBox.addActionListener(e1 -> {
                                if ("Guest".equals(roleComboBox.getSelectedItem())) {
                                    dobLabel.setEnabled(true);
                                    dobField.setEnabled(true);
                                } else {
                                    dobLabel.setEnabled(false);
                                    dobField.setEnabled(false);
                                }
                            });

                            // Show dialog for user creation
                            int option1 = JOptionPane.showConfirmDialog(
                                    null, editPanel, "Edit User", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                            // Exit if the user clicks "Cancel" or closes the dialog
                            if (option1 == JOptionPane.CANCEL_OPTION || option1 == JOptionPane.CLOSED_OPTION) {
                                break; // Stop prompting and exit the action
                            }

                            // Get user inputs
                            preservedName = nameField.getText().trim(); // Store the name
                            preservedUsername = usernameField.getText().trim();
                            preservedPassword = new String(passwordField.getPassword()).trim();
                            preservedRole = (String) roleComboBox.getSelectedItem();

                            // Parse the date of birth input and store it as a Date object
                            String dobText = dobField.getText().trim();
                            if (!dobText.isEmpty()) {
                                try {
                                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                                    preservedDOB = sdf.parse(dobText); // Parse input string to Date
                                } catch (Exception ex) {
                                    JOptionPane.showMessageDialog(null, "Invalid date format. Please use MM/dd/yyyy.", "Input Error", JOptionPane.ERROR_MESSAGE);
                                    continue; // Retry if invalid date format
                                }
                            } else {
                                preservedDOB = null; // Set null if no date entered
                            }

                            // Validate inputs
                            if (preservedName.isEmpty() || preservedUsername.isEmpty() || preservedPassword.isEmpty() ||
                                ("Guest".equals(preservedRole) && preservedDOB == null)) {
                                JOptionPane.showMessageDialog(
                                        null, "Please fill in all required fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
                            } else {
                                // Handle user update
                                if ("Guest".equals(preservedRole)) {
                                    Guests user = new Guests(preservedName, preservedUsername, preservedPassword, preservedDOB);
                                } else {
                                    Users user = new Users(preservedName, preservedUsername, preservedPassword, preservedRole);
                                }

                                // Insert the user into the database
                                System.out.println("call insert user function");
                                JOptionPane.showMessageDialog(null, "User successfully updated!");
                                break; // Exit the loop after successful input
                            }
                        }

                        break; // Exit the loop after successful input
                    } catch (NumberFormatException ex) {
                        // Show an error if the input is not a valid number
                        JOptionPane.showMessageDialog(null, "Please enter a valid numeric User ID.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        generateReport.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Generate Report functionality not implemented yet.");
        });

        exportReport.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Export Report functionality not implemented yet.");
        });

        initManagerScreen();
//        client.setOrderUpdateListener(this);
//        client.startListener();

        setVisible(true);
    }

    public void initManagerScreen() {
       try {
           getOrder();
       } catch (IOException | ClassNotFoundException ex) {
           LOGGER.error(ex.getMessage());
       }
    }

    public void getOrder() throws IOException, ClassNotFoundException {
        List<Order> orders = client.getPendingOrders();
        for (Order order : orders) {
            addOrder(order);
        }

        orders = client.getServedOrders();
        for (Order order : orders) {
            addServedOrder(order);
        }
    }

    public void getOrderDrinkObject(Order order) throws IOException, ClassNotFoundException {
        OD = client.getOrderDetails(order);
    }
    public List<OrderDetail> getOrderDetails() {
        return (List<OrderDetail>) OD[0];
    }

    public List<Drink> getOrderDetailsDrinks() {
        return (List<Drink>) OD[1];
    }

//    @Override
//    public void onNewOrder(Order order) {
//        SwingUtilities.invokeLater(() -> addOrder(order));
//    }

    // Method to add a single order to the list
    public void addOrder(Order order) {
        if (orderListContentPanel.getComponentCount() == 2 && orderListContentPanel.getComponent(0) == noOrdersLabel) {
            orderListContentPanel.remove(noOrdersLabel);
        }

        JPanel orderPanel = new JPanel(new BorderLayout());
        orderPanel.setBackground(Color.WHITE);
        orderPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.LIGHT_GRAY));
        orderPanel.setMaximumSize(new Dimension(1500, 80)); // This prevents the order panel from expanding

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(new EmptyBorder(10, 10, 0, 10));

        JLabel orderLabel = new JLabel("Order#" + String.format("%03d", order.getOrderId()));
        orderLabel.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel guestLabel = new JLabel("    Guest " + order.getGuestId() + "  |  " + order.getOrderDate());
        guestLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        infoPanel.add(orderLabel);
        infoPanel.add(Box.createVerticalStrut(12));
        infoPanel.add(guestLabel);

        orderPanel.add(infoPanel, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // View Button
        JButton viewButton = new JButton("VIEW");
        viewButton.setBackground(Color.decode("#d0e4ba"));
        viewButton.setForeground(Color.BLACK);
        viewButton.setFont(new Font("Arial", Font.BOLD, 14));
        viewButton.setFocusPainted(false);
        viewButton.setPreferredSize(new Dimension(100, 40));
        viewButton.addActionListener(e -> {
            try {
                showOrderDetails(order);
            } catch (IOException | ClassNotFoundException ex) {
                LOGGER.error(ex.getMessage());
            }
        });

        // Serve Button
        JButton serveButton = new JButton("SERVE");
        serveButton.setBackground(Color.decode("#f7a072"));
        serveButton.setForeground(Color.BLACK);
        serveButton.setFont(new Font("Arial", Font.BOLD, 14));
        serveButton.setFocusPainted(false);
        serveButton.setPreferredSize(new Dimension(100, 40));
        serveButton.addActionListener(e -> {
            removeOrder(order.getOrderId());
            addServedOrder(order);
            try {
                client.sendServedOrders(order);
            } catch (IOException | ClassNotFoundException ex) {
                LOGGER.error(ex.getMessage());
            }
        });

        buttonPanel.add(viewButton);
        buttonPanel.add(serveButton);

        orderPanel.add(buttonPanel, BorderLayout.EAST);
        orderListContentPanel.add(orderPanel);
        orderListContentPanel.add(Box.createVerticalStrut(5));

        orderListContentPanel.revalidate();
        orderListContentPanel.repaint();
    }


    public void removeOrder(int orderId) {
        Component[] components = orderListContentPanel.getComponents();

        // Iterate over components to find the order panel with the matching orderId
        for (int i = 0; i < components.length; i++) {
            Component component = components[i];

            if (component instanceof JPanel) {
                JPanel orderPanel = (JPanel) component;
                JPanel infoPanel = (JPanel) ((BorderLayout) orderPanel.getLayout()).getLayoutComponent(BorderLayout.CENTER);
                JLabel orderLabel = (JLabel) infoPanel.getComponent(0);

                // Check if the label text matches the formatted orderId
                if (orderLabel.getText().equals("Order#" + String.format("%03d", orderId))) {
                    // Remove the order panel
                    orderListContentPanel.remove(orderPanel);

                    // Check if the next component is a vertical strut (spacing), and remove it if present
                    if (i + 1 < components.length && components[i + 1] instanceof Box.Filler) {
                        orderListContentPanel.remove(components[i + 1]);
                    }
                    break;
                }
            }
        }

        // Display "No orders available" label if no orders remain
        if (orderListContentPanel.getComponentCount() == 0) {
            orderListContentPanel.add(noOrdersLabel); // Add "No orders available" label if empty
        }

        // Refresh the UI
        orderListContentPanel.revalidate();
        orderListContentPanel.repaint();
    }   
    
    private void showOrderDetails(Order order) throws IOException, ClassNotFoundException {


        getOrderDrinkObject(order);
        List<Drink> drinks = getOrderDetailsDrinks();
        List<OrderDetail> orderDetails = getOrderDetails();
        List<String> orderItem = new ArrayList<>();
        for (Drink drink : drinks) {
            for (OrderDetail orderDetail : orderDetails) {
                if (orderDetail.getDrinkId() == drink.getId()){
                    drink.setQuantity(orderDetail.getQuantity());
                }
            }
            orderItem.add(drink.getName() + " x" + drink.getQuantity());
        }

        String items = String.join("\n", orderItem);
        String message = """
            Order Details:
            Order Date: %s
            Order ID: %s
            Guest ID: %s
            Items: \n
            %s
            """.formatted(order.getOrderDate(), order.getOrderId(), order.getGuestId(), items);

        JOptionPane.showMessageDialog(this, message, "Order Details", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void addServedOrder(Order order) {
        if (servedOrdersContentPanel.getComponentCount() == 1 && servedOrdersContentPanel.getComponent(0) == noServedOrdersLabel) {
            servedOrdersContentPanel.remove(noServedOrdersLabel);
        }

        JLabel servedOrderLabel = new JLabel("Order#" + order.getOrderIdAsString()
        + "  |  Guest " + order.getGuestId() + "  |  Date: " + order.getOrderDate());
        servedOrderLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        servedOrdersContentPanel.add(servedOrderLabel);
        servedOrdersContentPanel.add(Box.createVerticalStrut(5));

        servedOrdersContentPanel.revalidate();
        servedOrdersContentPanel.repaint();
    }



    public static void main(String[] args) {
        ManagerScreen managerScreen = new ManagerScreen(new Client());
    }
}
