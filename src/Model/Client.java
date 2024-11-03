package Model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
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

    public Users authenticate(Users user) {
        try {
            out.writeObject("AUTH");
            out.flush();
            out.writeObject(user);
            out.flush();
            return (Users) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.error("Error while authenticating user", e);
            return null;
        }
    }

    public void guestUser(Date dob){
        try{
            out.writeObject("GUEST_LOGIN");
            out.flush();
            Users user = new Guests(0, null, null, null, dob);
            out.writeObject(user);
            out.flush();
        } catch (IOException e) {
            LOGGER.error(e);
        }
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

}
