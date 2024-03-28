import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeDijkstra;
import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AlgorithmeDijkstraTest {

    private AlgorithmeDijkstra<String> algorithmeDijkstra;
    private Graphe<String> graphe;
    private Noeud<String> noeudA;
    private Noeud<String> noeudB;
    private Noeud<String> noeudC;
    private Noeud<String> noeudD;
    private Noeud<String> noeudE;

    @BeforeEach
    public void setUp() {
        algorithmeDijkstra = new AlgorithmeDijkstra<>();
        graphe = new Graphe<>();
        noeudA = new Noeud<>("A");
        noeudB = new Noeud<>("B");
        noeudC = new Noeud<>("C");
        noeudD = new Noeud<>("D");
        noeudE = new Noeud<>("E");

        graphe.ajouterNoeud(noeudA);
        graphe.ajouterNoeud(noeudB);
        graphe.ajouterNoeud(noeudC);
        graphe.ajouterNoeud(noeudD);
        graphe.ajouterNoeud(noeudE);

        graphe.ajouterArete(noeudA, noeudB, 1.0);
        graphe.ajouterArete(noeudA, noeudC, 3.0);
        graphe.ajouterArete(noeudB, noeudD, 2.0);
        graphe.ajouterArete(noeudC, noeudD, 1.0);
        graphe.ajouterArete(noeudD, noeudE, 1.0);
    }
    @Test
    public void testTrouverCheminAvecDepartEgalArrivee() {
        List<Noeud<String>> chemin = algorithmeDijkstra.trouverChemin(graphe, noeudA, noeudA);
        assertEquals(Collections.singletonList(noeudA), chemin);
    }
}

