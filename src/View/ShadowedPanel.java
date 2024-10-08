package View;
import javax.swing.*;
import java.awt.*;

public final class ShadowedPanel extends JPanel {
    private static final int SHADOW_SIZE = 20;
    private static final Color SHADOW_COLOR = new Color(0,0,0,80);

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Draw shadow effect
        g2d.setColor(SHADOW_COLOR);
        int x = 100;
        for (int i = SHADOW_SIZE; i > 10; i--) {
            x-=4;
            // Draw shadow on the right side
            g2d.setColor(new Color(0,0,0,x));
            g2d.fillRect(getWidth() - i, SHADOW_SIZE+1, 1, getHeight() - SHADOW_SIZE - i-6);

            // Draw shadow on the bottom side
            g2d.setColor(new Color(0,0,0,x));
            g2d.fillRect(SHADOW_SIZE+7, getHeight()-6 - i, getWidth() - SHADOW_SIZE - i-7, 1);

        }

        // Draw the actual content (pop-up panel)
        g2d.setColor(getBackground());
        g2d.fillRect(0, 0, getWidth() - SHADOW_SIZE, getHeight()-5 - SHADOW_SIZE);
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Create a slight border around the panel
        g.setColor(new Color(0,0,0,80));
        g.drawRect(SHADOW_SIZE, SHADOW_SIZE-5, getWidth() - SHADOW_SIZE*2, getHeight() - SHADOW_SIZE*2);
    }
}