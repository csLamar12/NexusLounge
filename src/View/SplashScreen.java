package View;

import javax.swing.*;
import java.awt.*;


/**
 *
 * @author Lamar Haye
 */
public class SplashScreen extends JFrame {

    public SplashScreen() {
        JFrame splash = new JFrame();
        splash.setSize(800, 600);
        splash.setUndecorated(true);
        splash.setBackground(new Color(0,0,0,0));

        ImageIcon splashIcon = new ImageIcon("src/Resources/Nexus Lounge TranspB.png");
        JLabel splashLabel = new JLabel(splashIcon);

        splash.getContentPane().add(splashLabel);

        splash.pack();
        splash.setLocationRelativeTo(null);
        splash.setVisible(true);

        try{
            Thread.sleep(3000);
            splash.dispose();
            splash.setVisible(false);
        } catch(InterruptedException e){
            e.printStackTrace();
        }


    }
}
