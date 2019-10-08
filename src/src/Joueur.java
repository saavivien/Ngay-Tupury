package src;

public class Joueur {

    private String nomjoueur;
    private int nbreCase;
    private int nbrePion;
    private int caseDouble;
    private int caseSimple;
    private int caseRecompense;
    private int caseRecompense1;
    private int caseSimplerecup;
    private int caseSimplerecup1;

    public Joueur(String nom) {
        this.nomjoueur = nom;
        this.nbrePion = 25;
        this.nbreCase = 5;
    }

    public String getNomjoueur() {
        return nomjoueur;
    }

    public void setNomjoueur(String nomjoueur) {
        this.nomjoueur = nomjoueur;
    }

    public int getNbrePion() {
        return nbrePion;
    }

    public void setNbrePion(int nbrePion) {
        this.nbrePion = nbrePion;
    }

    public int getNbreCase() {
        return nbreCase;
    }

    public void setNbreCase(int nbreCase) {
        this.nbreCase = nbreCase;
    }

    public int getCaseDouble() {
        return caseDouble;
    }

    public void setCaseDouble(int caseDouble) {
        this.caseDouble = caseDouble;
    }

    public int getCaseSimple() {
        return caseSimple;
    }

    public void setCaseSimple(int caseSimple) {
        this.caseSimple = caseSimple;
    }

    public int getCaseRecompense() {
        return caseRecompense;
    }

    public void setCaseRecompense(int caseRecompense) {
        this.caseRecompense = caseRecompense;
    }

    public int getCaseRecompense1() {
        return caseRecompense1;
    }

    public void setCaseRecompense1(int caseRecompense1) {
        this.caseRecompense1 = caseRecompense1;
    }

    public int getCaseSimplerecup() {
        return caseSimplerecup;
    }

    public void setCaseSimplerecup(int caseSimplerecup) {
        this.caseSimplerecup = caseSimplerecup;
    }

    public int getCaseSimplerecup1() {
        return caseSimplerecup1;
    }

    public void setCaseSimplerecup1(int caseSimplerecup1) {
        this.caseSimplerecup1 = caseSimplerecup1;
    }

    public void reSetcaseDoubleSimple(int n1, int n2, int n3) {
        int nb1 = n2 - n1;
        if (nb1 == 2 || nb1 == 3) {
            this.setCaseDouble(1);
        }
        if (nb1 == 4 || nb1 == 5) {
            this.setCaseDouble(2);
        }
        if (nb1 == 1 && n3 == 0) {
            this.setCaseSimple(1);
            this.setCaseSimplerecup1(1);
        } else if (nb1 == 1 && n3 == 1) {
            this.setCaseSimplerecup(1);
            this.setCaseSimplerecup1(1);
        }

    }

}
