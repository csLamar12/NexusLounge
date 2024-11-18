package View;

import Model.Client;
import Model.Drink;
import Model.Order;
import Model.OrderDetail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import java.util.List;
import java.util.ArrayList;

public class BartenderScreen extends JFrame {

    private static final Logger LOGGER = LogManager.getLogger(ManagerScreen.class);

    private Client client;
    private final JPanel orderListContentPanel;
    private final JPanel servedOrdersContentPanel;
    private final JLabel noOrdersLabel;
    private final JLabel noServedOrdersLabel;
    private JButton refreshButton;
    private Object[] OD;

    public BartenderScreen(Client client) {
        this.client = client;


        // Set up the frame
        setTitle("Manager Screen");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.decode("#d9b7b7"));  // Set background color

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
                    for (Order order : client.getPendingOrders()) {
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
        initBartenderScreen();
//        client.setOrderUpdateListener(this);
//        client.startListener();

        setVisible(true);
    }

    public void initBartenderScreen() {
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

}