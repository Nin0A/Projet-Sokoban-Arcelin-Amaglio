package jeu;

public class Labyrinthe {

    //déclarations des attributs
    public final static char MUR = '#';
    public final static char CAISSE = '$';
    public final static char PJ = '@';
    public final static char DEPOT = '.';
    public final static char VIDE = ' ';
    private boolean murs [][];

    /**
     * Constructeur d'un labirynthe
     * @param tableau correspond à un tableau de boolean en deux dimensions
     * pour construire le labirynthe (true = mur | false = pas de mur)
     */
    public Labyrinthe(boolean[][] tableau){
        this.murs=tableau;
    }

    /**
     * méthode etreMur
     * @param x abscisse
     * @param y ordonnée
     * @return true si il y a un mur aux coordonnées (x,y)
     */
    public boolean etreMur(int x, int y){
       return this.murs[x][y];
    }

    /**
     * méthode getLargeurMax
     * @return un entier correpondant à la largeur maximum du labyrinthe
     */
    public int getLargeurMax() {
        return this.murs.length;
    }

    /**
     * méthode getHauteurMax
     * @return un entier correpondant à la hauteur maximum du labyrinthe
     */
    public int getHauteurMax(){
        int res=0;
        for(int i=0;i<this.murs.length;i++){
            int tmp=this.murs[i].length;
            if(res<tmp){
                res=tmp;
            }
        }
        return res;
    }
}
