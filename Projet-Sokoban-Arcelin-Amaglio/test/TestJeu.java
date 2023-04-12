import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import jeu.*;

public class TestJeu {

    /**
     * test du constructeur
     *
     * @throws Exception
     */
    @Test
    public void test_Constructeur() throws Exception {
        Jeu jeu = Chargement.chargerJeu("laby/laby_test.txt");
        assertEquals(11, jeu.getLaby().getHauteurMax(), "La hauteur doit être égal à 11");
        assertEquals(19, jeu.getLaby().getLargeurMax(), "La hauteur doit être égal à 19");
    }

    /**
     * test de la méthode getChar
     *
     * @throws Exception
     */
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

    /**
     * test de la méthode deplacerPerso quand action = HAUT
     *
     * @throws Exception
     */
    @Test
    public void test_deplacerPerso_Haut() throws Exception {
        Jeu jeu = Chargement.chargerJeu("laby/laby_test.txt");

        char c4 = jeu.getChar(11, 8); //coordonnées initial du personnage
        assertEquals('@', c4, "Aux coordonnées (11,8) la valeur doit être '@' ");
        jeu.deplacerPerso(Jeu.HAUT);
        assertEquals(11, jeu.getPerso().getX(), "La valeur de getX ne change pas");
        assertEquals(7, jeu.getPerso().getY(), "La valeur de getY diminue de 1");
    }

    /**
     * test de la méthode deplacerPerso quand action = BAS mais il y a un mur
     *
     * @throws Exception
     */
    @Test
    public void test_deplacerPerso_Bas_MUR() throws Exception {
        Jeu jeu = Chargement.chargerJeu("laby/laby_test.txt");

        char c4 = jeu.getChar(11, 8); //coordonnées initial du personnage
        assertEquals('@', c4, "Aux coordonnées (11,8) la valeur doit être '@' ");

        jeu.deplacerPerso(Jeu.BAS);
        assertEquals(11, jeu.getPerso().getX(), "La valeur de getX ne change pas");
        assertEquals(8, jeu.getPerso().getY(), "La valeur de getY ne change pas car il y a un mur");
    }

    /**
     * test de la méthode deplacerPerso quand action = Gauche mais il y a un mur
     *
     * @throws Exception
     */
    @Test
    public void test_deplacerPerso_Gauche_MUR() throws Exception {
        Jeu jeu = Chargement.chargerJeu("laby/laby_test.txt");

        char c4 = jeu.getChar(11, 8); //coordonnées initial du personnage
        assertEquals('@', c4, "Aux coordonnées (11,8) la valeur doit être '@' ");

        jeu.deplacerPerso(Jeu.GAUCHE);
        assertEquals(11, jeu.getPerso().getX(), "La valeur de getX ne change pas car il y a un mur");
        assertEquals(8, jeu.getPerso().getY(), "La valeur de getY ne change pas");
    }

    /**
     * test de la méthode deplacerPerso quand action = Droite mais il y a un mur
     *
     * @throws Exception
     */
    @Test
    public void test_deplacerPerso_Droite_MUR() throws Exception {
        Jeu jeu = Chargement.chargerJeu("laby/laby_test.txt");

        char c4 = jeu.getChar(11, 8); //coordonnées initial du personnage
        assertEquals('@', c4, "Aux coordonnées (11,8) la valeur doit être '@' ");

        jeu.deplacerPerso(Jeu.DROITE);
        assertEquals(11, jeu.getPerso().getX(), "La valeur de getX ne change pas car il y a un mur");
        assertEquals(8, jeu.getPerso().getY(), "La valeur de getY ne change pas");
    }

    /**
     * test de la méthode deplacerPerso quand action = Gauche
     *
     * @throws Exception
     */
    @Test
    public void test_deplacerPerso_Gauche_Normal() throws Exception {
        Jeu jeu = Chargement.chargerJeu("laby/laby_simple.txt"); //on change de labyrinthe pour effectuer de nouveaux tests
        char c4 = jeu.getChar(3, 2); //coordonnées initial du personnage

        assertEquals('@', c4, "Aux coordonnées (3,2) la valeur doit être '@' ");

        jeu.deplacerPerso(Jeu.GAUCHE);
        assertEquals(2, jeu.getPerso().getX(), "La valeur de getX diminue de 1");
        assertEquals(2, jeu.getPerso().getY(), "La valeur de getY ne change pas");
    }

    /**
     * test de la méthode deplacerPerso quand action = Droite
     *
     * @throws Exception
     */
    @Test
    public void test_deplacerPerso_Droite_Normal() throws Exception {
        Jeu jeu = Chargement.chargerJeu("laby/laby_simple.txt"); //on change de labyrinthe pour effectuer de nouveaux tests
        char c4 = jeu.getChar(3, 2); //coordonnées initial du personnage

        assertEquals('@', c4, "Aux coordonnées (3,2) la valeur doit être '@' ");

        jeu.deplacerPerso(Jeu.DROITE);
        assertEquals(4, jeu.getPerso().getX(), "La valeur de getX augmente de 1");
        assertEquals(2, jeu.getPerso().getY(), "La valeur de getY ne change pas");
    }

