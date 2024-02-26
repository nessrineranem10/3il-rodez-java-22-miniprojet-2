package fr.ecole3il.rodez2023.carte.elements;

import java.util.List;

public interface AlgorithmeChemin<E> {

/**
 * * @param graphe   le graphe dans lequel la recherche doit être effectuée.
 *      * @param depart   le nœud de départ.
 *      * @param arrivee  le nœud d'arrivée.
 *      * @return une liste de nœuds représentant le chemin trouvé entre le nœud de départ et le nœud d'arrivée dans le graphe.
 *      */
List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee);

}
