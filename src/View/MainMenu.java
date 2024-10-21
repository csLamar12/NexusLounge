package View;

import Model.Drink;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lamar Haye
 */
public class MainMenu extends JFrame {
    private JPanel contentPane = new JPanel();
    private JScrollPane scrollPane = new JScrollPane(contentPane);
    private List<JPanel> alcoholicDP = new ArrayList<>();
    private List<JPanel> nonAlcoholicDP = new ArrayList<>();
    private List<JPanel> allDrinkPanels = new ArrayList<>();
    private GridBagConstraints c = new GridBagConstraints();


    public MainMenu() {
        setTitle("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        getContentPane().setBackground(Color.WHITE);
        setBackground(Color.RED);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        contentPane.setLayout(new GridBagLayout());
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane);
    }
    public void initWindow(){
        addComponents();
        validate();
    }

    public void addComponents(){
        c.insets = new Insets(10, 10, 10, 0);
        c.anchor = GridBagConstraints.CENTER;


        int x = 0;
        int y = 2;
        for (JPanel dp : getNonAlcoholicDP()) {
            c.gridx = x;
            c.gridy = y;
            contentPane.add(dp, c);
            x++;
            if (x==4){
                y++;
                x=0;
            }
        }

    }
    public void setAlcoholicPanels(List<Drink> drinks) {
        alcoholicDP.clear();
        for (Drink drink : drinks) {
            alcoholicDP.add(new DrinkPanel(drink));
        }
    }
    public void setNonAlcoholicPanels(List<Drink> drinks) {
        nonAlcoholicDP.clear();
        for (Drink drink : drinks) {
            nonAlcoholicDP.add(new DrinkPanel(drink));
        }
    }

    public List<JPanel> getAlcoholicDP() {
        return alcoholicDP;
    }

    public List<JPanel> getNonAlcoholicDP() {
        return nonAlcoholicDP;
    }
    public List<JPanel> getAllDrinkPanels() {
        allDrinkPanels.clear();
        allDrinkPanels.addAll(alcoholicDP);
        allDrinkPanels.addAll(nonAlcoholicDP);
        return allDrinkPanels;
    }
}
