package Controller;

import View.DrinkPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.InputMismatchException;

/**
 * Mini controller for the DrinkPanel class
 *
 * @author Lamar Haye
 */
public class DrinkPanelController {
    private static final Logger LOGGER = LogManager.getLogger(DrinkPanelController.class);
    private DrinkPanel drinkPanel;
    private int qFTxt;

    /**
     * Primary constructor for the DrinkPanelController class
     * @param drinkPanel
     */
    public DrinkPanelController(DrinkPanel drinkPanel) {
        this.drinkPanel = drinkPanel;
        quantityKeyListener();
        bindDrinkPanelButtonEvents();
    }

    public void bindDrinkPanelButtonEvents(){
        drinkPanel.getMinusButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    qFTxt = Integer.parseInt(drinkPanel.getQuantityField().getText());
                    if(qFTxt == 0){
                        return;
                    }
                    qFTxt = qFTxt - 1;
                    drinkPanel.getQuantityField().setText(String.valueOf(qFTxt));
                } catch (InputMismatchException | NumberFormatException ex){
                    qFTxt = 0;
                } catch (Exception ex){
                    qFTxt = 0;
                    LOGGER.warn(ex.getMessage());
                }
            }
        });
        drinkPanel.getMinusButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                drinkPanel.getMinusButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                drinkPanel.getMinusButton().setCursor(Cursor.getDefaultCursor());
            }
        });
        drinkPanel.getPlusButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String text = drinkPanel.getQuantityField().getText();
                    qFTxt = Integer.parseInt(text);
                    qFTxt = qFTxt + 1;
                    drinkPanel.getQuantityField().setText(String.valueOf(qFTxt));
                } catch (InputMismatchException | NumberFormatException ex){
                    qFTxt = 1;
                    drinkPanel.getQuantityField().setText(String.valueOf(qFTxt));
                } catch (Exception ex){
                    qFTxt = 1;
                    drinkPanel.getQuantityField().setText(String.valueOf(qFTxt));
                    LOGGER.warn(ex.getMessage());
                }
            }
        });
        drinkPanel.getPlusButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                drinkPanel.getPlusButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                drinkPanel.getPlusButton().setCursor(Cursor.getDefaultCursor());
            }
        });
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
        drinkPanel.getQuantityField().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateButtonAppearance();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateButtonAppearance();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateButtonAppearance();
            }

            public void updateButtonAppearance(){
                String text = drinkPanel.getQuantityField().getText();
                if (text == null || text.isEmpty() || text.equals("0")) {
                    drinkPanel.changeButtonStatusToInactive(drinkPanel.getMinusButton());
                } else {
                    drinkPanel.changeButtonStatusToActive(drinkPanel.getMinusButton());
                }
            }
        });
    }
}
