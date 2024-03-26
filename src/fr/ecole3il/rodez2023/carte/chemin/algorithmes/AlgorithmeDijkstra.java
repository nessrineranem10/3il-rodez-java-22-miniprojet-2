package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;

import java.util.*;

public class AlgorithmeDijkstra<E> implements AlgorithmeChemin<E> {

    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
        Map<Noeud<E>, Double> couts = new HashMap<>();
        Map<Noeud<E>, Noeud<E>> predecesseurs = new HashMap<>();

        for (Noeud<E> noeud : graphe.getNoeuds()) {
            couts.put(noeud, Double.POSITIVE_INFINITY);
            predecesseurs.put(noeud, null);
        }
        couts.put(depart, 0.0);

        PriorityQueue<Noeud<E>> filePriorite = new PriorityQueue<>((n1, n2) -> (int) (couts.get(n1) - couts.get(n2)));
        filePriorite.add(depart);

        while (!filePriorite.isEmpty()) {
            Noeud<E> noeud = filePriorite.poll();
            if (noeud.equals(arrivee)) break;

            for (Noeud<E> voisin : graphe.getVoisins(noeud)) {
                double cout = couts.get(noeud) + graphe.getCoutArete(noeud, voisin);
                if (cout < couts.get(voisin)) {
                    couts.put(voisin, cout);
                    predecesseurs.put(voisin, noeud);
                    filePriorite.add(voisin);
                }
            }
        }

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
