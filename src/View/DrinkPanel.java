//Author: Danielle Johns
package View;

import Model.Drink; // Import Drink class from the model package

import javax.swing.*;
import java.awt.*;

/**
 * The DrinkPanel class is a custom JPanel component designed to display information about a Drink object.
 * It uses a 3x3 grid layout to position drink-related elements such as the drink name, image,
 * and controls for adjusting the quantity.
 */
public class DrinkPanel extends JPanel {

    // JLabel to display the name of the drink
    private final JLabel drinkNameLabel;

    // Buttons and quantity components
    private final JButton minusButton;
    private final JButton plusButton;
    private final JTextField quantityField;

    /**
     * Constructor that initializes the DrinkPanel with the provided Drink object.
     *
     * @param drink the Drink object containing the drink information to display
     */
    public DrinkPanel(Drink drink) {
        // Set the layout of the panel to GridBagLayout for precise component positioning
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Set the background color of the panel to light gray
        setBackground(Color.LIGHT_GRAY);

        // Add a black border around the panel with a thickness of 2 pixels
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        // Add the image placeholder (spanning all 3 columns)
        JLabel imageLabel = new JLabel("Drink Image", SwingConstants.CENTER); // Placeholder for image
        imageLabel.setPreferredSize(new Dimension(200, 100)); // Placeholder size
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;  // Span all 3 columns
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(imageLabel, gbc);

        // Initialize the drinkNameLabel with the drink's name and center the text
        drinkNameLabel = new JLabel(drink.getName(), SwingConstants.CENTER);
        drinkNameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;  // Span all 3 columns
        gbc.insets = new Insets(5, 0, 5, 0);  // Add some padding around the label
        add(drinkNameLabel, gbc);

        // Add the minus button, quantity field, and plus button in the third row

        // Minus button
        minusButton = new JButton("-");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;  // Occupy one column
        gbc.fill = GridBagConstraints.NONE;
        add(minusButton, gbc);

        // Quantity field (center column)
        quantityField = new JTextField("1", 3);
        quantityField.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(quantityField, gbc);

        // Plus button
        plusButton = new JButton("+");
        gbc.gridx = 2;
        gbc.gridy = 2;
        add(plusButton, gbc);
    }

    /**
     * Updates the DrinkPanel's displayed information based on a new Drink object.
     * This is useful for refreshing the panel when the drink details are changed.
     *
     * @param drink the new Drink object containing updated information
     */
    public void updateDrinkInfo(Drink drink) {
        // Update the drink name label with the new drink's name
        drinkNameLabel.setText(drink.getName());
    }
}
