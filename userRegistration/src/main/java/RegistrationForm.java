// PING WENCHAO 226969

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class RegistrationForm extends JDialog {
    // GUI Components bound from the IntelliJ UI Designer
    private JPanel panelRegister;
    private JTextField tfName;
    private JTextField tfEmail;
    private JTextField tfPhone;
    private JTextField tfAddress;
    private JPasswordField pwdPassword;
    private JPasswordField pwdConfirmPwd;
    private JButton jbtRegister;
    private JButton jbtCancel;

    // Object to hold the registered user's data
    public User user;

    public RegistrationForm(JFrame parent) {
        super(parent);

        // Initialize the Dialog window properties
        setTitle("Create a new account");
        setContentPane(panelRegister);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Action listener for the Register button
        jbtRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });

        // Action listener for the Cancel button
        jbtCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the window without registering
            }
        });
    }

    /**
     * Retrieves data from the text fields, validates the input,
     * and attempts to save the user to the database.
     */
    private void registerUser() {
        // Retrieve user inputs from the text fields
        String name = tfName.getText();
        String email = tfEmail.getText();
        String phone = tfPhone.getText();
        String address = tfAddress.getText();
        String password = String.valueOf(pwdPassword.getPassword());
        String confirmPassword = String.valueOf(pwdConfirmPwd.getPassword());

        // Validation 1: Ensure no fields are left empty
        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter all fields", "Try Again", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validation 2: Ensure the password and confirm password match
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Confirm Password does not match", "Try Again", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Proceed to save the user to the Oracle database
        user = addUserToDatabase(name, email, phone, address, password);

        // Close the form if the registration was successful
        if (user != null) {
            dispose();
        }
    }

    /**
     * Connects to the Oracle database and executes the INSERT statement.
     */
    private User addUserToDatabase(String name, String email, String phone, String address, String password) {
        User user = null;

        // Oracle Database connection credentials
        final String DB_URL = "jdbc:oracle:thin:@fsktmdbora.upm.edu.my:1521:FSKTM";
        final String USERNAME = "D226969";
        final String PASSWORD = "226969";

        try {
            // Establish the database connection
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stat = conn.createStatement();

            // Prepare the SQL INSERT statement with parameterized query to prevent SQL injection
            String sql = "INSERT INTO users (name, email, phone, address, password) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, password);

            // Execute the update and check if rows were affected
            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                // Populate the User object upon successful insertion
                user = new User();
                user.name = name;
                user.email = email;
                user.phone = phone;
                user.address = address;
                user.password = password;
            }

            // Clean up database resources
            stat.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    /**
     * Main entry point to launch the Registration Form GUI.
     */
    public static void main(String[] args) {
        // Initialize and display the GUI
        RegistrationForm myForm = new RegistrationForm(null);
        myForm.setVisible(true);

        // Print the outcome to the console after the window closes
        User user = myForm.user;
        if (user != null) {
            System.out.println("Successful registration of: " + user.name);
        } else {
            System.out.println("Registration canceled");
        }
    }
}