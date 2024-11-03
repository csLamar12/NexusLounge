package View;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Main view class for the application
 */
public class MainView extends JFrame {
    private SplashScreen splashScreen;
    private LoginScreen loginScreen;
    private MainMenu mainMenu;
    private Date dateOfBirth;

    public MainView() {

    }

    public void showSplashScreen(){
        splashScreen = new SplashScreen();
    }
    public void showLoginScreen(){
        loginScreen = new LoginScreen();
    }

    public void getGuestInfo(){
// Date format for input
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);  // Strict format matching

        while (true) {
            // Show an input dialog asking for date of birth
           String dobInput = JOptionPane.showInputDialog(this, "Please enter your Date of Birth (yyyy-MM-dd):", "Date of Birth", JOptionPane.PLAIN_MESSAGE);

            if (dobInput == null) {
                // User pressed Cancel or closed the dialog
                JOptionPane.showMessageDialog(this, "Date of birth entry was cancelled.", "Cancelled", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                // Parse the input date
                dateOfBirth = dateFormat.parse(dobInput);
                JOptionPane.showMessageDialog(this, "Thank you!! Enjoy!!", "Success", JOptionPane.INFORMATION_MESSAGE);
                break;
            } catch (ParseException e) {
                // Show error if input is not a valid date
                JOptionPane.showMessageDialog(this, "Invalid date format. Please enter the date in yyyy-MM-dd format.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
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