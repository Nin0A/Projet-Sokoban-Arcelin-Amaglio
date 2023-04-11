package jeu;

import java.io.*;
import java.util.ArrayList;

public class Chargement {

    /**
     * méthode chargerJeu
     *
     * @param nomFichier fichier que l'on souhaite charger
     * @return un Jeu correspondant aux caractéristiques présents dans le fichier 'nomFichier'
     * @throws IOException
     * @throws Exception
     */
    public static Jeu chargerJeu(String nomFichier) throws IOException, Exception {
        //ouverture du fichier
        BufferedReader fichier = new BufferedReader(new FileReader(nomFichier));

        //déclaration des listes Caisses & Dépot
        ListeElements listeCaisses = new ListeElements();
        ListeElements listeDepot = new ListeElements();
        //déclaration de la liste de String
        ArrayList<String> listeString = new ArrayList<String>();    //liste utile a la lecture du fichier

        //variables pour récupérer largeur & hauteur maximal
        int ymax = 0;
        int xmax = 0;

        //lecture des lignes du fichier pour avoir la hauteur max
        String ligne = fichier.readLine();
        while (ligne != null) {
            listeString.add(ligne);
            ligne = fichier.readLine();
            ymax++;
        }

        //on récupère la taille des différentes lignes pour avoir la largeur maximum
        for (int j = 0; j < listeString.size(); j++) {
            if (xmax < listeString.get(j).length()) {
                xmax = listeString.get(j).length();
            }
        }

        boolean[][] lab = new boolean[xmax][ymax];  //déclaration des valeurs de lab (labyrinthe) maximums
        int nbCaisse = 0; //compteur des caisses présentes
        int nbDepot = 0; //compteur des nombres de dépôt présents
        int nbPerso = 0; //compteur du nombre de joueur;
        Perso perso = null; //initialisation du personnage

        //on parcours notre tableau de String et toutes les lignes qu'il contient pour pouvoir identifier
        //chaque caractères
        for (int y = 0; y < ymax; y++) {
            for (int x = 0; x < listeString.get(y).length(); x++) {
                char c = listeString.get(y).charAt(x);
                switch (c) {
                    case Labyrinthe.CAISSE: //cas où on détecte une caisse
                        Caisse caisse = new Caisse(x, y);
                        listeCaisses.ajouter(caisse);
                        nbCaisse++;
                        break;
                    case Labyrinthe.DEPOT: //cas où on détecte un dépôt
                        Depot depot = new Depot(x, y);
                        listeDepot.ajouter(depot);
                        nbDepot++;
                        break;
                    case Labyrinthe.MUR: //cas où on détecte un mur
                        lab[x][y] = true;
                        break;
                    case Labyrinthe.PJ: //cas où on détecte un personnage
                        perso = new Perso(x, y);
                        nbPerso++;
                        break;
                    case Labyrinthe.VIDE: //cas où on détecte un vide
                        break;
                    default: //exception si caractère inconnu
                        throw new FichierIncorrectException("caractere inconnu <" + c + ">");
                }
            }
        }

        Labyrinthe laby = new Labyrinthe(lab); // on crée un labirynthe avec notre talbeau (lab)
        Jeu jeu = new Jeu(perso, laby, listeCaisses, listeDepot); // on peut ainsi créer un jeu
        fichier.close(); // on ferme le fichier

        //Gestion des diverses exceptions

        if (nbCaisse == 0) { //si il n'y a pas de caisses
            throw new FichierIncorrectException("caisses inconnues");
        }
        if (nbPerso > 1) { //si il y a plus d'un personnage
            throw new FichierIncorrectException("trop de personnages");
        }
        if (nbPerso == 0) { //si il n'y a pas de personnage
            throw new FichierIncorrectException("personnage inconnu"); //
        }
        if (nbCaisse != nbDepot) { //si le nombre de caisses ne correspond pas au nombre de dépôts
            throw new FichierIncorrectException("Caisses(<" + nbCaisse + ">) Depots(<" + nbDepot + ">)");
        }
        return jeu;
    }
}
