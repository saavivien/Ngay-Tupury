package src;

public class Jeu {

    private Joueur joueur1;
    private Joueur joueur2;
    int tabJeu[];
    boolean tabJeu1[];
    //getter et setter

    public Joueur getJoueur1() {
        return joueur1;
    }

    public void setJoueur1(Joueur joueur1) {
        this.joueur1 = joueur1;
    }

    public Joueur getJoueur2() {
        return joueur2;
    }

    public void setJoueur2(Joueur joueur2) {
        this.joueur2 = joueur2;
    }

    public int[] getTabJeu() {
        return tabJeu;
    }

    public void setTabJeu(int[] tabJeu) {
        this.tabJeu = tabJeu;
    }

    public boolean[] getTabJeu1() {
        return tabJeu1;
    }

    public void setTabJeu1(boolean[] tabJeu1) {
        this.tabJeu1 = tabJeu1;
    }

    public Jeu(Joueur j1, Joueur j2) {
//Réinitialisation des paramètres des joueurs pour un rédistribution

        tabJeu = new int[10];
        tabJeu1 = new boolean[10];
        joueur1 = new Joueur(j1.getNomjoueur());
        joueur2 = new Joueur(j2.getNomjoueur());
        joueur1.setNbreCase(j1.getNbreCase());
        joueur2.setNbreCase(j2.getNbreCase());
        joueur1.setNbrePion(0);
        joueur2.setNbrePion(0);
        joueur1.setCaseRecompense(j1.getCaseRecompense1());
        joueur2.setCaseRecompense(j2.getCaseRecompense1());
        joueur1.setCaseDouble(0);
        joueur1.setCaseSimple(0);
        joueur2.setCaseDouble(0);
        joueur1.setCaseSimplerecup(0);
        joueur2.setCaseSimplerecup(0);
        joueur1.setCaseSimplerecup1(0);
        joueur2.setCaseSimplerecup1(0);
        int nb11 = j1.getNbrePion() % 5;
        int nb12 = j1.getNbrePion() / 5;
        if (nb11 > 2) {
            joueur1.setNbreCase(nb12 + 1);
        } else {
            joueur1.setNbreCase(nb12);
        }

        int nb21 = j2.getNbrePion() % 5;
        int nb22 = j2.getNbrePion() / 5;
        if (nb21 > 2) {
            joueur2.setNbreCase(nb22 + 1);
        } else {
            joueur2.setNbreCase(nb22);
        }
        joueur1.reSetcaseDoubleSimple(j1.getNbreCase(), joueur1.getNbreCase(), j2.getCaseSimplerecup1());
        joueur2.reSetcaseDoubleSimple(j2.getNbreCase(), joueur2.getNbreCase(), j1.getCaseSimplerecup1());
        for (int i = 0; i < 10; i++) {
            tabJeu[i] = 5;
            tabJeu1[i] = true;

        }
//fin de la réinitialisation des joueur pour un nouveau jeu

    }

    // le joueur 1 a til le permi de jouer?
    public boolean permiDeJouer1(int i) {
        return (i < joueur1.getNbreCase() && tabJeu1[i] == true);
        // le joueur 2 a til le permi de jouer?	 
    }

    public boolean permiDeJouer2(int i) {
        return (i >= joueur1.getNbreCase() && tabJeu1[i] == true);

    }
    //le joueur 1 peut -il encore jouer?

    public boolean peuJoueur1() {
        int bool = 0;
        for (int i = 0; i < joueur1.getNbreCase(); i++) {
            if (tabJeu1[i] == true && tabJeu[i] > 1) {
                bool = 1;
            }
        }
        return bool == 1;
    }

    //le joueur 2 peut -il encore jouer?
    public boolean peuJoueur2() {
        int bool = 0;
        for (int i = joueur1.getNbreCase(); i < 10; i++) {
            if (tabJeu1[i] == true && tabJeu[i] > 1) {
                bool = 1;
            }
        }
        return bool == 1;
    }
    //Jeu simple

    public void jeuSimple1(Joueur j, int n) {
        if (permiDeJouer1(n) && this.tabJeu[n] > 8 && j.getCaseSimple() > 0) {
            this.tabJeu[n] = this.tabJeu[n] - 7;
            j.setNbrePion(j.getNbrePion() + 7);
            distribuer(n, j);
            j.setCaseSimple(j.getCaseSimple() - 1);
        }
    }

    public void jeuSimple2(Joueur j, int n) {
        if (permiDeJouer2(n) && this.tabJeu[n] > 8 && j.getCaseSimple() > 0) {
            this.tabJeu[n] = this.tabJeu[n] - 7;
            j.setNbrePion(j.getNbrePion() + 7);
            distribuer(n, j);
            j.setCaseSimple(j.getCaseSimple() - 1);
        }
    }
    //jeu recupération

    public void jeuSimpleRecup1(Joueur j, int n) {
        if (permiDeJouer1(n) && this.tabJeu[n] > 6 && j.getCaseSimplerecup() > 0) {
            this.tabJeu[n] = this.tabJeu[n] - 5;
            j.setNbrePion(j.getNbrePion() + 5);
            distribuer(n, j);
            j.setCaseSimplerecup(j.getCaseSimplerecup() - 1);
        }
    }