    /**
     * test de la méthode deplacerPerso quand action = Haut mais il y a un mur
     *
     * @throws Exception
     */
    @Test
    public void test_deplacerPerso_Haut_MUR() throws Exception {
        Jeu jeu = Chargement.chargerJeu("laby/laby_test.txt");

        char c4 = jeu.getChar(11, 8); //coordonnées initial du personnage
        assertEquals('@', c4, "Aux coordonnées (11,8) la valeur doit être '@' ");
        jeu.deplacerPerso(Jeu.HAUT);
        assertEquals(11, jeu.getPerso().getX(), "La valeur de getX ne change pas");
        assertEquals(7, jeu.getPerso().getY(), "La valeur de getY diminue de 1");
        jeu.deplacerPerso(Jeu.HAUT);
        assertEquals(11, jeu.getPerso().getX(), "La valeur de getX ne change pas");
        assertEquals(7, jeu.getPerso().getY(), "La valeur de getY ne change pas car il y a un mur");
    }

    /**
     * test deplacerPerso lorsque que le perso va dans la direction d'une caisse adjacente à une autre
     * le perso ne devrait pas pouvoir déplacer deux caisses
     *
     * @throws Exception
     */
    @Test
    public void test_deplacerPerso_2_CAISSES() throws Exception {
        Jeu jeu = Chargement.chargerJeu("laby/laby_test2.txt");

        char c1 = jeu.getChar(4, 2); //coordonnées initial du personnage
        char c2 = jeu.getChar(2, 2); //coordonnées initial de la première caisse
        char c3 = jeu.getChar(3, 2); //coordonnées initial de la deuxième caisse
        assertEquals('@', c1, "Aux coordonnées (4,2) la valeur doit être '@' ");
        assertEquals('$', c2, "Aux coordonnées (2,2) la valeur doit être '$' ");
        assertEquals('$', c3, "Aux coordonnées (2,2) la valeur doit être '$' ");

        jeu.deplacerPerso(Jeu.GAUCHE);

        assertEquals(4, jeu.getPerso().getX(), "Les coordonnées de personnage ne change pas");
        assertEquals(2, jeu.getListeCaisses().getElementByIndice(0).getX(), "Les coordonnées de la première caisse de change pas");
        assertEquals(3, jeu.getListeCaisses().getElementByIndice(1).getX(), "Les coordonnées de la seconde caisse ne change pas");
    }

    /**
     * test deplacerPerso lorsqu'il y a une seul caisse, cette fois-ci le perso doit pouvoir
     * déplacer la caisse
     *
     * @throws Exception
     */
    @Test
    public void test_deplacerPerso_1_CAISSE() throws Exception {
        Jeu jeu = Chargement.chargerJeu("laby/laby_test3.txt");

        char c1 = jeu.getChar(4, 2); //coordonnées initial du personnage
        char c2 = jeu.getChar(3, 2); //coordonnées initial de la première caisse

        assertEquals('@', c1, "Aux coordonnées (4,2) la valeur doit être '@' ");
        assertEquals('$', c2, "Aux coordonnées (2,2) la valeur doit être '$' ");

        jeu.deplacerPerso(Jeu.GAUCHE);

        assertEquals(3, jeu.getPerso().getX(), "La getX doit renvoyer x-1");
        assertEquals(2, jeu.getListeCaisses().getElementByIndice(0).getX(), "La getX doit renvoyer x-1");
    }

    /**
     * méthode déplacerPerso lorsque l'action n'est pas connue, alors une erreur doit s'afficher
     *
     * @throws Exception
     */
    @Test
    public void test_deplacerPerso_Action_Inconnue() throws Exception {
        Jeu jeu = Chargement.chargerJeu("laby/laby_test.txt");
        char c4 = jeu.getChar(11, 8); //coordonnées initial du personnage
        assertEquals('@', c4, "Aux coordonnées (11,8) la valeur doit être '@' ");

        String erreur = "";
        try {
            jeu.deplacerPerso("ACTION_INCONNUE");
        } catch (ActionInconnueException e) {
            erreur = e.getMessage();
        }
        assertEquals("Action inconnue : ACTION_INCONNUE", erreur, "L'action n'est pas reconnue");
    }

    /**
     * test méthode etreFini 1er deplacement la partie n'est pas finie deuxième action la partie est finie
     *
     * @throws Exception
     */
    @Test
    public void test_etreFini() throws Exception {
        Jeu jeu = Chargement.chargerJeu("laby/laby_test3.txt");

        char c1 = jeu.getChar(4, 2); //coordonnées initial du personnage
        char c2 = jeu.getChar(3, 2); //coordonnées initial de la première caisse

        assertEquals('@', c1, "Aux coordonnées (4,2) la valeur doit être '@' ");
        assertEquals('$', c2, "Aux coordonnées (2,2) la valeur doit être '$' ");

        jeu.deplacerPerso(Jeu.GAUCHE);

        assertEquals(3, jeu.getPerso().getX(), "La getX doit renvoyer x-1");
        assertEquals(2, jeu.getListeCaisses().getElementByIndice(0).getX(), "La getX doit renvoyer x-1");
        assertFalse(jeu.etreFini()); //le jeu n'est pas fini

        jeu.deplacerPerso(Jeu.GAUCHE);

        assertEquals(2, jeu.getPerso().getX(), "La getX doit renvoyer x-1");
        assertEquals(1, jeu.getListeCaisses().getElementByIndice(0).getX(), "La getX doit renvoyer x-1");
        assertTrue(jeu.etreFini()); //le jeu est pas fini
    }
}
