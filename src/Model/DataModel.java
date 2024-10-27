package Model;

/**
 * Main Data model for the application
 */
public class DataModel {

    /**
     * Delegate login check responsibilities to UsersSQLProvider class
     * @param username  Users's Username
     * @param password  Users's Password
     * @return  Returns a boolean value to represent success of authentication
     *
     */
    public boolean authenticateUser(String username, String password) {
        // Call UsersSQLProvider to implement user authentication logic
        if (username.equals(" ") && password.equals(" ")) {
            return true;
        } else {
            return false;
        }

    }
}
