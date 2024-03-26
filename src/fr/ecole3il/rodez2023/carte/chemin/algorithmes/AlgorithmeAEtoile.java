package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;

import java.util.*;

/**
 * Implémentation de l'algorithme A* pour trouver le chemin le plus court entre deux noeuds dans un graphe pondéré.
 *
 * @param <E> le type des éléments contenus dans les noeuds du graphe
 */
public class AlgorithmeAEtoile<E> implements AlgorithmeChemin<E> {

    /**
     * Trouve un chemin optimal entre un noeud de départ et un noeud d'arrivée dans un graphe pondéré en utilisant l'algorithme A*.
     *
     * @param graphe   le graphe dans lequel trouver le chemin
     * @param depart   le noeud de départ
     * @param arrivee  le noeud d'arrivée
     * @return une liste de noeuds représentant le chemin optimal trouvé, ou une liste vide si aucun chemin n'est trouvé
     */
    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
        Map<Noeud<E>, Double> coutEstime = new HashMap<>();
        Map<Noeud<E>, Double> coutActuel = new HashMap<>();
        Map<Noeud<E>, Noeud<E>> predecesseurs = new HashMap<>();

        // Initialisation des coûts estimés et actuels à l'infini pour tous les noeuds
        for (Noeud<E> noeud : graphe.getNoeuds()) {
            coutEstime.put(noeud, Double.POSITIVE_INFINITY);
            coutActuel.put(noeud, Double.POSITIVE_INFINITY);
            predecesseurs.put(noeud, null);
        }
        coutEstime.put(depart, 0.0);
        coutActuel.put(depart, 0.0);

        // File de priorité pour stocker les noeuds à explorer, triés par le coût estimé
        PriorityQueue<Noeud<E>> filePriorite = new PriorityQueue<>((n1, n2) -> (int) (coutEstime.get(n1) - coutEstime.get(n2)));
        filePriorite.add(depart);

        // Algorithme A*
        while (!filePriorite.isEmpty()) {
            Noeud<E> noeud = filePriorite.poll();
            if (noeud.equals(arrivee)) break;

            // Parcours des voisins du noeud actuel
            for (Noeud<E> voisin : graphe.getVoisins(noeud)) {
                double cout = coutActuel.get(noeud) + graphe.getCoutArete(noeud, voisin);
                if (cout < coutActuel.get(voisin)) {
                    predecesseurs.put(voisin, noeud);
                    coutActuel.put(voisin, cout);
                    coutEstime.put(voisin, cout); // Dans l'algorithme A*, le coût estimé est égal au coût actuel
                    filePriorite.add(voisin);
                }
            }
        }

        // Reconstruction du chemin à partir des prédécesseurs
        List<Noeud<E>> chemin = new ArrayList<>();
        Noeud<E> noeud = arrivee;
        while (noeud != null) {
            chemin.add(noeud);
            noeud = predecesseurs.get(noeud);
        }
        Collections.reverse(chemin);

        return chemin;
    }
}
