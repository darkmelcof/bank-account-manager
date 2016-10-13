package MODELE;

import java.util.*;

public class LigneComptable {

    public int numCompte;
    public double valeur;
    public String date;
    public String motif;
    public String mode;

    public LigneComptable(double val, String mot, String mod, int num) {
        valeur = val;
        motif = mot;
        mode = mod;
        numCompte = num;
    }

    public LigneComptable(double val, String mot, String mod) {
        valeur = val;
        motif = mot;
        mode = mod;
    }

    public LigneComptable() {
    }

    public void afficherLigne() {
        if (valeur < 0) {
            System.out.print("Debiter : " + valeur);
        } else {
            System.out.print("Crediter : " + valeur);
        }
        System.out.println(" le : " + date + " motif  : " + motif + " mode : " + mode);
    }

    public double quelleValeur() {
        return valeur;
    }
}
