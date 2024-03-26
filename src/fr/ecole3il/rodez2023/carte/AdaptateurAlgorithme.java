package fr.ecole3il.rodez2023.carte;

import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeChemin;
import fr.ecole3il.rodez2023.carte.chemin.elements.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe fournit des méthodes pour adapter un algorithme de recherche de chemin à une carte donnée.
 */
public class AdaptateurAlgorithme {

    /**
     * Trouve un chemin entre deux points sur une carte en utilisant un algorithme de recherche de chemin spécifié.
     *
     * @param algorithme l'algorithme de recherche de chemin à utiliser
     * @param carte      la carte sur laquelle trouver le chemin
     * @param xDepart    la coordonnée x du point de départ
     * @param yDepart    la coordonnée y du point de départ
     * @param xArrivee   la coordonnée x du point d'arrivée
     * @param yArrivee   la coordonnée y du point d'arrivée
     * @return le chemin trouvé entre les deux points
     */
    public static Chemin trouverChemin(AlgorithmeChemin<Case> algorithme, Carte carte, int xDepart, int yDepart, int xArrivee, int yArrivee) {
        Graphe<Case> graphe = creerGraphe(carte);
        Noeud<Case> depart = graphe.getNoeuds().get(xDepart * carte.getHauteur() + yDepart);
        Noeud<Case> arrivee = graphe.getNoeuds().get(xArrivee * carte.getHauteur() + yArrivee);
        List<Noeud<Case>> cheminNoeuds = algorithme.trouverChemin(graphe, depart, arrivee);
        afficherChemin(cheminNoeuds);
        return new Chemin(convertirNoeudsEnCases(cheminNoeuds));
    }

    /**
     * Crée un graphe à partir d'une carte donnée.
     *
     * @param carte la carte à partir de laquelle créer le graphe
     * @return le graphe créé
     */
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

    /**
     * Ajoute les arêtes voisines pour un noeud donné dans un graphe.
     *
     * @param graphe      le graphe dans lequel ajouter les arêtes
     * @param currentCase la case actuelle pour laquelle ajouter les arêtes
     * @param x           la coordonnée x de la case actuelle
     * @param y           la coordonnée y de la case actuelle
     * @param largeur     la largeur de la carte
     * @param hauteur     la hauteur de la carte
     */


    static void ajouterAretesVoisines(Graphe<Case> graphe, Case currentCase, int x, int y, int largeur, int hauteur) {
        /* int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Déplacements possibles : haut, bas, gauche, droite

        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];

            // Vérification si la nouvelle position est à l'intérieur de la carte
            if (newX >= 0 && newX < largeur && newY >= 0 && newY < hauteur) {
                Case neighborCase = new Case(null, newX, newY); // Création d'une case voisine (la tuile peut être null si elle n'est pas nécessaire)

                // Ajout de la case voisine au graphe
                graphe.ajouterNoeud(new Noeud<>(neighborCase));
            }
        } */
    }

    /**
     * Convertit une liste de noeuds en une liste de cases.
     *
     * @param noeuds la liste de noeuds à convertir
     * @return la liste de cases obtenue à partir des noeuds
     */
    static List<Case> convertirNoeudsEnCases(List<Noeud<Case>> noeuds) {
        List<Case> cases = new ArrayList<>();
        for (Noeud<Case> noeud : noeuds) {
            cases.add(noeud.getValeur());
        }
        return cases;
    }

    /**
     * Calcule le coût entre deux cases.
     *
     * @param from la case de départ
     * @param to   la case d'arrivée
     * @return le coût entre les deux cases
     */
    static double calculerCout(Case from, Case to) {
        // Calcul du coût entre deux cases
        Tuile tuileFrom = from.getTuile();
        Tuile tuileTo = to.getTuile();
        // Vous devrez adapter cette partie selon la logique spécifique de calcul de coût entre les tuiles
        return Math.abs(tuileFrom.getPenalite() - tuileTo.getPenalite());
    }

    /**
     * Affiche le chemin trouvé sur la console.
     *
     * @param chemin la liste des noeuds formant le chemin
     */
    public static void afficherChemin(List<Noeud<Case>> chemin) {
        System.out.println("Chemin :");
        for (Noeud<Case> noeud : chemin) {
            Case caseCourante = noeud.getValeur();
            System.out.println("[" + caseCourante.getX() + ", " + caseCourante.getY() + "] : " + caseCourante.getTuile());
        }
    }
}
