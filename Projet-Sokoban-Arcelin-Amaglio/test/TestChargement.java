import static org.junit.jupiter.api.Assertions.assertEquals;

import jeu.Chargement;
import jeu.FichierIncorrectException;
import jeu.Jeu;
import org.junit.jupiter.api.Test;

public class TestChargement {

    /**
     * test pour voir si le jeu à était chargé correctement
     *
     * @throws Exception
     */
    @Test
    public void test_chargerJeuNormal() throws Exception {
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

    /**
     * test lorsqu'il n'y a pas de personnage dans le fichier, alors une erreur doit s'afficher
     *
     * @throws Exception
     */
    @Test
    public void test_chargerJeu_Pas_De_Personnage() throws Exception {
        String erreur = "";
        try {
            Chargement.chargerJeu("laby/laby_test4.txt");
        } catch (FichierIncorrectException e) {
            erreur = e.getMessage();
        }
        assertEquals("personnage inconnu", erreur, "Il doit y avoir une erreur car il n'y a pas de personnage");
    }

    /**
     * test lorsqu'il y a trop de personnage dans le fichier, alors une erreur doit s'afficher
     *
     * @throws Exception
     */
    @Test
    public void test_chargerJeu_Trop_De_Personnages() throws Exception {
        String erreur = "";
        try {
            Chargement.chargerJeu("laby/laby_test5.txt");
        } catch (FichierIncorrectException e) {
            erreur = e.getMessage();
            ;
        }
        assertEquals("trop de personnages", erreur, "Il doit y avoir une erreur car il n'y a trop de personnages");
    }

    /**
     * test lorsque le nombre de dépôts est différents au nombre de caisses dans le ficher, alors une erreur doit s'afficher
     *
     * @throws Exception
     */
    @Test
    public void test_chargerJeu_Nb_Caisses_Different_De_Nb_Depot() throws Exception {
        String erreur = "";
        try {
            Chargement.chargerJeu("laby/laby_test6.txt");
        } catch (FichierIncorrectException e) {
            erreur = e.getMessage();
        }
        assertEquals("Caisses(<1>) Depots(<2>)", erreur, "Il doit y avoir une erreur car nombre de depots != nombre de caisses");
    }

    /**
     * test lorsqu'il y a un caractere inconnue dans le fichier, alors une erreur doit s'afficher
     *
     * @throws Exception
     */
    @Test
    public void test_chargerJeu_Caractere_Inconnu() throws Exception {
        String erreur = "";
        try {
            Chargement.chargerJeu("laby/laby_test7.txt");
        } catch (FichierIncorrectException e) {
            erreur = e.getMessage();
        }
        assertEquals("caractere inconnu <¨>", erreur, "Il doit y avoir une erreur car il y a un caractere inconnu");
    }
}
