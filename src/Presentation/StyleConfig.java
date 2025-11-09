package Presentation;

import javax.swing.*;
import java.awt.*;

public class StyleConfig {

    // Champs arrondis
    public static void roundTextField(JTextField field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        field.setBackground(new Color(245, 245, 245));
    }

    // Bouton stylé moderne
    public static void modernButton(JButton button) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(new Color(58, 123, 213));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));

        // Effet hover
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(72, 140, 230));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(58, 123, 213));
            }
        });
    }
}
