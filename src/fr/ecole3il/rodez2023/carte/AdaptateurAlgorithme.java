package fr.ecole3il.rodez2023.carte;

import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeChemin;
import fr.ecole3il.rodez2023.carte.chemin.elements.*;

import java.util.*;

public class AdaptateurAlgorithme {

    public static Chemin trouverChemin(AlgorithmeChemin<Case> algorithme, Carte carte, int xDepart, int yDepart, int xArrivee, int yArrivee) {
        Graphe<Case> graphe = creerGraphe(carte);
        Case depart = carte.getCase(xDepart, yDepart);
        Case arrivee = carte.getCase(xArrivee, yArrivee);
        List<Case> cheminCases = algorithme.trouverChemin(graphe, depart, arrivee);
        Chemin chemin = new Chemin();
        chemin.setCases(cheminCases);
        return chemin;
    }

    public static Graphe<Case> creerGraphe(Carte carte) {
        Graphe<Case> graphe = new Graphe<>();
        int largeur = carte.getLargeur();
        int hauteur = carte.getHauteur();

        for (int x = 0; x < largeur; x++) {
            for (int y = 0; y < hauteur; y++) {
                Case currentCase = carte.getCase(x, y);
                graphe.ajouterNoeud(currentCase);
                ajouterAretesVoisines(graphe, currentCase, x, y, largeur, hauteur);
            }
        }

        return graphe;
    }

    public static void ajouterAretesVoisines(Graphe<Case> graphe, Case currentCase, int x, int y, int largeur, int hauteur) {
        if (x > 0) {
            Case left = graphe.getNoeud(x - 1, y);
            double cout = calculerCout(currentCase, left);
            graphe.ajouterArete(currentCase, left, cout);
        }
        if (x < largeur - 1) {
            Case right = graphe.getNoeud(x + 1, y);
            double cout = calculerCout(currentCase, right);
            graphe.ajouterArete(currentCase, right, cout);
        }
        if (y > 0) {
            Case up = graphe.getNoeud(x, y - 1);
            double cout = calculerCout(currentCase, up);
            graphe.ajouterArete(currentCase, up, cout);
        }
        if (y < hauteur - 1) {
            Case down = graphe.getNoeud(x, y + 1);
            double cout = calculerCout(currentCase, down);
            graphe.ajouterArete(currentCase, down, cout);
        }
    }

    public static double calculerCout(Case from, Case to) {
        // Supposons que le coût soit simplement la distance euclidienne entre les cases
        int deltaX = to.getX() - from.getX();
        int deltaY = to.getY() - from.getY();
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    public static void afficherChemin(List<Noeud<Case>> chemin) {
        for (Noeud<Case> noeud : chemin) {
            System.out.println("[" + noeud.getContenu().getX() + ", " + noeud.getContenu().getY() + "]");
        }
    }
}
