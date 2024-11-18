package Controller;

import Model.Client;
import Model.Guests;
import Model.Users;
import View.LoginScreen;
import View.MainView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Main controller class for the application
 *
 * @author Lamar Haye
 */
public class MainController {
    private static final Logger LOGGER = LogManager.getLogger(MainController.class);
    private MainView view;
    private MainMenuController mainMenuController;
    private Client client;


    /**
     * Primary Constructor for the MainController class
     *
     * @param view  The main view
     */
    public MainController( MainView view) {
        this.view = view;
    }

    /**
     * Initialize the application by showing the splash screen and login screen.
     */
    public void initApp(){
//        view.showSplashScreen();
        view.showLoginScreen();
        bindLoginScreenButtonEvents();
        client = new Client();
    }

    /**
     * Authenticate user based on their username and password
     */
    public void authenticateUser() {
        Users user = new Users();
        user.setUsername(view.getLoginScreen().getUsername());
        user.setPassword(view.getLoginScreen().getPassword());
        user = client.authenticate(user);
        try {
            if (user != null) {
                if (user.getRole().equals("Guest")) {
                    view.getLoginScreen().displayMessage("Login Successful");
                    view.getLoginScreen().dispose();
                    view.showMainMenuScreen();
                    MainMenuController mMC = new MainMenuController(view.getMainMenuScreen(), client, (Guests) user);
                } else if (user.getRole().equals("Bartender")) {
                    view.getLoginScreen().displayMessage("Login Successful");
                    view.getLoginScreen().dispose();
                    view.showBartenderScreen(client);
                } else if (user.getRole().equals("Manager")) {
                    view.getLoginScreen().displayMessage("Login Successful");
                    view.getLoginScreen().dispose();
                    view.showManagerScreen(client);
                } else
                    view.getLoginScreen().displayMessage("Login Unsuccessful");
            } else
                view.getLoginScreen().displayMessage("Login Failed");
        } catch (NullPointerException e) {
            LOGGER.error("Login Failed due to NullPointerException");
            view.getLoginScreen().displayMessage("Login Failed");
        } catch (Exception e) {
            LOGGER.error("Login Failed: {}", e.getMessage());
            view.getLoginScreen().displayMessage("Login Failed");
        }
    }

    /**
     * Handles guest login
     */
    public void guestLogin(){
        view.getGuestInfo();
        Guests guest = client.guestUser(view.getDateOfBirth());
        view.getLoginScreen().dispose();
        view.showMainMenuScreen();
        MainMenuController mMC = new MainMenuController(view.getMainMenuScreen(), client, guest);
    }

    /**
     * Bind the button events to the login screen
     */
    public void bindLoginScreenButtonEvents(){
        LoginScreen loginScreen = view.getLoginScreen();
        loginScreen.setLoginButtonListener(e -> authenticateUser());
        loginScreen.setGuestButtonListener(e -> guestLogin());
    }

}
