package src;

public class Cellule {

    Joueur j;
    boolean enable;
    int nbrPionCel;

    public Cellule(Joueur j) {
        this.j = j;
        this.enable = true;
        this.nbrPionCel = 5;
    }

    public Joueur getJ() {
        return j;
    }

    public void setJ(Joueur j) {
        this.j = j;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public int getNbrPionCel() {
        return nbrPionCel;
    }

    public void setNbrPionCel(int nbrPionCel) {
        this.nbrPionCel = nbrPionCel;
    }

}
