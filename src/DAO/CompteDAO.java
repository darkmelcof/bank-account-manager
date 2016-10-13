package DAO;

import IHM.CreerCompte;
import IHM.Editer;
import MODELE.Compte;
import MODELE.LigneComptable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * @author Darkmelcof
 * Handle data from interface to DB
 */
public class CompteDAO extends DAO<Compte> {
    
    public CompteDAO(Connection con) {
        super(con);
    }
    
    /**
     * Creation d'un compte
     * @param cpt
     * @return true or false
     * Créer un compte dans la base de donnée
     */
    public boolean create(Compte cpt) {
        String str = "insert into compte values('" + cpt.getTypeCpte() + "','" + cpt.getVal_courante() + "','" + cpt.getVal_courante() + "','" + cpt.getNumeroCpte() + "')";
        try {
            DBManager.updateQuery(str);
        } catch (SQLException ex) {
            Logger.getLogger(CompteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public boolean delete(Compte cpt) {
        return false;
    }
    
    /**
     * Mise à jour du compte
     * @param cpt
     * @return true or false
     * Met à jour le compte dans la base de donnée
     */
    public boolean update(Compte cpt) {
        String str = "update compte set SOLDE = SOLDE +'" + cpt.getLigne().valeur + "' where NUMEROCPTE = " + cpt.getNumeroCpte() + "";
        try {
            DBManager.updateQuery(str);
        } catch (SQLException ex) {
            Logger.getLogger(CompteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    /**
     * Creation de compte 
     * @param id numero de compte
     * @return rien
     * Si le compte n'existe pas, on le creer
     */
    public Compte find(int id) {
        Compte compte = new Compte();
        ResultSet res;
        String str = "select NUMEROCPTE from compte where NUMEROCPTE = " + id + "";

        try {
            switch (id) {
                case 0:
                    JOptionPane.showMessageDialog(null, "Compte 0 est invalide");
                    break;
                default:
                    res = DBManager.executeQuery(str);
                    if (res.first()) {
                        JOptionPane.showMessageDialog(null, "Le compte " + id + " existe déjà");
                    } else {
                        // Creer le compte
                        new CreerCompte(String.valueOf(id)).setVisible(true);
                    }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return compte;
    }

    /**
     * Test d'existence du compte
     * @param id numero de compte
     * @return Compte
     * @throws SQLException
     */
    public Compte isExist(int id) throws SQLException {
        Compte cpt = new Compte();

        try {
            String str = "select TYPECPTE, SOLDE, NUMEROCPTE from compte where NUMEROCPTE =" + id + "";

            ResultSet res;
            res = DBManager.executeQuery(str);
            if (res.first()) {
                cpt.creerCompte(res.getString(1), res.getString(3), Double.parseDouble(res.getString(2)));
                return cpt;
            }
        } catch (SQLException e) {
            Logger.getLogger(CompteDAO.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Le compte " + id + " n'existe pas");

        }
        return null;
    }
    
    /**
     * Edition de compte
     * @param cpt
     * @throws SQLException 
     * Edite le compte s'il existe
     */
    public void Edition(Compte cpt) throws SQLException {
        ResultSet res;
        if (cpt != null) {
            try {
                //Recuperer les infos du comptes
                String str = "select c.NUMEROCPTE, TYPECPTE, VALEUR, MOTIF, MODE, SOLDE from compte c inner join lignecomptable lc on lc.NUMEROCPTE = c.NUMEROCPTE WHERE c.NUMEROCPTE = " + cpt.getNumeroCpte() + "";
                res = DBManager.executeQuery(str);

                // Parcours des résultats
                while (res.next()) {
                    cpt.getLi().add(new LigneComptable(Double.parseDouble(res.getString(3)), res.getString(4), res.getString(5)));
                    cpt.creerCompte(res.getString(2), res.getString(1), Double.parseDouble(res.getString("SOLDE")));
                }
                res.close();
                new Editer(cpt).setVisible(true);
            } catch (SQLException e) {
                Logger.getLogger(CompteDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Mauvais numéro de compte");
        }
    }
}
