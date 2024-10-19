package Controller;

import Model.DataModel;
import View.DrinkPanel;
import View.LoginScreen;
import View.MainView;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;
import java.util.InputMismatchException;

/**
 * Main controller class for the application
 *
 * @author Lamar Haye
 */
public class MainController {

    private DataModel model;
    private MainView view;
    private DrinkPanelController dpController;

    /**
     * Primary Constructor for the MainController class
     *
     * @param model The data model
     * @param view  The main view
     */
    public MainController(DataModel model, MainView view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Initialize the application by showing the splash screen and login screen.
     */
    public void initApp(){
//        view.showSplashScreen();
//        view.showLoginScreen();
//        bindLoginScreenButtonEvents();
        view.showMainMenuScreen();
        dpController = new DrinkPanelController((DrinkPanel) view.getMainMenuScreen().getDrinkPanel());
    }

    /**
     * Authenticate user based on their username and password
     */
    public void authenticateUser() {
        String username = view.getLoginScreen().getUsername();
        String password = view.getLoginScreen().getPassword();
        if (model.authenticateUser(username, password)){
            view.getLoginScreen().displayMessage("Login Successful");
            view.getLoginScreen().dispose();
            view.showMainMenuScreen();
        } else
            view.getLoginScreen().displayMessage("Login Failed");
    }

    /**
     * Handles guest login
     */
    public void guestLogin(){
        view.getLoginScreen().dispose();
        view.showMainMenuScreen();
    }

    /**
     * Bind the button events to the login screen
     */
    public void bindLoginScreenButtonEvents(){
        LoginScreen loginScreen = view.getLoginScreen();
        loginScreen.setLoginButtonLister(e -> authenticateUser());
        loginScreen.setGuestButtonLister(e -> guestLogin());
    }

}
