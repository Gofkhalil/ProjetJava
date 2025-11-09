package metier;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import DAO.Utilisateur;
import Presentation.DashboardProprietaire;
import util.HibernateUtil;

import javax.swing.JOptionPane;

public class GestionUtilisateur {

    public static void Login(String email, String password) {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            
            Query<Utilisateur> query = session.createQuery(
                "FROM Utilisateur WHERE email = :email AND motdepasse = :password",
                Utilisateur.class
            );
            
            
            		
            query.setParameter("email", email);
            query.setParameter("password", password);

            Utilisateur user = query.uniqueResult();

            tx.commit();

            if (user != null) {
                JOptionPane.showMessageDialog(null, 
                    "Bienvenue " + user.getNom() + " (" + user.getRole() + ") 🎉");
                
                
                if ("proprietaire".equalsIgnoreCase(user.getRole())) {
                    new DashboardProprietaire(user); 
                } else if ("reparateur".equalsIgnoreCase(user.getRole())) {
                    // new DashboardReparateur(user); // futur dashboard pour réparateur
                }
            } else {
                JOptionPane.showMessageDialog(null, 
                    "Identifiants incorrects ❌", 
                    "Erreur", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, 
                "Erreur lors de la connexion : " + e.getMessage());
        } finally {
            if (session != null) session.close();
        }
    }
}
