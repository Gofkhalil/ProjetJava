package Presentation;

import javax.swing.*;

import metier.GestionUtilisateur;

import java.awt.*;

public class LoginPage extends JFrame {

    public LoginPage() {
        
        setTitle("Page de connexion");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        
        JPanel background = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(58, 123, 213),
                        0, getHeight(), new Color(58, 213, 178)
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        background.setLayout(null);
        add(background);

        
        JPanel card = new JPanel();
        card.setLayout(null);
        card.setBackground(Color.WHITE);
        card.setBounds(200, 80, 300, 320);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        background.add(card);

        
        ImageIcon logo = new ImageIcon(""); 
        Image scaledLogo = logo.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(scaledLogo));
        logoLabel.setBounds(110, -40, 80, 80);
        background.add(logoLabel);

        
        JLabel title = new JLabel("Bienvenue !");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(new Color(40, 40, 40));
        title.setBounds(100, 10, 150, 30);
        card.add(title);

       
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        emailLabel.setBounds(30, 60, 100, 25);
        card.add(emailLabel);

        
        JTextField emailField = new JTextField();
        StyleConfig.roundTextField(emailField);
        emailField.setBounds(30, 85, 230, 30);
        card.add(emailField);

       
        JLabel passwordLabel = new JLabel("Mot de passe");
        passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        passwordLabel.setBounds(30, 130, 100, 25);
        card.add(passwordLabel);

        
        JPasswordField passwordField = new JPasswordField();
        StyleConfig.roundTextField(passwordField);
        passwordField.setBounds(30, 155, 230, 30);
        card.add(passwordField);

       
        JButton loginButton = new JButton("Se connecter");
        StyleConfig.modernButton(loginButton);
        loginButton.setBounds(65, 220, 170, 40);
        card.add(loginButton);

       
        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            GestionUtilisateur.Login(email,password);
        });

        setVisible(true);
    }

}
