package Controller;

import Model.DataModel;
import View.LoginScreen;
import View.MainView;

/**
 * Main controller class for the application
 *
 * @author Lamar Haye
 */
public class MainController {
    /**
     * Main Data model for the application
     */
    private DataModel model;
    /**
     * Main view of the application
     */
    private MainView view;

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
        view.showSplashScreen();
        view.showLoginScreen();
        bindLoginScreenButtonEvents();
        // next screen/event
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
        } else
            view.getLoginScreen().displayMessage("Login Failed");
    }

    /**
     * Handles guest login
     */
    public void guestLogin(){
        view.getLoginScreen().dispose();
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
