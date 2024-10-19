package View;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Main view class for the application
 */
public class MainView extends JFrame {
    private SplashScreen splashScreen;
    private LoginScreen loginScreen;
    private MainMenu mainMenu;

    public MainView() {

    }

    public void showSplashScreen(){
        splashScreen = new SplashScreen();
    }
    public void showLoginScreen(){
        loginScreen = new LoginScreen();
    }
    public void showMainMenuScreen(){
        mainMenu = new MainMenu();
    }

    public MainMenu getMainMenuScreen() {
        return mainMenu;
    }

    public LoginScreen getLoginScreen() {
        return loginScreen;
    }
    public SplashScreen getSplashScreen() {
        return splashScreen;
    }

}

// each view should have it's own controller