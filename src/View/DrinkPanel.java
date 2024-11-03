package View;

import Model.Drink; // Import Drink class from the model package

import javax.swing.*;
import java.awt.*;

/**
 * The DrinkPanel class is a custom JPanel component designed to display information about a Drink object.
 * It uses a 3x3 grid layout to position drink-related elements such as the drink name, image,
 * and controls for adjusting the quantity.
 *
 * @author Danielle Johns
 */
public class DrinkPanel extends JPanel {

    private ImageIcon drinkImg;
    private Drink drink;
    // JLabel to display the name of the drink
    private final JLabel drinkNameLabel;

    // Buttons and quantity components
    private JButton minusButton;
    private JButton plusButton;
    private JTextField quantityField;
    private ImageIcon minusButtonIconActive;
    private ImageIcon plusButtonIconActive;
    private ImageIcon minusButtonIconInactive;
    private ImageIcon plusButtonIconInactive;
    private final String minusIconPathInactive = "src/Resources/Buttons/MinusButtonInActive.png";
    private final String minusIconPathActive = "src/Resources/Buttons/MinusButtonActive.png";
    private final String plusIconPathInactive = "src/Resources/Buttons/PlusButtonInActive.png";
    private final String plusIconPathActive = "src/Resources/Buttons/PlusButtonActive.png";

    /**
     * Constructor that initializes the DrinkPanel with the provided Drink object.
     *
     * @param drink the Drink object containing the drink information to display
     */
    public DrinkPanel(Drink drink) {
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
//            ex.printStackTrace();
//        }
        this.drink = drink;
        ToolTipManager.sharedInstance().setInitialDelay(700);
        minusButtonIconActive = new ImageIcon(minusIconPathActive);
        plusButtonIconInactive = new ImageIcon(plusIconPathInactive);
        String drinkImgPath = String.format("src/Resources/Drinks/%d.png", drink.getId());
        // Set the layout of the panel to GridBagLayout for precise component positioning
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(210, 220));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;

        // Set the background color of the panel to light gray
        setBackground(Color.WHITE);

        // Add a black border around the panel with a thickness of 2 pixels
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        // Add the image placeholder (spanning all 3 columns)
        drinkImg = new ImageIcon(drinkImgPath);
        JLabel imageLabel = new JLabel(drinkImg, SwingConstants.CENTER); // Placeholder for image
        imageLabel.setToolTipText(drink.getDescription());
        imageLabel.setPreferredSize(new Dimension(200, 100)); // Placeholder size
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;  // Span all 3 columns
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(imageLabel, gbc);

        // Initialize the drinkNameLabel with the drink's name and center the text
        drinkNameLabel = new JLabel(drink.getName(), SwingConstants.CENTER);
        drinkNameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        drinkNameLabel.setToolTipText(drink.getName());
        StringBuilder name = new StringBuilder();
        int r = 0;
        if (drink.getName().length() > 20){
            for (Character c : drink.getName().toCharArray()){
                name.append(c);
                r++;
                if (r == 20)
                    break;
            }
            drinkNameLabel.setText(name.toString());
        }
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;  // Span all 3 columns
        gbc.insets = new Insets(5, 0, 5, 0);  // Add some padding around the label
        add(drinkNameLabel, gbc);

        // Add the minus button, quantity field, and plus button in the third row

        // Minus button
        minusButtonIconInactive = new ImageIcon(minusIconPathInactive);
        minusButton = new JButton(minusButtonIconInactive);
        minusButton.setOpaque(false);
        minusButton.setBorderPainted(false);
        minusButton.setToolTipText("Lower Quantity");
        gbc.insets = new Insets(5, 12, 5, 0);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;  // Occupy one column
        gbc.anchor = GridBagConstraints.EAST;
        add(minusButton, gbc);

        // Quantity field (center column)
        quantityField = new JTextField("0", 5);
        quantityField.setFont(new Font("Arial", Font.BOLD, 18));
        quantityField.setHorizontalAlignment(SwingConstants.CENTER);
        quantityField.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,100), 2));
        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(quantityField, gbc);

        // Plus button
        plusButtonIconActive = new ImageIcon(plusIconPathActive);
        plusButton = new JButton(plusButtonIconActive);
        plusButton.setOpaque(false);
        plusButton.setBorderPainted(false);
        plusButton.setToolTipText("Add drink");
        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
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

    public JLabel getDrinkNameLabel() {
        return drinkNameLabel;
    }

    public JButton getMinusButton() {
        return minusButton;
    }

    public JButton getPlusButton() {
        return plusButton;
    }

    public JTextField getQuantityField() {
        return quantityField;
    }

    public ImageIcon getPlusButtonIconActive() {
        return plusButtonIconActive;
    }

    public ImageIcon getMinusButtonIconInactive() {
        return minusButtonIconInactive;
    }

    public String getMinusIconPathInactive() {
        return minusIconPathInactive;
    }

    public String getMinusIconPathActive() {
        return minusIconPathActive;
    }

    public String getPlusIconPathInactive() {
        return plusIconPathInactive;
    }

    public String getPlusIconPathActive() {
        return plusIconPathActive;
    }

    public void setMinusButton(JButton minusButton) {
        this.minusButton = minusButton;
    }
    public void setPlusButton(JButton plusButton) {
        this.plusButton = plusButton;
    }
    public void changeButtonStatusToInactive(JButton button) {
        if (button == this.plusButton) {
            plusButton.setIcon(plusButtonIconInactive);
        }
        if (button == this.minusButton) {
            minusButton.setIcon(minusButtonIconInactive);
        }
    }
    public void changeButtonStatusToActive(JButton button) {
        if (button == this.plusButton) {
            plusButton.setIcon(plusButtonIconActive);
        }
        if (button == this.minusButton) {
            minusButton.setIcon(minusButtonIconActive);
        }
    }
    public Drink getDrink() {
        return this.drink;
    }
}
