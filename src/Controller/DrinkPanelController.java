package Controller;

import View.DrinkPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.InputMismatchException;

public class DrinkPanelController {
    private DrinkPanel drinkPanel;

    public DrinkPanelController(DrinkPanel drinkPanel) {
        this.drinkPanel = drinkPanel;
        quantityKeyListener();
    }

    public void quantityKeyListener(){
        drinkPanel.getQuantityField().addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                char keyChar = e.getKeyChar();
                if (!Character.isDigit(keyChar) &&
                        keyChar != '\b' && // Backspace
                        keyChar != 127) { // Delete
                    e.consume();
                    throw new InputMismatchException();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // No Implementation they just need to be here
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // No Implementation they just need to be here
            }
        });
    }
}
