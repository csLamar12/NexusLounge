package Model;

import View.ManagerScreen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *
 * @author Lamar Haye
 */
public class Client {
    private static final Logger LOGGER = LogManager.getLogger(Client.class);
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12350;

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Thread listenerThread;
    private boolean running = true;

    public Client() {
        try{
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            LOGGER.info("Connected to server: " + SERVER_ADDRESS + " on port: " + SERVER_PORT);
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

//    public interface OrderUpdateListener {
//        void onNewOrder(Order order);
//    }
//
//    private OrderUpdateListener orderUpdateListener;
//
//    public void setOrderUpdateListener(OrderUpdateListener orderUpdateListener) {
//        this.orderUpdateListener = orderUpdateListener;
//    }
//
//    // Method to start the listener thread (for handling incoming messages)
//    public void startListener() {
//        // Assuming this part exists to listen for new orders
//        new Thread(() -> {
//            try {
//                while (true) {
//                    Object message = in.readObject();
//                    if ("NEW_ORDER".equals(message)) {
//                        Order order = (Order) in.readObject();
//                        if (orderUpdateListener != null) {
//                            orderUpdateListener.onNewOrder(order);  // Notify the listener
//                        }
//                    }
//                }
//            } catch (IOException | ClassNotFoundException e) {
//                LOGGER.error("Error while listening for new orders", e);
//            }
//        }).start();
//    }

    public Users authenticate(Users user) {
        try {
            out.writeObject("AUTH");
            out.flush();
            out.writeObject(user);
            out.flush();
            user = (Users) in.readObject();
            if (user instanceof Guests guest){
                guest.calculateAge();
                return guest;
            }
            return user;
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.error("Error while authenticating user", e);
            return null;
        }
    }

    public int getGuestAge(Guests guest) {
        guest.calculateAge();
        return guest.getAge();
    }

    public Guests guestUser(Date dob){
        try{
            out.writeObject("GUEST_LOGIN");
            out.flush();
            Users user = new Guests(0, null, null, null, dob);
            out.writeObject(user);
            out.flush();
            user = (Guests) in.readObject();
            return (Guests) user;
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.error(e);
        }
        return null;
    }

    public List<Drink> getAlcoholicDrinks() {
        try {
            return (List<Drink>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.error(e);
        }
        return null;
    }
    public List<Drink> getNonAlcoholicDrinks() {
        try {
            return (List<Drink>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.error(e);
        }
        return null;
    }

    public int sendOrder(Order order) throws IOException, ClassNotFoundException {
        out.writeObject("ORDER");
        out.flush();
        out.writeObject(order);
        out.flush();
        order = (Order) in.readObject();
        return order.getOrderId();
    }

    public void sendOrderDetail(List<OrderDetail> orderDetails) throws IOException, ClassNotFoundException {
        out.writeObject("ORDER_DETAIL");
        out.flush();
        out.writeObject(orderDetails);
        out.flush();
    }

    public List<Order> getPendingOrders() throws IOException, ClassNotFoundException {
        List<Order> orders;
        out.writeObject("GET_PENDING_ORDER");
        out.flush();
        orders = (List<Order>) in.readObject();
        return orders;
    }



    public List<Order> getServedOrders() throws IOException, ClassNotFoundException {
        List<Order> orders;
        out.writeObject("GET_SERVED_ORDER");
        out.flush();
        orders = (List<Order>) in.readObject();
        return orders;
    }

    public void sendServedOrders(Order order) throws IOException, ClassNotFoundException {
        out.writeObject("SERVED_ORDER");
        out.flush();
        out.writeObject(order);
        out.flush();
    }

    public Object[] getOrderDetails(Order order) throws IOException, ClassNotFoundException {
        List<OrderDetail> orderDetails;
        out.writeObject("GET_ORDER_DETAIL");
        out.flush();
        out.writeObject(order);
        out.flush();
        orderDetails = (List<OrderDetail>) in.readObject();
        List<Drink> drinks = (List<Drink>) in.readObject();
        return new Object[]{orderDetails, drinks};
    }


}
