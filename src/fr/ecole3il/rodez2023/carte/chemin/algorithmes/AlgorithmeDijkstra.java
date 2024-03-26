package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;

import java.util.*;

/**
 * Implémentation de l'algorithme de Dijkstra pour trouver le chemin le plus court entre deux noeuds dans un graphe pondéré.
 *
 * @param <E> le type des éléments contenus dans les noeuds du graphe
 */
public class AlgorithmeDijkstra<E> implements AlgorithmeChemin<E> {

    /**
     * Trouve le chemin le plus court entre un noeud de départ et un noeud d'arrivée dans un graphe pondéré en utilisant l'algorithme de Dijkstra.
     *
     * @param graphe   le graphe dans lequel trouver le chemin
     * @param depart   le noeud de départ
     * @param arrivee  le noeud d'arrivée
     * @return une liste de noeuds représentant le chemin le plus court trouvé, ou une liste vide si aucun chemin n'est trouvé
     */
    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
        Map<Noeud<E>, Double> couts = new HashMap<>();
        Map<Noeud<E>, Noeud<E>> predecesseurs = new HashMap<>();

        // Initialisation des coûts à l'infini pour tous les noeuds sauf le départ
        for (Noeud<E> noeud : graphe.getNoeuds()) {
            couts.put(noeud, Double.POSITIVE_INFINITY);
            predecesseurs.put(noeud, null);
        }
        couts.put(depart, 0.0);

        // File de priorité pour stocker les noeuds à explorer, triés par le coût actuel
        PriorityQueue<Noeud<E>> filePriorite = new PriorityQueue<>((n1, n2) -> (int) (couts.get(n1) - couts.get(n2)));
        filePriorite.add(depart);

        // Algorithme de Dijkstra
        while (!filePriorite.isEmpty()) {
            Noeud<E> noeud = filePriorite.poll();
            if (noeud.equals(arrivee)) break;

            // Parcours des voisins du noeud actuel
            for (Noeud<E> voisin : graphe.getVoisins(noeud)) {
                double cout = couts.get(noeud) + graphe.getCoutArete(noeud, voisin);
                if (cout < couts.get(voisin)) {
                    couts.put(voisin, cout);
                    predecesseurs.put(voisin, noeud);
                    filePriorite.add(voisin);
                }
            }
        }

        // Reconstruction du chemin à partir des prédécesseurs
        List<Noeud<E>> cheminLePlusCourt = new ArrayList<>();
        Noeud<E> noeud = arrivee;
        while (noeud != null) {
            cheminLePlusCourt.add(0, noeud);
            noeud = predecesseurs.get(noeud);
        }
        Collections.reverse(cheminLePlusCourt);

        return cheminLePlusCourt;
    }
}

