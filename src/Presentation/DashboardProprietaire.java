package Presentation;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import org.hibernate.Session;
import org.hibernate.Transaction;

import DAO.Reparateur;
import DAO.Reparation;
import DAO.Utilisateur;
import util.HibernateUtil;

public class DashboardProprietaire extends JFrame {

    private JPanel contentPanel;
    private Utilisateur user;

    public DashboardProprietaire(Utilisateur user) {
        this.user = user;
        initUI();
    }

    private void initUI() {
        setTitle("Dashboard Propriétaire - " + user.getNom());
        setSize(1100, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ------------------ Sidebar ------------------
     // Sidebar
        JPanel sideBar = new JPanel();
        sideBar.setBackground(new Color(25, 42, 86));
        sideBar.setPreferredSize(new Dimension(250, getHeight()));
        sideBar.setLayout(new GridLayout(0, 1, 0, 15)); // lignes flexibles
        sideBar.setBorder(new EmptyBorder(20, 10, 20, 10));

        JButton btnAjouterReparateur = createSidebarButton("Ajouter un réparateur");
        JButton btnNouvelleReparation = createSidebarButton("Nouvelle réparation");
        JButton btnCaisseProprietaire = createSidebarButton("Ma caisse");
        JButton btnCaisseReparateurs = createSidebarButton("Caisse des réparateurs");
        JButton btnEmprunt = createSidebarButton("Emprunt");
        JButton btnAfficherReparations = createSidebarButton("Afficher les réparations");
        JButton btnDeconnexion = createSidebarButton("Déconnexion");

        JButton[] buttons = {btnAjouterReparateur, btnNouvelleReparation, btnCaisseProprietaire,
                             btnCaisseReparateurs, btnEmprunt, btnAfficherReparations, btnDeconnexion};

        for (JButton btn : buttons) sideBar.add(btn);
        add(sideBar, BorderLayout.WEST);


        // ------------------ Content Panel ------------------
        contentPanel = new JPanel();
        contentPanel.setBackground(new Color(245, 245, 245));
        contentPanel.setLayout(new BorderLayout(20, 20));
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        add(contentPanel, BorderLayout.CENTER);


        // ------------------ Actions ------------------
        btnAjouterReparateur.addActionListener(e -> showAjouterReparateur());
        btnNouvelleReparation.addActionListener(e -> showNouvelleReparation());
        btnCaisseProprietaire.addActionListener(e -> showCaisseProprietaire());
        btnCaisseReparateurs.addActionListener(e -> showCaisseReparateurs());
        btnEmprunt.addActionListener(e -> showEmprunt());
        btnAfficherReparations.addActionListener(e -> showReparations());
        btnDeconnexion.addActionListener(e -> {
            dispose();
            new LoginPage();
        });

        
        showWelcome();
        setVisible(true);
    }

    // ------------------ Créer bouton stylé ------------------
    private JButton createSidebarButton(String text) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setBackground(new Color(58, 123, 213));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(38, 97, 175));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(58, 123, 213));
            }
        });
        return btn;
    }


    // ------------------ Pages ------------------
    private void showWelcome() {
        contentPanel.removeAll();
        JPanel welcomePanel = new JPanel(new GridBagLayout());
        welcomePanel.setBackground(Color.WHITE);
        welcomePanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(58,123,213), 2),
            BorderFactory.createEmptyBorder(30,30,30,30)
        ));

        JLabel welcome = new JLabel("Bienvenue, " + user.getNom() + " !");
        welcome.setFont(new Font("Segoe UI", Font.BOLD, 28));
        welcome.setForeground(new Color(25, 42, 86));

        welcomePanel.add(welcome);
        contentPanel.add(welcomePanel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }


    private void showAjouterReparateur() {
        contentPanel.removeAll();
        JPanel form = new JPanel();
        form.setBackground(Color.WHITE);
        form.setLayout(new GridBagLayout());
        form.setBorder(BorderFactory.createTitledBorder("Ajouter un nouveau réparateur"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nomLabel = new JLabel("Nom:");
        JTextField nomField = new JTextField(15);
        JLabel prenomLabel = new JLabel("Prénom:");
        JTextField prenomField = new JTextField(15);
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(15);
        JLabel mdpLabel = new JLabel("Mot de passe:");
        JPasswordField mdpField = new JPasswordField(15);
        JButton submit = new JButton("Ajouter");

        gbc.gridx = 0; gbc.gridy = 0; form.add(nomLabel, gbc);
        gbc.gridx = 1; form.add(nomField, gbc);
        gbc.gridx = 0; gbc.gridy = 1; form.add(prenomLabel, gbc);
        gbc.gridx = 1; form.add(prenomField, gbc);
        gbc.gridx = 0; gbc.gridy = 2; form.add(emailLabel, gbc);
        gbc.gridx = 1; form.add(emailField, gbc);
        gbc.gridx = 0; gbc.gridy = 3; form.add(mdpLabel, gbc);
        gbc.gridx = 1; form.add(mdpField, gbc);
        gbc.gridx = 1; gbc.gridy = 4; form.add(submit, gbc);

        submit.addActionListener(e -> {
            String nom = nomField.getText();
            String prenom = prenomField.getText();
            String email = emailField.getText();
            String mdp = new String(mdpField.getPassword());

            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            try {
                Reparateur reparateur = new Reparateur(nom, email,prenom, mdp);
                session.save(reparateur);
                tx.commit();
                JOptionPane.showMessageDialog(null, "Réparateur ajouté avec succès !");
            } catch (Exception ex) {
                if(tx != null) tx.rollback();
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout !");
            } finally {
                session.close();
            }
        });

        contentPanel.add(form, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showNouvelleReparation() {
        contentPanel.removeAll();

        JPanel form = new JPanel();
        form.setLayout(new GridBagLayout());
        form.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ---------------- Champs principaux ----------------
        gbc.gridx = 0;
        gbc.gridy = 0;
        form.add(new JLabel("Type d'appareil:"), gbc);
        gbc.gridx = 1;
        JTextField typeAppareilField = new JTextField();
        form.add(typeAppareilField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        form.add(new JLabel("Prix:"), gbc);
        gbc.gridx = 1;
        JTextField prixField = new JTextField();
        form.add(prixField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        form.add(new JLabel("État:"), gbc);
        gbc.gridx = 1;
        String[] etats = {"Pas encore commencé", "En cours", "Prêt"};
        JComboBox<String> etatCombo = new JComboBox<>(etats);
        form.add(etatCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        form.add(new JLabel("Date début:"), gbc);
        gbc.gridx = 1;
        JTextField dateDebutField = new JTextField("yyyy-mm-dd");
        form.add(dateDebutField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        form.add(new JLabel("Date fin estimée:"), gbc);
        gbc.gridx = 1;
        JTextField dateFinEstimeeField = new JTextField("yyyy-mm-dd");
        form.add(dateFinEstimeeField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        form.add(new JLabel("Date livraison:"), gbc);
        gbc.gridx = 1;
        JTextField dateLivraisonField = new JTextField("yyyy-mm-dd");
        form.add(dateLivraisonField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        form.add(new JLabel("Code de suivi client:"), gbc);
        gbc.gridx = 1;
        JTextField codeSuiviField = new JTextField();
        form.add(codeSuiviField, gbc);

        // ---------------- Sous-formulaire client ----------------
        gbc.gridx = 0;
        gbc.gridy++;
        form.add(new JLabel("Nom du client:"), gbc);
        gbc.gridx = 1;
        JTextField clientNomField = new JTextField();
        form.add(clientNomField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        form.add(new JLabel("Téléphone client:"), gbc);
        gbc.gridx = 1;
        JTextField clientTelField = new JTextField();
        form.add(clientTelField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        form.add(new JLabel("Photo client (URL):"), gbc);
        gbc.gridx = 1;
        JTextField clientPhotoField = new JTextField();
        form.add(clientPhotoField, gbc);

        // ---------------- Bouton Enregistrer ----------------
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton submit = new JButton("Enregistrer");
        submit.setBackground(new Color(58, 123, 213));
        submit.setForeground(Color.WHITE);
        submit.setFont(new Font("Segoe UI", Font.BOLD, 14));

        submit.addActionListener(e -> {
            String typeAppareil = typeAppareilField.getText();
            String prix = prixField.getText();
            String etat = (String) etatCombo.getSelectedItem();
            String dateDebut = dateDebutField.getText();
            String dateFinEstimee = dateFinEstimeeField.getText();
            String dateLivraison = dateLivraisonField.getText();
            String codeSuivi = codeSuiviField.getText();
            String clientNom = clientNomField.getText();
            String clientTel = clientTelField.getText();
            String clientPhoto = clientPhotoField.getText();

            // Ici tu peux appeler ta méthode Hibernate pour sauvegarder la réparation
            JOptionPane.showMessageDialog(null,
                    "Réparation enregistrée (simulé)\n"
                    + "Type: " + typeAppareil + "\n"
                    + "Prix: " + prix + "\n"
                    + "État: " + etat + "\n"
                    + "Client: " + clientNom + " (" + clientTel + ")");
        });

        form.add(submit, gbc);

        contentPanel.add(form, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }


    private void showCaisseProprietaire() {
        contentPanel.removeAll();
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BorderLayout());
        JLabel label = new JLabel("Caisse propriétaire : afficher transactions ici");
        label.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);
        contentPanel.add(panel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showCaisseReparateurs() {
        contentPanel.removeAll();
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BorderLayout());
        JLabel label = new JLabel("Caisse des réparateurs : afficher toutes les caisses ici");
        label.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);
        contentPanel.add(panel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showEmprunt() {
        contentPanel.removeAll();
        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Color.WHITE);
        form.setBorder(BorderFactory.createTitledBorder("Effectuer un emprunt"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel reparateurLabel = new JLabel("Réparateur emprunté (ID):");
        JTextField reparateurField = new JTextField(15);
        JLabel montantLabel = new JLabel("Montant emprunté:");
        JTextField montantField = new JTextField(15);
        JButton submit = new JButton("Valider l'emprunt");

        gbc.gridx = 0; gbc.gridy = 0; form.add(reparateurLabel, gbc);
        gbc.gridx = 1; form.add(reparateurField, gbc);
        gbc.gridx = 0; gbc.gridy = 1; form.add(montantLabel, gbc);
        gbc.gridx = 1; form.add(montantField, gbc);
        gbc.gridx = 1; gbc.gridy = 2; form.add(submit, gbc);

        submit.addActionListener(e -> JOptionPane.showMessageDialog(null, "Emprunt effectué (simulé)"));

        contentPanel.add(form, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    private void showReparations() {
        contentPanel.removeAll();

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder("Mes réparations"));

        // Colonnes du tableau
        String[] columns = {"ID", "Type", "Prix", "État", "Client", "Date début", "Date fin estimée", "Date livraison", "Action"};

        Object[][] data;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Récupérer les réparations de l'utilisateur connecté
            java.util.List<Reparation> reparations = session.createQuery(
                "FROM DAO.Reparation r WHERE r.utilisateur.id = :userId", Reparation.class)
                .setParameter("userId", user.getId())
                .list();

            data = new Object[reparations.size()][columns.length];
            for (int i = 0; i < reparations.size(); i++) {
                Reparation r = reparations.get(i);
                data[i][0] = r.getId();
                data[i][1] = r.getTypeAppareil();
                data[i][2] = r.getPrix();
                data[i][3] = r.getEtat();
                data[i][4] = r.getClient().getNom();
                data[i][5] = r.getDateDebut();
                data[i][6] = r.getDateFinEstimee();
                data[i][7] = r.getDateLivraison();
                data[i][8] = "Modifier"; // bouton affiché mais non fonctionnel pour l'instant
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors de la récupération des réparations !");
            data = new Object[0][columns.length];
        }

        // Créer le tableau
        JTable table = new JTable(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 8; // seulement la colonne "Action" est éditable (bouton)
            }
        };

        // Ajouter un JScrollPane pour le tableau
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        contentPanel.add(panel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

}
