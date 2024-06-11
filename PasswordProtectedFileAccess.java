import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class PasswordProtectedFileAccess extends JFrame {

    private JPasswordField setPasswordField;
    private JButton setButton;
    private JPasswordField accessPasswordField;
    private JButton accessButton;

    private File textFile;

    public PasswordProtectedFileAccess() {
        super("Password Protected File Access");

        // Initialize the components for setting the password
        setPasswordField = new JPasswordField(20);
        setButton = new JButton("Set Password");

        // Add action listener to the set button
        setButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setPassword();
            }
        });

        // Initialize the components for accessing the file
        accessPasswordField = new JPasswordField(20);
        accessButton = new JButton("Access File");

        // Add action listener to the access button
        accessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                accessFile();
            }
        });

        // Set the layout
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Add the components to the frame for setting the password
        add(new JLabel("Set Password:"));
        add(setPasswordField);
        add(setButton);

        // Add the components to the frame for accessing the file
        add(new JLabel("Access Password:"));
        add(accessPasswordField);
        add(accessButton);

        // Set the default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the frame size and visibility
        setSize(300, 200);
        setVisible(true);
    }

    private void setPassword() {
        String setPassword = new String(setPasswordField.getPassword());
        if (setPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a password.");
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            textFile = fileChooser.getSelectedFile();
            try {
                // Write the password to the file
                BufferedWriter writer = new BufferedWriter(new FileWriter(textFile));
                writer.write(setPassword);
                writer.close();
                JOptionPane.showMessageDialog(this, "Password set successfully.");
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "An error occurred while setting the password.");
            }
        }
    }

    private void accessFile() {
        String accessPassword = new String(accessPasswordField.getPassword());
        if (accessPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the password.");
            return;
        }

        if (textFile == null) {
            JOptionPane.showMessageDialog(this, "Please set a password and select a file first.");
            return;
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(textFile));
            String storedPassword = reader.readLine();
            reader.close();

            if (accessPassword.equals(storedPassword)) {
                // Allow access to the file
                JOptionPane.showMessageDialog(this, "File accessed successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect password. Access denied.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while accessing the file.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PasswordProtectedFileAccess();
            }
        });
    }
}
