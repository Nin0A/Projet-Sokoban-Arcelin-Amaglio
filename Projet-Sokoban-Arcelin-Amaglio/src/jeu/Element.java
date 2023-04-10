package jeu;

public class Element {

    //déclarations des attributs
    private int x;
    private int y;

    /**
     * Constructeur d'un élement
     * @param x abscisse
     * @param y ordonnée
     */
    public Element(int x, int y){
        this.x=x;
        this.y=y;
    }

    /**
     * getter de l'abscisse
     * @return valeur de x
     */
    public int getX(){
        return x;
    }

    /**
     * getter de l'ordonnée
     * @return valeur de y
     */
    public int getY(){
        return y;
    }

    /**
     * setter de l'abscisse
     * @param x maj de l'abscisse avec cette valeur
     */
    public void setX(int x){
        this.x=x;
    }

    /**
     * setter de l'ordonée
     * @param y maj de l'ordonnée avec cette valeur
     */
    public void setY(int y){
        this.y=y;
    }

}
