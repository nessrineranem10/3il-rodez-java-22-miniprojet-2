package fr.ecole3il.rodez2023.carte.chemin.elements;
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

public class Graphe<E> {
    Map<Noeud<E>, Map<Noeud<E>, Double>> voisins;

    public Graphe() {
        this.voisins = new HashMap<>();
    }

    /**
     * Ajoute un noeud au graphe
     * @param noeud le noeud à ajouter
     */
    public void ajouterNoeud(Noeud<E> noeud) {
        if (!voisins.containsKey(noeud)) voisins.put(noeud, new HashMap<>());
    }

    /**
     * Ajoute une arête au graphe
     * @param depart le noeud de départ
     * @param arrivee le noeud d'arrivée
     * @param cout le coût de l'arête
     */
    public void ajouterArete(Noeud<E> depart, Noeud<E> arrivee, double cout){
        assert depart != null && arrivee != null;
        ajouterNoeud(depart);
        ajouterNoeud(arrivee);

        voisins.get(depart).put(arrivee, cout);
    }

    /**
     * Récupère le coût d'une arête
     * @param depart le noeud de départ
     * @param arrivee le noeud d'arrivée
     * @return le coût de l'arête
     */
    public double getCoutArete(Noeud<E> depart, Noeud<E> arrivee) {
        if (voisins.containsKey(depart) && voisins.get(depart).containsKey(arrivee)) {
            return voisins.get(depart).get(arrivee);
        }
        return Double.POSITIVE_INFINITY;
    }

    /**
     * Récupère la liste des noeuds du graphe
     * @return la liste des noeuds du graphe
     */
    public List<Noeud<E>> getNoeuds(){
        return new ArrayList<>(voisins.keySet());
    }

    /**
     * Récupère la liste des voisins d'un noeud
     * @param noeud le noeud dont on veut les voisins
     * @return la liste des voisins du noeud
     */
    public List<Noeud<E>> getVoisins(Noeud<E> noeud){
        if (voisins.containsKey(noeud)) {
            return new ArrayList<>(voisins.get(noeud).keySet());
        }
        return new ArrayList<>();
    }
    /**
     * Retourne le nœud correspondant aux coordonnées spécifiées.
     *
     * @param x La coordonnée x de la case recherchée.
     * @param y La coordonnée y de la case recherchée.
     * @return Le nœud correspondant aux coordonnées spécifiées, ou null si aucun nœud n'est trouvé.
     */
    public Noeud<E> getNoeud(int x, int y) {
        for (Noeud<E> noeud : this.getNoeuds()) {
            Case caseActuelle = (Case) noeud.getValeur();
            if (caseActuelle.getX() == x && caseActuelle.getY() == y) {
                return noeud;
            }
        }
        return null;
    }
}