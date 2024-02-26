package fr.ecole3il.rodez2023.carte.chemin.elements;

import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * La classe Graphe sert à représenter et à manipuler un graphe.
 * Elle offre des méthodes pour ajouter des nœuds au graphe, ajouter des arêtes entre les nœuds avec des coûts associés,
 * obtenir le coût d'une arête entre deux nœuds spécifiques, ainsi que pour obtenir des informations sur les nœuds et leurs voisins.
 *
 * @param <E> le type générique pour la valeur des nœuds du graphe.
 */
public class Graphe <E>{
    private Map<Noeud<E>, Map<Noeud<E>, Double>> adjacence;

    public Graphe() {
        adjacence = new HashMap<>();
    }

    public void ajouterNoeud(Noeud<E> noeud){
        if (!adjacence.containsKey(noeud)) {
            adjacence.put(noeud, new HashMap<>());
        }
    }

    /**
     * Méthode pour ajouter une arête pondérée entre deux nœuds du graphe.
     *
     * @param depart   le nœud de départ de l'arête.
     * @param arrivee  le nœud d'arrivée de l'arête.
     * @param cout     le coût de l'arête.
     */
    public void ajouterArete(Noeud<E> depart, Noeud<E> arrivee, double cout){
        ajouterNoeud(depart);
        ajouterNoeud(arrivee);

        adjacence.get(depart).put(arrivee, cout);
    }

    /**
     * Méthode pour obtenir le coût de l'arête entre deux nœuds spécifiés.
     *
     * @param depart   le nœud de départ de l'arête.
     * @param arrivee  le nœud d'arrivée de l'arête.
     * @return le coût de l'arête entre les deux nœuds spécifiés, null si pas d'arête entre les nœuds.
     */
    public double getCoutArete(Noeud<E> depart, Noeud<E> arrivee){
        if (!adjacence.containsKey(depart) || !adjacence.get(depart).containsKey(arrivee)) {
            return Double.POSITIVE_INFINITY;
        }
        return adjacence.get(depart).get(arrivee);

    }

    /**
     * Méthode pour obtenir une liste contenant tous les nœuds du graphe.
     *
     * @return une liste contenant tous les nœuds du graphe.
     */
    public List<Noeud<E>> getNoeuds(){

        return new ArrayList<>(adjacence.keySet());
    }

    /**
     * Méthode pour obtenir une liste contenant tous les voisins d'un nœud spécifié.
     *
     * @param noeud le nœud dont on souhaite obtenir les voisins.
     * @return une liste contenant tous les voisins du nœud spécifié, une liste vide si le nœud n'existe pas dans le graphe.
     */
    public List<Noeud<E>> getVoisins(Noeud<E> noeud) {
        if (!adjacence.containsKey(noeud)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(adjacence.get(noeud).keySet());
    }

}
