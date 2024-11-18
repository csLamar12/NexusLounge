package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Lamar Haye
 */
public class LoginScreen extends JFrame {
    private static final String nLTBPath = "src/Resources/nXLogo.png";
    private ShadowedPanel popUpPanel = new ShadowedPanel();
    private PlaceHolderTF usernameTB, passwordTB;
    private JButton loginButton, guestButton;

    public LoginScreen() {
        setTitle("Nexus Lounge Login");
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screensize.width / 2, screensize.height / 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setBackground(Color.RED);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        popUpPanel.setBounds(0, 0, (screensize.width)/2, (screensize.height-30)/2);
        popUpPanel.setBackground(Color.WHITE);
        add(popUpPanel);

        // Logo
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image logoImage = tk.getImage(nLTBPath);
        ImageIcon logo = new ImageIcon(logoImage);
        JLabel logoLabel = new JLabel(logo);
        logoLabel.setBounds((popUpPanel.getWidth() - logo.getIconWidth()) / 2,130, logo.getIconWidth(), logo.getIconHeight());

        // Add components then repaint the panel
        popUpPanel.add(logoLabel);
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);
        loginPanel.setBackground(new Color(232, 232, 232));
        loginPanel.setBounds((popUpPanel.getWidth()-300)/2, 235, 300, 80);
        popUpPanel.add(loginPanel);


        // Username and Password Textfields
        usernameTB = new PlaceHolderTF("Username");
        usernameTB.setBounds((loginPanel.getWidth() - 260) / 2, 10, 150, 25);
        passwordTB = new PlaceHolderTF("Password");
        passwordTB.setBounds((loginPanel.getWidth() - 260) / 2, 45, 150, 25);

        // Continue as Guest & Login Button
        guestButton = new RoundedButton("Guests", new Color(232, 232, 232, 100), new Color(232, 232,232, 80));
        guestButton.setBounds((loginPanel.getWidth() + 60) / 2, 5, 105, 30);

        loginButton = new RoundedButton("Login", Color.RED, Color.RED);
        loginButton.setBounds((loginPanel.getWidth() + 60) / 2, 45, 105, 30);
        enterKeyListener();

        // Add loginPanel components
        loginPanel.add(usernameTB);
        loginPanel.add(passwordTB);
        loginPanel.add(guestButton);
        loginPanel.add(loginButton);

        popUpPanel.repaint();

    }

    public JButton getGuestButton() {
        return guestButton;
    }
    public JButton getLoginButton() {
        return loginButton;
    }

    public void enterKeyListener(){
        KeyAdapter enterKeyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    loginButton.doClick();
                }
            }
        };
        usernameTB.addKeyListener(enterKeyListener);
        passwordTB.addKeyListener(enterKeyListener);
        popUpPanel.addKeyListener(enterKeyListener);
        loginButton.addKeyListener(enterKeyListener);
        this.addKeyListener(enterKeyListener);

    }

    public void setLoginButtonListener(ActionListener listener){
        loginButton.addActionListener(listener);
    }
    public void setGuestButtonListener(ActionListener listener){
        guestButton.addActionListener(listener);
    }

    public String getUsername() {
        return usernameTB.getText();
    }

    public String getPassword() {
        return passwordTB.getText();
    }
    public void displayMessage(String message){
        JOptionPane.showMessageDialog(this, message);
    }

}
