package View;

import javax.swing.*;
import java.awt.*;

public final class RoundedButton extends JButton {
    private Color color1, color2;

    public RoundedButton(String text, Color color1, Color color2) {
        super(text);
        this.color1 = color1;
        this.color2 = color2;
        setBorderPainted(false);
        setContentAreaFilled(false);
    }
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setPaint(new GradientPaint(new Point(0,0), color1, new Point(0, getHeight()), color2.darker()));
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        g2d.dispose();
        super.paintComponent(g);
    }
}