package Model;

public class DataModel {

    /**
     * Delegate login check responsibilities to UserSQLProvider class
     * @param username  User's Username
     * @param password  User's Password
     * @return  Returns a boolean value to represent success of authentication
     *
     */
    public boolean authenticateUser(String username, String password) {
        // Call UserSQLProvider to implement user authentication logic
        if (username.equals(" ") && password.equals(" ")) {
            return true;
        } else {
            return false;
        }

    }
}
