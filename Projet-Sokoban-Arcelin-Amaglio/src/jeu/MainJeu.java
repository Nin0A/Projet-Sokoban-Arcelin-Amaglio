package jeu;

import java.io.IOException;
import java.util.Scanner;

public class MainJeu {
    public static void main(String[] args) throws IOException,Exception, FichierIncorrectException {
        Chargement test = new Chargement();
        Jeu jeu=test.chargerJeu("laby/laby_simple.txt");
        System.out.println(jeu.jeuToString());
        Scanner sc = new Scanner(System.in);
        String action;
        int score=0;
        while(!jeu.etreFini()) {
            try {
                System.out.println("Action : ");
                action = sc.nextLine();
                jeu.deplacerPerso(action);
                score++;
                System.out.println(jeu.jeuToString());

            } catch (ActionInconnueException e) {
                System.out.println(jeu.jeuToString());
                System.out.println("Veuillez choisir une action valide :");
            }
        }
        System.out.println("Vous avez gagnez !\nVotre score : "+score);
    }
}
