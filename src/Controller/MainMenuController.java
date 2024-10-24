package Controller;

import Model.Drink;
import Model.DrinkSQLProvider;
import View.DrinkPanel;
import View.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lamar Haye
 *
 */
public class MainMenuController {
    private int age = 21;
    private MainMenu mainMenu;
    private List<JPanel> drinkPanels;
    private DrinkSQLProvider drinkSQLProvider = new DrinkSQLProvider();
    private DrinkPanelController dPController;

    public MainMenuController(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
        mainMenu.setAlcoholicPanels(getAlcoholicDrinks());
        mainMenu.setNonAlcoholicPanels(getNonAlcoholicDrinks());
        mainMenu.initWindow();
        this.drinkPanels = mainMenu.getAllDrinkPanels();
        for (JPanel drinkPanel : drinkPanels) {
            dPController = new DrinkPanelController((DrinkPanel) drinkPanel);
        }
        bindButtonListeners();
    }
    public void bindButtonListeners() {
        mainMenu.getAlcoholicTab().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Replace with user.getAge()
                if (age >= 18)
                    mainMenu.setAlcoholicTabActive();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                mainMenu.getAlcoholicTab().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mainMenu.getAlcoholicTab().setCursor(Cursor.getDefaultCursor());
            }
        });
        mainMenu.getNonAlcoholicTab().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainMenu.setNonAlcoholicTabActive();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                mainMenu.getNonAlcoholicTab().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mainMenu.getNonAlcoholicTab().setCursor(Cursor.getDefaultCursor());
            }
        });
    }

    public List<Drink> getAlcoholicDrinks() {
        return drinkSQLProvider.getDrinkByType(true);
    }
    public List<Drink> getNonAlcoholicDrinks() {
        return drinkSQLProvider.getDrinkByType(false);
    }
}
