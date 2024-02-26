package fr.ecole3il.rodez2023.carte.elements;


import java.util.ArrayList;
import java.util.List;


/**
 * La classe Noeud représente un nœud générique dans une structure de graphe.
 *
 * @param <E> le type générique pour la valeur du nœud.
 */

public class Noeud<E> {
    private E valeur;
    private List<Noeud<E>> voisins;

    /**
     * Constructeur de la classe Noeud.
     *
     * @param valeur la valeur du nœud.
     */
    public Noeud(E valeur) {
        this.valeur = valeur;
        this.voisins = new ArrayList<>();
    }

    /**
     * Méthode pour obtenir la valeur du nœud.
     *
     * @return la valeur du nœud.
     */

    public E getValeur() {
        return valeur;
    }


    /**
     * Méthode pour obtenir la liste des nœuds voisins.
     *
     * @return la liste des nœuds voisins.
     */

    public List<Noeud<E>> getVoisins() {
        return voisins;
    }

    /**
     * Méthode pour ajouter un nœud voisin à la liste des voisins du nœud actuel.
     *
     * @param voisin le nœud voisin à ajouter.
     */
    public void ajouterVoisin(Noeud<E> voisin){
            voisins.add(voisin);
    }

}