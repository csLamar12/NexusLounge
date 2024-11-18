package Controller;

import Model.*;
import View.DrinkPanel;
import View.MainMenu;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Lamar Haye
 *
 */
public class MainMenuController {

    private static final Logger LOGGER = LogManager.getLogger(MainMenuController.class);

    private MainController mc;
    private MainMenu mainMenu;
    private List<JPanel> drinkPanels;
    private DrinkPanelController dPController;
    private List<Drink> drinks = new ArrayList<>();
    private Client client;
    private Guests guest;

    public MainMenuController(MainMenu mainMenu, Client client, Guests guest) {
        this.mainMenu = mainMenu;
        this.client = client;
        this.guest = guest;
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
                guest.calculateAge();
                if (guest.getAge() >= 18)
                    mainMenu.setAlcoholicTabActive();
                else
                    mainMenu.displayMessage("You're not old enough to access this menu!");
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
                try {
                    // will set bartender ID when bartender clicks serve
                    int orderID = client.sendOrder(new Order(guest.getId(), 2, new Date(), false));
                    List<OrderDetail> orderDetails = new ArrayList<>();
                    for (Drink d : mainMenu.getCheckoutList()) {
                        orderDetails.add(new OrderDetail(orderID, d.getId(), d.getQuantity()));
                    }
                    client.sendOrderDetail(orderDetails);

                } catch (IOException | ClassNotFoundException ex) {
                    LOGGER.error(ex);
                }
                mainMenu.displayMessage("Order Received");
                mainMenu.dispose();

            }
        });
    }

    public List<Drink> getAlcoholicDrinks() {
        return client.getAlcoholicDrinks();
    }
    public List<Drink> getNonAlcoholicDrinks() {
        return client.getNonAlcoholicDrinks();
    }
}
