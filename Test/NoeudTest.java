import static org.junit.jupiter.api.Assertions.*;

import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;

public class NoeudTest {

    private Noeud<String> noeudA;
    private Noeud<String> noeudB;
    private Noeud<String> noeudC;

    @BeforeEach
    public void setUp() {
        noeudA = new Noeud<>("A");
        noeudB = new Noeud<>("B");
        noeudC = new Noeud<>("C");
    }

    @Test
    public void testGetValeur() {
        assertEquals("A", noeudA.getValeur());
        assertEquals("B", noeudB.getValeur());
        assertEquals("C", noeudC.getValeur());
    }

    @Test
    public void testAjouterVoisin() {
        assertEquals(0, noeudA.getVoisins().size());

        noeudA.ajouterVoisin(noeudB);
        assertEquals(1, noeudA.getVoisins().size());
        assertTrue(noeudA.getVoisins().contains(noeudB));

        noeudA.ajouterVoisin(noeudC);
        assertEquals(2, noeudA.getVoisins().size());
        assertTrue(noeudA.getVoisins().contains(noeudC));
    }
}

