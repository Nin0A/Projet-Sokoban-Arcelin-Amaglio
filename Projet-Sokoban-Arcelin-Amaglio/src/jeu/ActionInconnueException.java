package jeu;

public class ActionInconnueException extends Exception{

    /**
     * Constructeur
     * @param s correspond à l'erreur (soit une action inconnue)
     */
    ActionInconnueException(String s){
        super(s);
    }
}
