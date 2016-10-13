package MODELE;

import java.util.*;

public class Compte {

    // Les variables caracterisant un compte sont declarees comme variables de classe
    private String typeCpte;
    private double val_courante;
    public double taux;
    private String numeroCpte;
    private LigneComptable ligne;
    public int nbLigneReel;
    public static final int NBLigne = 10;

    private ArrayList<LigneComptable> li = new ArrayList();
    private static ArrayList<Compte> ListeCompte = new ArrayList();

    public Compte(String Type, String num, double taux_placement, double val, int nb_lignes) {
        typeCpte = Type;
        numeroCpte = num;
        taux = taux_placement;
        val_courante = val;
        nbLigneReel = nb_lignes;
    }

    public Compte() {
    }

    public Compte(String num, double valeur) {
        numeroCpte = num;
        val_courante = valeur;
    }

    /**
     * Creer un compte courant ou epargne
     *
     * @param type
     * @param numCompte
     * @param solde
     */
    public void creerCompte(String type, String numCompte, double solde) {
        switch (type) {
            case "courant":
                setTypeCpte("courant");
                setVal_courante(solde);
                setNumeroCpte(numCompte);
                ListeCompte.add(this);
                break;
            case "entreprise":
                setTypeCpte("entreprise");
                setVal_courante(solde);
                setNumeroCpte(numCompte);
                ListeCompte.add(this);
                break;
        }
    }

    /**
     * Creer un compte courant
     *
     * @param numCompte
     * @param solde
     */
    public void creerCpte(String numCompte, double solde) {
        setTypeCpte("courant");
        setVal_courante(solde);
        setNumeroCpte(numCompte);
    }

    public Compte getCompte(ArrayList<Compte> liste, String numCompte) {
        for (Compte c : liste) {
            if (c.getNumeroCpte().equals(numCompte)) {
                return c;
            }
        }
        return null;
    }

    public boolean isExist(ArrayList<Compte> liste, String numCompte) {
        for (Compte c : liste) {
            if (c.getNumeroCpte().equals(numCompte)) {
                return true;
            }
        }
        return false;
    }

    public void creerLigne(LigneComptable lc) {
        ligne = lc;
    }

    public void creerLigne(double val, String mot, String mod) {
        getLi().add(new LigneComptable(val, mot, mod));
    }

    /**
     * @return the typeCpte
     */
    public String getTypeCpte() {
        return typeCpte;
    }

    /**
     * @param typeCpte the typeCpte to set
     */
    public void setTypeCpte(String typeCpte) {
        this.typeCpte = typeCpte;
    }

    /**
     * @return the val_courante
     */
    public double getVal_courante() {
        return val_courante;
    }

    /**
     * @param val_courante the val_courante to set
     */
    public void setVal_courante(double val_courante) {
        this.val_courante = val_courante;
    }

    /**
     * @return the numeroCpte
     */
    public String getNumeroCpte() {
        return numeroCpte;
    }

    /**
     * @param numeroCpte the numeroCpte to set
     */
    public void setNumeroCpte(String numeroCpte) {
        this.numeroCpte = numeroCpte;
    }

    /**
     * @return the ListeCompte
     */
    public ArrayList<Compte> getListeCompte() {
        return ListeCompte;
    }

    /**
     * @param ListeCompte the ListeCompte to set
     */
    public void setListeCompte(ArrayList<Compte> ListeCompte) {
        this.ListeCompte = ListeCompte;
    }

    /**
     * @return the li
     */
    public ArrayList<LigneComptable> getLi() {
        return li;
    }

    /**
     * @return the ligne
     */
    public LigneComptable getLigne() {
        return ligne;
    }
}
