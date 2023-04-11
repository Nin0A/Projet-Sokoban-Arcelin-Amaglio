package graphisme;

import jeu.Chargement;
import jeu.FichierIncorrectException;
import jeu.Jeu;

import java.io.IOException;

public class MainTest {
    public static void main(String[] args) throws FichierIncorrectException, IOException, Exception{
        // charge un jeu
        Jeu j = Chargement.chargerJeu("laby/laby0.txt");
        Application appli = new Application(j);
    }
}
