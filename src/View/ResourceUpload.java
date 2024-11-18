package View;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.swing.JPanel;

/**
 * The ResourceUpload class provides a simple GUI for uploading a drink image.
 * Users can select an image file from their file explorer, which will be displayed
 * on the screen after selection.
 */
public class ResourceUpload extends JPanel {

    private JButton uploadButton; // Button to trigger the file chooser
    private JLabel selectedFileLabel; //Label to show the selected file's name

    /**
     * Constructor to initialize the GUI components and layout.
     * Sets up the main frame and adds necessary UI elements.
     */
    public ResourceUpload() {
        setSize(100, 50);
        setLayout(new FlowLayout());
        setVisible(true);

        //Create a button that will open the file explorer when clicked
        uploadButton = new JButton("Click to Upload Drink Image");
        selectedFileLabel = new JLabel("No file selected");

        //Add an action listener to handle file selection
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFileChooser();
            }
        });

        //Add components to the frame
        add(uploadButton); add(selectedFileLabel);
    }

    /**
     * Opens a file chooser dialog allowing the user to select an image file.
     * Restricts the file types to image files (jpg, png, jpeg) only.
     * Updates the label with the selected file's name if an image is chosen.
     */
    private void openFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select an Image");

        fileChooser.setAcceptAllFileFilterUsed(false);//Restrict file types to images only
        fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg"));

        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            selectedFileLabel.setText("Selected: " + selectedFile.getName());
            saveFileToDirectory(88,selectedFile);
        } else if (userSelection == JFileChooser.CANCEL_OPTION) {
            selectedFileLabel.setText("File selection cancelled.");
        } else if (userSelection == JFileChooser.ERROR_OPTION) {
            selectedFileLabel.setText("An error occurred while selecting a file.");
        } else {
            selectedFileLabel.setText("No file selected");
        }
    }

    /**
     * Saves the selected file to the specified directory.
     * @param selectedFile The file selected by the user.
     */
    private void saveFileToDirectory(int drinkID, File selectedFile) {
        // Ensure the target directory exists
        File targetDirectory = new File("src/Resources/Drinks/");
        if (!targetDirectory.exists()) {
            targetDirectory.mkdirs();
        }

        // Define the target file path
        File targetFile = new File(targetDirectory,drinkID + ".png");

        try {
            // Copy the file to the target directory
            Files.copy(selectedFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            selectedFileLabel.setText("File saved to: " + targetFile.getAbsolutePath());
        } catch (IOException ex) {
            selectedFileLabel.setText("Error saving file: " + ex.getMessage());
        }
    }

    /**
     * Main method to launch the application.
     * Creates and displays the ResourceUpload frame.
     */
    public static void main(String[] args) {
        // Create and display the upload frame
        SwingUtilities.invokeLater(() -> {
            ResourceUpload uploadFrame = new ResourceUpload();
        });
    }
}
