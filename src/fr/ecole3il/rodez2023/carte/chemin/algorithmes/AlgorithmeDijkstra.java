package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;

import java.util.*;

public class AlgorithmeDijkstra<E> implements AlgorithmeChemin<E> {

    private Graphe<E> graph;

    public AlgorithmeDijkstra(Graphe<E> graph) {
        this.graph = graph;
    }

    @Override
    public Map<E, Double> trouverCheminLePlusCourt(E depart) {
        Map<E, Double> couts = new HashMap<>();
        Map<E, E> predecesseurs = new HashMap<>();
        PriorityQueue<E> filePriorite = new PriorityQueue<>(Comparator.comparingDouble(couts::get));


        initialiserStructures(depart, couts, predecesseurs, filePriorite);


        while (!filePriorite.isEmpty()) {
            E node = filePriorite.poll();

            for (E voisin : graph.getVoisins(node)) {
                double coutTotal = couts.get(node) + graph.getCout(node, voisin);

                if (coutTotal < couts.getOrDefault(voisin, Double.POSITIVE_INFINITY)) {
                    couts.put(voisin, coutTotal);
                    predecesseurs.put(voisin, node);
                    filePriorite.add(voisin);
                }
            }
        }

        return couts;
    }

    private void initialiserStructures(E depart, Map<E, Double> couts, Map<E, E> predecesseurs, PriorityQueue<E> filePriorite) {
        for (E node : graph.getNoeuds()) {
            if (node.equals(depart)) {
                couts.put(node, 0.0);
            } else {
                couts.put(node, Double.POSITIVE_INFINITY);
            }
            predecesseurs.put(node, null);
            filePriorite.add(node);
        }
    }

    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
        return null;
    }
}
