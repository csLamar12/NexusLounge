package Controller;

import Model.Drink;
import Model.DrinkSQLProvider;
import View.DrinkPanel;
import View.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private MainController mc;
    private MainMenu mainMenu;
    private List<JPanel> drinkPanels;
    private DrinkSQLProvider drinkSQLProvider = new DrinkSQLProvider();
    private DrinkPanelController dPController;
    private List<Drink> drinks = new ArrayList<>();

    public MainMenuController(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
        mainMenu.setAlcoholicPanels(getAlcoholicDrinks());
        mainMenu.setNonAlcoholicPanels(getNonAlcoholicDrinks());
        mainMenu.initWindow();
        this.drinkPanels = mainMenu.getAllDrinkPanels();
        for (JPanel drinkPanel : drinkPanels) {
            dPController = new DrinkPanelController((DrinkPanel) drinkPanel, this);
        }
        bindButtonListeners();
    }

    public void addToCheckoutItems(Drink drink, int quantity) {

        for (Drink d : drinks) {
            if (d.getId() == drink.getId()) {
                d.setQuantity(quantity);
                mainMenu.updateCheckoutItems(drinks);
                return;
            }
        }
        drink.setQuantity(quantity);
        drinks.add(drink);
        mainMenu.updateCheckoutItems(drinks);
    }

    public void minusFromCheckoutItems(Drink drink, int quantity) {
        for (Drink d : drinks) {
            if (d.equals(drink)) {
                if (quantity == 0) {
                    drinks.remove(d);
                    mainMenu.updateCheckoutItems(drinks);
                    return;
                }
                d.setQuantity(quantity);
            }
        }
        mainMenu.updateCheckoutItems(drinks);
    }

    public void updateCheckoutList(Drink drink, int quantity) {
        for (Drink d : drinks) {
            if (d.equals(drink)) {
                if (quantity == 0) {
                    drinks.remove(d);
                    mainMenu.updateCheckoutItems(drinks);
                    return;
                }
                d.setQuantity(quantity);
                mainMenu.updateCheckoutItems(drinks);
                return;
            }
        }
        drink.setQuantity(quantity);
        drinks.add(drink);
        mainMenu.updateCheckoutItems(drinks);
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
        mainMenu.getCheckoutButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Receive order logic then display message
                mainMenu.displayMessage("Order Received");
                mainMenu.dispose();

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
