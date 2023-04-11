package jeu;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.util.Scanner;

public class MainJeu {
    public static void main(String[] args) throws IOException, Exception, FichierIncorrectException {

        Scanner sc = new Scanner(System.in);

        //on crée le jeu
        System.out.println("Niveaux disponibles : laby.txt, laby_simple.txt, laby_test.txt"); //on affiche tous les labirynthes disponible pour que le choix soit plus facile
        System.out.println("Choisissez un niveau :");
        String fichier = "laby/" + sc.nextLine();
        Chargement test = new Chargement();
        Jeu jeu = test.chargerJeu(fichier);

        //on l'affiche une fois pour que le joueur puisse faire des actions en fonction de ceci
        System.out.println(jeu.JeuToString());

        String action;
        int score = 0; //initialisation du score à 0

        while (!jeu.etreFini()) {
            try {
                System.out.println("Haut, Bas, Gauche, Droite       " + " score : " + score);
                System.out.println("Action : ");
                action = sc.nextLine(); //le joueur entre l'action souhaiter
                jeu.deplacerPerso(action);
                score++; //le score augmente de 1
                System.out.println(jeu.JeuToString());

            } catch (ActionInconnueException e) { //si l'action n'est pas connue
                System.out.println(jeu.JeuToString());
                System.out.println("Veuillez choisir une action valide :");
            }
        }
        System.out.println("Vous avez gagnez !\nVotre score : " + score); //on affiche le score
    }
}
