package Model;

import javax.swing.*;

public class DataModel {


    public boolean checkLogin(String username, String password) {
        // Implement DB login logic
        if (username.equals(" ") && password.equals(" ")) {
            return true;
        } else {
            return false;
        }

    }
}
