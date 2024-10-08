package View;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    private SplashScreen splashScreen;
    private LoginScreen loginScreen;

    public MainView() {

    }

    public void showSplashScreen(){
        splashScreen = new SplashScreen();
    }
    public void showLoginScreen(){
        loginScreen = new LoginScreen();
    }

    public LoginScreen getLoginScreen() {
        return loginScreen;
    }
    public SplashScreen getSplashScreen() {
        return splashScreen;
    }

}
