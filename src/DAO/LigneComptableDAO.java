/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import IHM.CreerLigne;
import MODELE.LigneComptable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Darkmelcof
 */
public class LigneComptableDAO extends DAO<LigneComptable> {

    public LigneComptableDAO(Connection con) {
        super(con);
    }

    public boolean create(LigneComptable ligne) {
        String str;

        try {
            int lignes = this.nbLigne() + 1;
            str = "insert into lignecomptable values('" + ligne.numCompte + "','" + lignes + "','" + ligne.valeur + "','" + ligne.motif + "','" + ligne.mode + "')";
            DBManager.updateQuery(str);
        } catch (SQLException ex) {
            Logger.getLogger(LigneComptableDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public boolean delete(LigneComptable ligne) {
        return false;
    }

    public boolean update(LigneComptable ligne) {

        return true;
    }

    /**
     * Nombre de ligne Comptable
     * @return nombre
     * @throws SQLException
     * Retourne le nombre de ligne comptable dans la base de donnée
     */
    public int nbLigne() throws SQLException {
        String str = "Select * from lignecomptable";
        ResultSet res;
        res = DBManager.executeQuery(str);
        res.last();
        int nombre = res.getRow();
        return nombre;
    }

    public LigneComptable find(int id) {
        ResultSet res;
        LigneComptable lc = new LigneComptable();

        String str = "select NUMEROCPTE from compte where NUMEROCPTE = " + id + "";

        try {
            switch (id) {
                case 0:
                    JOptionPane.showMessageDialog(null, "Compte 0 est invalide");
                    break;
                default:
                    res = DBManager.executeQuery(str);
                    if (res.first()) {
                        new CreerLigne(String.valueOf(id)).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Le compte " + id + " n'existe pas");
                    }
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lc;
    }
}
