package View;

import Model.Drink;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainMenu extends JFrame {
    private JPanel drinkPanel;


    public MainMenu() {
        setTitle("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        getContentPane().setBackground(Color.WHITE);
        setBackground(Color.RED);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new GridBagLayout());
        setVisible(true);
        configureDrinkPanels();
        addComponents();
        validate();

    }
    public void configureDrinkPanels() {
        drinkPanel = new DrinkPanel(new Drink(1, "Whiskey Sour", true));
    }
    public void addComponents(){
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;

        c.gridx = 0;
        c.gridy = 0;
        add(drinkPanel, c);

    }
    public JPanel getDrinkPanel() {
        return drinkPanel;
    }

}
