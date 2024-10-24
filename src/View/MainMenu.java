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
    private JPanel alcoholicTab, nonAlcoholicTab, tabPanel;
    private JLabel tabPanelLabel;


    public MainMenu() {
        setTitle("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        getContentPane().setBackground(Color.WHITE);
        setBackground(Color.RED);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        tabPanel = new JPanel();
        tabPanel.setSize(1000, 20);
        tabPanel.setBackground(Color.LIGHT_GRAY);
        tabPanel.setLayout(new GridBagLayout());
        add(tabPanel, BorderLayout.NORTH);

        contentPane.setLayout(new GridBagLayout());
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);
    }
    public void initWindow(){
        addComponents();
        validate();
    }

    public void addComponents(){
        c.insets = new Insets(0, 0, 0, 0);
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 0;
        c.gridwidth = 2;
        alcoholicTab = createTab("Alcoholic    ");
        tabPanel.add(alcoholicTab, c);

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        nonAlcoholicTab = createTab("Non-Alcoholic");
        tabPanel.add(nonAlcoholicTab, c);

        c.gridwidth = 1;
        c.insets = new Insets(10, 10, 10, 0);
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0.0;

        setNonAlcoholicTabActive();

    }

    public void displayMenu(List<JPanel> panels){
        contentPane.removeAll();
        int x = 0;
        int y = 2;
        for (JPanel dp : panels) {
            c.gridx = x;
            c.gridy = y;
            contentPane.add(dp, c);
            x++;
            if (x==4){
                y++;
                x=0;
            }
        }
        contentPane.revalidate();
        contentPane.repaint();
    }
    public JPanel createTab(String text){
        JPanel panel = new JPanel();
        tabPanelLabel = new JLabel(text);
        tabPanelLabel.setFont(new Font("Arial", Font.BOLD, 32));
        panel.setBackground(Color.WHITE);
        panel.add(tabPanelLabel);
        return panel;
    }
    public void setAlcoholicTabActive(){
        alcoholicTab.setBackground(Color.WHITE);
        alcoholicTab.setBorder(BorderFactory.createMatteBorder(0, 3, 3, 0, Color.BLACK));
        nonAlcoholicTab.setBackground(Color.GRAY);
        nonAlcoholicTab.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.GRAY));
        displayMenu(getAlcoholicDP());
    }
    public void setNonAlcoholicTabActive(){
        nonAlcoholicTab.setBackground(Color.WHITE);
        nonAlcoholicTab.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 3, Color.BLACK));
        alcoholicTab.setBackground(Color.GRAY);
        alcoholicTab.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.GRAY));
        displayMenu(getNonAlcoholicDP());
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

    public JPanel getTabPanel() {
        return tabPanel;
    }

    public void setTabPanel(JPanel tabPanel) {
        this.tabPanel = tabPanel;
    }

    public JPanel getNonAlcoholicTab() {
        return nonAlcoholicTab;
    }

    public void setNonAlcoholicTab(JPanel nonAlcoholicTab) {
        this.nonAlcoholicTab = nonAlcoholicTab;
    }

    public JPanel getAlcoholicTab() {
        return alcoholicTab;
    }

    public void setAlcoholicTab(JPanel alcoholicTab) {
        this.alcoholicTab = alcoholicTab;
    }

    public JLabel getTabPanelLabel() {
        return tabPanelLabel;
    }
}
