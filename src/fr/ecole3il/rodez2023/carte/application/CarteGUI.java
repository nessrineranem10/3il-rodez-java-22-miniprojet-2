package fr.ecole3il.rodez2023.carte.application;

import fr.ecole3il.rodez2023.carte.AdaptateurAlgorithme;
import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeAEtoile;
import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeChemin;
import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeDijkstra;
import fr.ecole3il.rodez2023.carte.chemin.elements.Carte;
import fr.ecole3il.rodez2023.carte.chemin.elements.Case;
import fr.ecole3il.rodez2023.carte.chemin.elements.Chemin;
import fr.ecole3il.rodez2023.carte.chemin.elements.Tuile;
import fr.ecole3il.rodez2023.carte.manipulateurs.GenerateurCarte;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * @author p.roquart
 * voilà
 * donc
 * c'est la classe finale pour le gui quoi
 * enfin je sais pas
 * moi j'aime pas le java
 */
public class CarteGUI extends JFrame {
	private Carte carte;
	private Case caseDepart;
	private Case caseArrivee;
	private AlgorithmeChemin algorithme;

	public CarteGUI(Carte carte) {
		this.carte = carte;
		this.caseDepart = null;
		this.caseArrivee = null;
		this.algorithme = new AlgorithmeDijkstra(); // Algorithme par défaut

		setTitle("Carte");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(carte.getLargeur() * 32, carte.getHauteur() * 32 + 50); // +50 pour la ComboBox
		setLocationRelativeTo(null);

		JPanel cartePanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				dessinerCarte((Graphics2D) g);
			}
		};
		cartePanel.setPreferredSize(new Dimension(carte.getLargeur() * 32, carte.getHauteur() * 32));

		JComboBox<String> algorithmeComboBox = new JComboBox<>(new String[] { "Dijkstra", "A*" });
		algorithmeComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String choix = (String) algorithmeComboBox.getSelectedItem();
				if (choix.equals("Dijkstra")) {
					algorithme = new AlgorithmeDijkstra();
				} else if (choix.equals("A*")) {
					algorithme = new AlgorithmeAEtoile();
				}
			}
		});

		add(algorithmeComboBox, BorderLayout.NORTH);
		add(cartePanel);

		cartePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = e.getX() / 32;
				int y = e.getY() / 32;

				if (caseDepart == null) {
					caseDepart = new Case(carte.getTuile(x, y), x, y);
					System.out.println("Case de départ : [" + x + ", " + y + "]");
				} else if (caseArrivee == null) {
					caseArrivee = new Case(carte.getTuile(x, y), x, y);
					System.out.println("Case d'arrivée : [" + x + ", " + y + "]");
					trouverChemin();
				} else {
					caseDepart = new Case(carte.getTuile(x, y), x, y);
					caseArrivee = null;
					System.out.println("Nouvelle case de départ : [" + x + ", " + y + "]");
				}

				cartePanel.repaint();
			}
		});
	}

	private void dessinerCarte(Graphics2D g) {
		for (int x = 0; x < carte.getLargeur(); x++) {
			for (int y = 0; y < carte.getHauteur(); y++) {
				Tuile tuile = carte.getTuile(x, y);
				BufferedImage imageTuile = getTuileImage(tuile);
				g.drawImage(imageTuile, x * 32, y * 32, null);

				if (caseDepart != null && caseDepart.getX() == x && caseDepart.getY() == y) {
					g.setColor(Color.BLUE);
					g.drawRect(x * 32, y * 32, 32, 32);
				}

				if (caseArrivee != null && caseArrivee.getX() == x && caseArrivee.getY() == y) {
					g.setColor(Color.RED);
					g.drawRect(x * 32, y * 32, 32, 32);
				}
			}
		}
/**
 * Si les cases de départ et d'arrivée sont toutes deux définies, cette méthode utilise l'algorithme spécifié pour trouver le chemin le plus court entre elles sur la carte.
 * Une fois le chemin trouvé, les cases qui le composent sont colorées en rouge dans la fenêtre graphique fournie.
 * @param g l'objet Graphics permettant de dessiner dans la fenêtre graphique
 */
		if (caseDepart != null && caseArrivee != null) {
			// Recherche du chemin le plus court
			Chemin chemin = (Chemin) AdaptateurAlgorithme.trouverChemin(algorithme, carte, caseDepart.getX(), caseDepart.getY(), caseArrivee.getX(), caseArrivee.getY());

			// Coloration des cases du chemin en rouge dans la fenêtre graphique
			g.setColor(Color.RED);
			for (Case c : chemin.getCases()) {
				g.fillRect(c.getX() * 32, c.getY() * 32, 32, 32);
			}
		}

	}

	/**
	 * Trouve et affiche le chemin le plus court entre la case de départ et la case d'arrivée sur la carte en utilisant l'algorithme spécifié.
	 * Si les cases de départ et d'arrivée sont définies, la méthode recherche le chemin le plus court entre elles.
	 * Une fois le chemin trouvé, les coordonnées des cases constituant le chemin sont affichées sur la console.
	 */
	private void trouverChemin() {
		if (caseDepart != null && caseArrivee != null) {
			// Recherche du chemin le plus court
			Chemin chemin = AdaptateurAlgorithme.trouverChemin(algorithme, carte, caseDepart.getX(), caseDepart.getY(), caseArrivee.getX(), caseArrivee.getY());

			// Affichage du chemin le plus court
			System.out.println("Chemin le plus court :");
			for (Case c : chemin.getCases()) {
				System.out.println("[" + c.getX() + ", " + c.getY() + "]");
			}
		}
	}

	private BufferedImage getTuileImage(Tuile tuile) {
		// Bon, j'ai pas eu le temps de faire les images
		// mais ça marche
		BufferedImage image = new BufferedImage(32, 32, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		switch (tuile) {
			case DESERT:
				g.setColor(Color.YELLOW);
				break;
			case MONTAGNES:
				g.setColor(Color.GRAY);
				break;
			case PLAINE:
				g.setColor(Color.GREEN);
				break;
			case FORET:
				g.setColor(Color.DARK_GRAY);
				break;
		}
		g.fillRect(0, 0, 32, 32);
		g.dispose();
		return image;
	}

	public static void main(String[] args) {
		// Créer une carte de test
		/*Tuile[][] tuiles = new Tuile[][] { { Tuile.DESERT, Tuile.MONTAGNES, Tuile.PLAINE },
				{ Tuile.FORET, Tuile.DESERT, Tuile.PLAINE }, { Tuile.PLAINE, Tuile.MONTAGNES, Tuile.FORET } };*/
		// J'ai mis ça en test
		// Donc OKLM en commentaires
		GenerateurCarte gen = new GenerateurCarte();
		Carte carte = gen.genererCarte(10, 10);//new Carte(tuiles);

		// Créer et afficher l'interface graphique
		SwingUtilities.invokeLater(() -> {
			CarteGUI carteGUI = new CarteGUI(carte);
			carteGUI.setVisible(true);
		});
	}
}