package View;

import Model.Guests;
import Model.Users;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ManagerDrinkMenu {
    public void addDrink(){

        //int quantity = 0;
        String name = "";
        String description = "";
        boolean isAlcoholic;

        while (true) {
            // Create a panel for user input
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            // Fields for user input
            JTextField drinkName = new JTextField(name);
            JTextField drinkDesc = new JTextField(description);
            JComboBox<String> drinkType = new JComboBox<>(new String[]{"Alcoholic","Non-Alcoholic"});
            drinkType.setSelectedItem("isAlcoholic");

            // Add components to the panel
            panel.add(new JLabel("Name:"));
            panel.add(drinkName);
            panel.add(new JLabel("Description:"));
            panel.add(drinkDesc);
            panel.add(new JLabel("Type:"));
            panel.add(drinkType);
            panel.add(new ResourceUpload());


            // Show dialog for user creation
            int option = JOptionPane.showConfirmDialog(
                    null, panel, "Add Drink", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            // Exit if the user clicks "Cancel" or closes the dialog
            if (option == JOptionPane.CANCEL_OPTION || option == JOptionPane.CLOSED_OPTION) {
                break;
            }

            // Get user inputs
            name = drinkName.getText().trim();
            description = drinkDesc.getText().trim();
            isAlcoholic = (boolean) drinkType.getSelectedItem();
        }
    }

        public void removeDrink(){
        while (true) {
            // Create a panel to input user ID
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            // Create a text field for User ID input
            JTextField drinkID = new JTextField();

            // Add label and input field to the panel
            panel.add(new JLabel("Enter Drink ID to Remove Drink:"));
            panel.add(drinkID);

            // Show the confirmation dialog with "Confirm" and "Cancel" buttons
            int option = JOptionPane.showConfirmDialog(
                    null, panel, "Remove Drink", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            // Exit the loop if the user clicks "Cancel" or closes the dialog
            if (option == JOptionPane.CANCEL_OPTION || option == JOptionPane.CLOSED_OPTION) {
                break; // Stop prompting and exit the action
            }

            // Get the User ID from the text field
            String drinkId = drinkID.getText().trim();

            // Check if the User ID is empty
            if (drinkId.isEmpty()) {
                // Show an error if the user ID is empty
                JOptionPane.showMessageDialog(null, "Please enter a Drink ID.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    // Try to parse the user ID as an integer
                    int id = Integer.parseInt(drinkId);

                    // If valid, proceed with the user removal logic
                    System.out.println("call delete user function");


                    JOptionPane.showMessageDialog(null, "Drink with ID " + id + " has been removed.");
                    break; // Exit the loop after successful input
                } catch (NumberFormatException ex) {
                    // Show an error if the input is not a valid number
                    JOptionPane.showMessageDialog(null, "Please enter a valid numeric Drink ID.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

        public void editDrink(){
        while (true) {
            // Create a panel to input user ID
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            // Create a text field for User ID input
            JTextField drinkIdField = new JTextField();

            // Add label and input field to the panel
            panel.add(new JLabel("Enter Drink ID to Edit User:"));
            panel.add(drinkIdField);

            // Show the confirmation dialog with "Confirm" and "Cancel" buttons
            int option = JOptionPane.showConfirmDialog(
                    null, panel, "Find Drink", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            // Exit the loop if the user clicks "Cancel" or closes the dialog
            if (option == JOptionPane.CANCEL_OPTION || option == JOptionPane.CLOSED_OPTION) {
                break; // Stop prompting and exit the action
            }

            // Get the User ID from the text field
            String drinkId = drinkIdField.getText().trim();

            // Check if the User ID is empty
            if (drinkId.isEmpty()) {
                // Show an error if the user ID is empty
                JOptionPane.showMessageDialog(null, "Please enter a Drink ID.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    // Try to parse the user ID as an integer
                    int id = Integer.parseInt(drinkId);

                    // If valid, proceed with the user removal logic
                    System.out.println("find drink to edit information");

                    // Initialize variables to preserve user input
                    String drinkName = ""; // New variable for name
                    String drinkDescription = "";
                    Boolean isAlcoholic = null;

                    while (true) {
                        // Create a panel for user input
                        JPanel editPanel = new JPanel();
                        editPanel.setLayout(new BoxLayout(editPanel, BoxLayout.Y_AXIS));

                        // Fields for user input
                        JTextField nameField = new JTextField(drinkName); // Pre-fill with preserved value
                        JTextField descriptionField = new JTextField(drinkDescription); // Pre-fill with preserved value
                        JComboBox<String> drinkType = new JComboBox<>(new String[]{"Alcoholic", "Non-Alcoholic"});
                        drinkType.setSelectedItem(isAlcoholic); // Pre-select preserved value

                        // Add components to the panel
                        editPanel.add(new JLabel("Drink Name:"));
                        editPanel.add(nameField); // Add name field to panel
                        editPanel.add(new JLabel("Drink Description:"));
                        editPanel.add(descriptionField);
                        editPanel.add(new JLabel("Drink Type:"));
                        editPanel.add(drinkType);

                        // Show dialog for user creation
                        int option1 = JOptionPane.showConfirmDialog(
                                null, editPanel, "Edit Drink", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                        // Exit if the user clicks "Cancel" or closes the dialog
                        if (option1 == JOptionPane.CANCEL_OPTION || option1 == JOptionPane.CLOSED_OPTION) {
                            break; // Stop prompting and exit the action
                        }

                        // Get user inputs
                        drinkName = nameField.getText().trim();
                        drinkDescription = descriptionField.getText().trim();
                        isAlcoholic = (boolean) drinkType.getSelectedItem();//add null pointer exception

                    }

                    break; // Exit the loop after successful input
                } catch (NumberFormatException ex) {
                    // Show an error if the input is not a valid number
                    JOptionPane.showMessageDialog(null, "Please enter a valid numeric Drink ID.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

        public static void main(String[] args) {
            // Create an instance of ManagerDrinkMenu
            ManagerDrinkMenu managerDrinkMenu = new ManagerDrinkMenu();

            while (true) {
                // Provide menu options for testing
                String[] options = {"Add Drink", "Remove Drink", "Edit Drink", "Exit"};
                int choice = JOptionPane.showOptionDialog(
                        null,
                        "Select an action to test:",
                        "Manager Drink Menu Test",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        options,
                        options[0]
                );

                // Perform action based on user's choice
                switch (choice) {
                    case 0: // Add Drink
                        managerDrinkMenu.addDrink();
                        break;
                    case 1: // Remove Drink
                        managerDrinkMenu.removeDrink();
                        break;
                    case 2: // Edit Drink
                        managerDrinkMenu.editDrink();
                        break;
                    default: // Exit
                        System.exit(0);
                        break;
                }
            }
        }
    }


