package jeu;

public class FichierIncorrectException extends Exception {

    /**
     * Constructeur
     * @param s correspond à l'erreur
     */
    public FichierIncorrectException(String s) {
        super(s);
    }
}
