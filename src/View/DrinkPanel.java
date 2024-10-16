//Author: Danielle Johns
package View;

import Model.Drink; // Import Drink class from the model package

import javax.swing.*;
import java.awt.*;

// DrinkPanel is a custom JPanel component designed to display information about a Drink object.
public class DrinkPanel extends JPanel {

    // JLabel to display the name of the drink
    private final JLabel drinkNameLabel;

    // JLabel to display whether the drink is alcoholic or non-alcoholic
    private final JLabel isAlcoholicLabel;

    // Constructor that creates a DrinkPanel for the given Drink object
    public DrinkPanel(Drink drink) {
        // Set the layout of the panel to BorderLayout for positioning components
        setLayout(new BorderLayout());

        // Set the preferred size of the panel to 200x100 pixels
        setPreferredSize(new Dimension(200, 100));

        // Set the background color of the panel to light gray
        setBackground(Color.LIGHT_GRAY);

        // Add a black border around the panel with a thickness of 2 pixels
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        // Initialize the drinkNameLabel with the drink's name, set the font to bold 18-point Arial,
        // and center the text horizontally within the label
        drinkNameLabel = new JLabel(drink.getName());
        drinkNameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        drinkNameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Determine if the drink is alcoholic or non-alcoholic and create the corresponding label
        String alcoholicStatus = drink.isAlcoholic() ? "Alcoholic" : "Non-Alcoholic";
        isAlcoholicLabel = new JLabel(alcoholicStatus);
        isAlcoholicLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        isAlcoholicLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Add the drink name label to the top (NORTH) of the panel
        add(drinkNameLabel, BorderLayout.NORTH);

        // Add the alcoholic status label to the bottom (SOUTH) of the panel
        add(isAlcoholicLabel, BorderLayout.SOUTH);
    }

    // Method to update the information displayed in the panel with a new Drink object
    public void updateDrinkInfo(Drink drink) {
        // Update the drink name label with the new drink's name
        drinkNameLabel.setText(drink.getName());

        // Update the alcoholic status label with "Alcoholic" or "Non-Alcoholic"
        String alcoholicStatus = drink.isAlcoholic() ? "Alcoholic" : "Non-Alcoholic";
        isAlcoholicLabel.setText(alcoholicStatus);
    }
}