    public void jeuSimpleRecup2(Joueur j, int n) {
        if (permiDeJouer2(n) && this.tabJeu[n] > 6 && j.getCaseSimplerecup() > 0) {
            this.tabJeu[n] = this.tabJeu[n] - 5;
            j.setNbrePion(j.getNbrePion() + 5);
            distribuer(n, j);
            j.setCaseSimplerecup(j.getCaseSimplerecup() - 1);
        }
    }
    //Bloquer une case pour en récupérer le contenu à la fin du jeu

    public void jeuDouble1(Joueur j, int n) {
        if (permiDeJouer1(n) && j.getCaseDouble() > 0) {
            this.tabJeu1[n] = false;
            j.setCaseDouble(j.getCaseDouble() - 1);
            j.setCaseRecompense1(j.getCaseRecompense1() + 1);
        }

    }

    public void jeuDouble2(Joueur j, int n) {
        if (permiDeJouer2(n) && j.getCaseDouble() > 0) {
            this.tabJeu1[n] = false;
            j.setCaseDouble(j.getCaseDouble() - 1);
            j.setCaseRecompense1(j.getCaseRecompense1() + 1);
        }
    }
    //Une case de récompense

    public void jeuRecompense1(Joueur j, int n) {
        if (permiDeJouer1(n)) {
            j.setNbrePion(j.getNbrePion() + 5);
            this.tabJeu[n] = this.tabJeu[n] - 5;
            j.setCaseRecompense(j.getCaseRecompense() - 1);
        }
    }

    public void jeuRecompense2(Joueur j, int n) {
        if (permiDeJouer2(n)) {
            j.setNbrePion(j.getNbrePion() + 5);
            this.tabJeu[n] = this.tabJeu[n] - 5;
            j.setCaseRecompense(j.getCaseRecompense() - 1);
        }
    }
    //on teste pour voire si on peut encore jouer

    public boolean jeuTermine() {
        return (!this.peuJoueur1() && !this.peuJoueur2());
    }
    //on finalise le jeu

    public void finaliserJeu() {
        if (this.jeuTermine()) {
            for (int i = 0; i < this.joueur1.getNbreCase(); i++) {
                this.joueur1.setNbrePion(this.joueur1.getNbrePion() + this.tabJeu[i]);
            }
            for (int i = this.joueur1.getNbreCase(); i < 10; i++) {
                this.joueur2.setNbrePion(this.joueur2.getNbrePion() + this.tabJeu[i]);
            }
        }
    }

    //on ne distribu que si le nombre de pions est superieur à 1
    public boolean peutDistribuer(int numCase) {
        return tabJeu[numCase] > 1 && tabJeu1[numCase] == true;
        //On distribu les pions d'une cellule	   
    }

    public void distribuer(int numCase, Joueur j) {
        if (peutDistribuer(numCase)) {
            int nbPion = tabJeu[numCase];
            tabJeu[numCase] = 0;
            for (int i = 1; i <= nbPion; i++) {
                tabJeu[(numCase + i) % 10]++;
            }
            //gain simple et une partie des gain cumulés gérés
            if (tabJeu[(numCase + nbPion) % 10] == 2 || tabJeu[(numCase + nbPion) % 10] == 4) {
                j.setNbrePion(j.getNbrePion() + tabJeu[(numCase + nbPion) % 10]);
                tabJeu[(numCase + nbPion) % 10] = 0;
                if (((numCase + nbPion) % 10) != 4 && ((numCase + nbPion) % 10) != 9) {
                    int e = 1;
                    while ((tabJeu[(e + numCase + nbPion) % 10] == 2 || tabJeu[(e + numCase + nbPion) % 10] == 4) && (((e + numCase + nbPion) % 10) != 5 && ((e + numCase + nbPion) % 10) != 0)) {
                        j.setNbrePion(j.getNbrePion() + tabJeu[e + (numCase + nbPion) % 10]);
                        tabJeu[e + (numCase + nbPion) % 10] = 0;
                        e++;
                    }
                }
            } else if (((numCase + nbPion) % 10) != 4 && ((numCase + nbPion) % 10) != 9) {
                int e = 1;
                while ((tabJeu[(e + numCase + nbPion) % 10] == 2 || tabJeu[(e + numCase + nbPion) % 10] == 4) && (((e + numCase + nbPion) % 10) != 5 && ((e + numCase + nbPion) % 10) != 0)) {
                    j.setNbrePion(j.getNbrePion() + tabJeu[e + (numCase + nbPion) % 10]);
                    tabJeu[e + (numCase + nbPion) % 10] = 0;
                    e++;
                }
            }

        }
    }

    public static void main(String[] args) {

        Joueur j1 = new Joueur("saa");
        Joueur j2 = new Joueur("vivien");
        Jeu jeu = new Jeu(j1, j2);
        //jeu.tabJeu[]={2,1,1,2,2,2,2,5,5,6};
        jeu.tabJeu[0] = 2;
        jeu.tabJeu[1] = 1;
        jeu.tabJeu[2] = 1;
        jeu.tabJeu[3] = 2;
        jeu.tabJeu[4] = 2;
        jeu.tabJeu[5] = 2;
        jeu.tabJeu[6] = 2;
        jeu.tabJeu[7] = 3;
        jeu.distribuer(0, j1);
        for (int i = 0; i < 10; i++) {

            System.out.println(jeu.tabJeu[i]);

        }
        System.out.println(j1.getNbrePion());
    }
}
