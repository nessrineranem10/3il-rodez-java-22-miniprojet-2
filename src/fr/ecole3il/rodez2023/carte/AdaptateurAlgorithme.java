package fr.ecole3il.rodez2023.carte;

import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeChemin;
import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeDijkstra;
import fr.ecole3il.rodez2023.carte.chemin.elements.*;
import fr.ecole3il.rodez2023.carte.manipulateurs.GenerateurCarte;

import java.util.ArrayList;
import java.util.List;


public class AdaptateurAlgorithme {

    public static Chemin trouverChemin(AlgorithmeChemin<Case> algorithme, Carte carte, int xDepart, int yDepart, int xArrivee, int yArrivee) {
        Graphe<Case> graphe = creerGraphe(carte);
        Noeud<Case> depart = graphe.getNoeuds().get(xDepart * carte.getHauteur() + yDepart);
        Noeud<Case> arrivee = graphe.getNoeuds().get(xArrivee * carte.getHauteur() + yArrivee);
        List<Noeud<Case>> cheminNoeuds = algorithme.trouverChemin(graphe, depart, arrivee);
        afficherChemin(cheminNoeuds);
        return new Chemin(convertirNoeudsEnCases(cheminNoeuds));
    }


    static Graphe<Case> creerGraphe(Carte carte) {
        Graphe<Case> graphe = new Graphe<>();
        int largeur = carte.getLargeur();
        int hauteur = carte.getHauteur();

        // Parcours de chaque case de la carte pour créer les noeuds du graphe
        for (int i = 0; i < largeur; i++) {
            for (int j = 0; j < hauteur; j++) {
                Tuile tuileCourante = carte.getTuile(i, j);
                Case caseCourante = new Case(tuileCourante, i, j);
                graphe.ajouterNoeud(new Noeud<>(caseCourante));
                ajouterAretesVoisines(graphe, caseCourante, i, j, largeur, hauteur);
            }
        }
        return graphe;
    }

    static void ajouterAretesVoisines(Graphe<Case> graphe, Case currentCase, int x, int y, int largeur, int hauteur) {
    }
    static List<Case> convertirNoeudsEnCases(List<Noeud<Case>> noeuds) {
        List<Case> cases = new ArrayList<>();
        for (Noeud<Case> noeud : noeuds) {
            cases.add(noeud.getValeur());
        }
        return cases;
    }
    static double calculerCout(Case from, Case to) {
        // Calcul du coût entre deux cases
        Tuile tuileFrom = from.getTuile();
        Tuile tuileTo = to.getTuile();
        // Vous devrez adapter cette partie selon la logique spécifique de calcul de coût entre les tuiles
        return Math.abs(tuileFrom.getPenalite() - tuileTo.getPenalite());
    }

    public static void afficherChemin(List<Noeud<Case>> chemin) {
        System.out.println("Chemin :");
        for (Noeud<Case> noeud : chemin) {
            Case caseCourante = noeud.getValeur();
            System.out.println("[" + caseCourante.getX() + ", " + caseCourante.getY() + "] : " + caseCourante.getTuile());
        }
    }

}