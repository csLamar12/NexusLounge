package View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReportGenerator {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Main application frame
            JFrame mainFrame = new JFrame("Main Window");
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setSize(300, 200);
            mainFrame.setLayout(new FlowLayout());

            // Button to open the popup
            JButton openPopupButton = new JButton("Export Report");
            openPopupButton.addActionListener(e -> showExportReportPopup(mainFrame));
            mainFrame.add(openPopupButton);

            mainFrame.setVisible(true);
        });
    }

    private static void showExportReportPopup(JFrame parent) {
        // Create the dialog
        JDialog popup = new JDialog(parent, "Export Report", true);
        popup.setSize(400, 300);
        popup.setLayout(new GridBagLayout());
        popup.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // From Date
        JLabel fromLabel = new JLabel("From:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        popup.add(fromLabel, gbc);

        JTextField fromDateField = new JTextField(20);
        gbc.gridx = 1;
        popup.add(fromDateField, gbc);

        // To Date
        JLabel toLabel = new JLabel("To:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        popup.add(toLabel, gbc);

        JTextField toDateField = new JTextField(20);
        gbc.gridx = 1;
        popup.add(toDateField, gbc);

        // Drink Type ComboBox
        JLabel drinkTypeLabel = new JLabel("Drink Type:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        popup.add(drinkTypeLabel, gbc);

        String[] drinkTypes = {"All", "Alcoholic", "Non-Alcoholic"};
        JComboBox<String> drinkTypeComboBox = new JComboBox<>(drinkTypes);
        gbc.gridx = 1;
        popup.add(drinkTypeComboBox, gbc);

        // Drink Name ComboBox
        JLabel drinkNameLabel = new JLabel("Drink Name:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        popup.add(drinkNameLabel, gbc);

        // Placeholder for drinks from the database
        List<String> drinkNamesFromDB = Arrays.asList("All", "Mojito", "Lemonade", "Martini");
        JComboBox<String> drinkNameComboBox = new JComboBox<>(drinkNamesFromDB.toArray(new String[0]));
        gbc.gridx = 1;
        popup.add(drinkNameComboBox, gbc);

        // Export Button
        JButton exportButton = new JButton("Export");
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        exportButton.addActionListener(e -> {
            // Handle export logic here
            String fromDate = fromDateField.getText();
            String toDate = toDateField.getText();
            String drinkType = (String) drinkTypeComboBox.getSelectedItem();
            String drinkName = (String) drinkNameComboBox.getSelectedItem();
            ReportExport re = new ReportExport();
            // Sample data to simulate database output
            List<Object[]> sampleOrders = new ArrayList<>();
            sampleOrders.add(new Object[]{"001", "2024-11-01", "John Doe", "G001", "Jane Smith", "Mojito", 2});
            sampleOrders.add(new Object[]{"002", "2024-11-02", "Alice Johnson", "G002", "Peter Brown", "Martini", 1});
            sampleOrders.add(new Object[]{"003", "2024-11-03", "Bob White", "G003", "Emily Davis", "Lemonade", 3});
            sampleOrders.add(new Object[]{"004", "2024-11-04", "Carol Black", "G004", "Chris Green", "Margarita", 4});

            // Specify the file path to save the HTML report
            String filePath = "OrderReport.html";

            re.exportToHTML(sampleOrders, filePath, fromDate, toDate);

            JOptionPane.showMessageDialog(popup,
                    String.format("Exporting Report\nFrom: %s\nTo: %s\nType: %s\nName: %s",
                            fromDate, toDate, drinkType, drinkName));
        });
        popup.add(exportButton, gbc);

        popup.setLocationRelativeTo(parent);
        popup.setVisible(true);
    }
}

