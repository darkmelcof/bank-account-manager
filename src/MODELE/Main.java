package MODELE;

import IHM.FenetrePrincipal;

public class Main {

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FenetrePrincipal().setVisible(true);
            }
        });
    }

}
