package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 *
 * @author Lamar Haye
 */
public final class PlaceHolderTF extends JTextField {

    public PlaceHolderTF(String placeHolder) {
        super(placeHolder);
        setBackground(new Color(201, 200, 200));
        setForeground(Color.BLACK);


        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if(getText().equals(placeHolder)) {
                    setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(getText().isEmpty()) {
                    setText(placeHolder);
                }
            }
        });
    }

}