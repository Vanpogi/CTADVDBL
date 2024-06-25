package samsungcrm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI {

    private JFrame frame;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private CardLayout cardLayout;
    private JPanel panelContainer;
    private JPanel loginPanel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginGUI window = new LoginGUI();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public LoginGUI() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        cardLayout = new CardLayout();
        panelContainer = new JPanel(cardLayout);

        // Background Panel with Samsung IMG
        ImageIcon backgroundImageIcon = new ImageIcon("C:\\Users\\Bogs\\Downloads\\map_samsung.jpg");
        Image backgroundImage = backgroundImageIcon.getImage().getScaledInstance(600, -80, Image.SCALE_SMOOTH); // Scale image to width 300 pixels
        ImageIcon scaledBackgroundIcon = new ImageIcon(backgroundImage);
        JLabel background = new JLabel(scaledBackgroundIcon);
        background.setLayout(new GridBagLayout());

        loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setOpaque(false); // Make the login panel transparent

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10); // Padding

        // Title Label
        JLabel lblTitle = new JLabel("Customer Relationship Management");
        lblTitle.setForeground(Color.BLACK);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        loginPanel.add(lblTitle, constraints);

        // Username Label
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setForeground(Color.BLACK);
        lblUsername.setFont(new Font("Segoe UI", Font.BOLD, 14));
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.WEST;
        loginPanel.add(lblUsername, constraints);

        // Username TextField
        txtUsername = new JTextField(20);
        txtUsername.setFont(new Font("Segoe UI", Font.BOLD, 14));
        constraints.gridx = 1;
        constraints.gridy = 1;
        loginPanel.add(txtUsername, constraints);

        // Password Label
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 14));
        constraints.gridx = 0;
        constraints.gridy = 2;
        loginPanel.add(lblPassword, constraints);

        // Password Field
        txtPassword = new JPasswordField(20);
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        constraints.gridx = 1;
        constraints.gridy = 2;
        loginPanel.add(txtPassword, constraints);

        // Login Button
        JButton btnLogin = new JButton("Login");
        btnLogin.setBackground(new Color(0, 122, 204)); // Blue button color
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 1;
        loginPanel.add(btnLogin, constraints);

        background.add(loginPanel, new GridBagConstraints());

        panelContainer.add(background, "loginPanel");
        frame.getContentPane().add(panelContainer, BorderLayout.CENTER);
        cardLayout.show(panelContainer, "loginPanel");
    }

    private void login() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        
        if ("root".equals(username) && "CRM123".equals(password)) {
            JOptionPane.showMessageDialog(frame, "Login successful!");
            // Open the CustomerFeedbackGUI and close the login frame
            CustomerFeedbackGUI customerFeedbackGUI = new CustomerFeedbackGUI();
            customerFeedbackGUI.setVisible(true);
            frame.dispose();
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid username or password.");
        }
    }
}
