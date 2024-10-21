package Controller;

import Model.Drink;
import Model.DrinkSQLProvider;
import View.DrinkPanel;
import View.MainMenu;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lamar Haye
 *
 */
public class MainMenuController {
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
    }

    public List<Drink> getAlcoholicDrinks() {
        return drinkSQLProvider.getDrinkByType(true);
    }
    public List<Drink> getNonAlcoholicDrinks() {
        return drinkSQLProvider.getDrinkByType(false);
    }
}
