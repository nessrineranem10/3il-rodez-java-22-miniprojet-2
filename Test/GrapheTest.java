import static org.junit.jupiter.api.Assertions.*;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GrapheTest {

    private Graphe<String> graphe;
    private Noeud<String> noeudA;
    private Noeud<String> noeudB;
    private Noeud<String> noeudC;

    @BeforeEach
    public void setUp() {
        graphe = new Graphe<>();
        noeudA = new Noeud<>("A");
        noeudB = new Noeud<>("B");
        noeudC = new Noeud<>("C");
    }

    @Test
    public void testAjouterNoeud() {
        graphe.ajouterNoeud(noeudA);
        assertTrue(graphe.getNoeuds().contains(noeudA));
    }

    @Test
    public void testAjouterArete() {
        graphe.ajouterArete(noeudA, noeudB, 2.0);
        assertTrue(graphe.getCoutArete(noeudA, noeudB) == 2.0);
    }

    @Test
    public void testGetCoutArete() {
        graphe.ajouterArete(noeudA, noeudB, 2.0);
        assertTrue(graphe.getCoutArete(noeudA, noeudB) == 2.0);
        assertTrue(graphe.getCoutArete(noeudB, noeudA) == Double.POSITIVE_INFINITY);
    }

    @Test
    public void testGetNoeuds() {
        graphe.ajouterNoeud(noeudA);
        graphe.ajouterNoeud(noeudB);
        graphe.ajouterNoeud(noeudC);

        assertEquals(3, graphe.getNoeuds().size());
        assertTrue(graphe.getNoeuds().contains(noeudA));
        assertTrue(graphe.getNoeuds().contains(noeudB));
        assertTrue(graphe.getNoeuds().contains(noeudC));
    }

    @Test
    public void testGetVoisins() {
        graphe.ajouterArete(noeudA, noeudB, 2.0);
        graphe.ajouterArete(noeudA, noeudC, 3.0);

        assertEquals(2, graphe.getVoisins(noeudA).size());
        assertTrue(graphe.getVoisins(noeudA).contains(noeudB));
        assertTrue(graphe.getVoisins(noeudA).contains(noeudC));

        assertEquals(0, graphe.getVoisins(noeudB).size());
    }

}
