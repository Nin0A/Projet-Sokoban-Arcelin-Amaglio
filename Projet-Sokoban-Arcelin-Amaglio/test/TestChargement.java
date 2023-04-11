import static org.junit.jupiter.api.Assertions.assertEquals;

import jeu.Chargement;
import jeu.Jeu;
import org.junit.jupiter.api.Test;

public class TestChargement {
    @Test
    public void test_getChar() throws Exception {
        Jeu jeu = Chargement.chargerJeu("laby/laby_test.txt");

        char c1 = jeu.getChar(10, 9);//mur
        char c2 = jeu.getChar(17, 7);//depot
        char c3 = jeu.getChar(3, 6);//vide
        char c4 = jeu.getChar(11, 8);//personnage
        char c5 = jeu.getChar(7, 3);//caisse

        assertEquals('#', c1, "Aux coordonnées (10,9) la valeur doit être '#' ");
        assertEquals('.', c2, "Aux coordonnées (17,7) la valeur doit être '.' ");
        assertEquals(' ', c3, "Aux coordonnées (3,6) la valeur doit être ' ' ");
        assertEquals('@', c4, "Aux coordonnées (11,8) la valeur doit être '@' ");
        assertEquals('$', c5, "Aux coordonnées (7,3) la valeur doit être '$' ");
    }
}
