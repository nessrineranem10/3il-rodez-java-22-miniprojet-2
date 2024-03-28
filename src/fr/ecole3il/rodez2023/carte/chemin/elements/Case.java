package fr.ecole3il.rodez2023.carte.chemin.elements;

import java.util.Objects;

/**
 * La classe Case représente une case sur une carte, caractérisée par une tuile et des coordonnées (x, y).
 * Elle permet de conserver la position (x, y) d'une tuile sur une carte.
 */
public class Case {
    private Tuile tuile; // La tuile associée à cette case
    private int x; // Coordonnée x de la case sur la carte
    private int y; // Coordonnée y de la case sur la carte

    /**
     * Construit une nouvelle case avec la tuile spécifiée et les coordonnées (x, y) spécifiées.
     * @param tuile La tuile associée à cette case.
     * @param x La coordonnée x de la case sur la carte.
     * @param y La coordonnée y de la case sur la carte.
     */
    public Case(Tuile tuile, int x, int y) {
        this.tuile = tuile;
        this.x = x;
        this.y = y;
    }

    /**
     * Récupère la tuile associée à cette case.
     * @return La tuile associée à cette case.
     */
    public Tuile getTuile() {
        return tuile;
    }

    /**
     * Récupère la coordonnée x de la case sur la carte.
     * @return La coordonnée x de la case sur la carte.
     */
    public int getX() {
        return x;
    }

    /**
     * Récupère la coordonnée y de la case sur la carte.
     * @return La coordonnée y de la case sur la carte.
     */
    public int getY() {
        return y;
    }

    /**
     * Renvoie une représentation textuelle de la case sous forme de chaîne de caractères.
     * @return Une chaîne de caractères représentant la case avec sa tuile et ses coordonnées.
     */
    @Override
    public String toString() {
        return "Case [tuile=" + tuile + ", x=" + x + ", y=" + y + "]";
    }
    /**
     * Méthode de comparaison pour déterminer l'égalité de cette Case avec un autre objet.
     * Deux objets Case sont considérés égaux si et seulement si ils ont les mêmes coordonnées (x, y)
     * et contiennent la même tuile.
     *
     * @param o L'objet à comparer avec cette Case pour l'égalité.
     * @return true si les objets sont égaux, false sinon.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Case aCase = (Case) o;
        return x == aCase.x && y == aCase.y && tuile == aCase.tuile;
    }

    /**
     * Méthode de calcul du code de hachage pour cette Case.
     * Le code de hachage est calculé en fonction des attributs 'tuile', 'x' et 'y' de la Case.
     *
     * @return Le code de hachage calculé pour cette Case.
     */
    @Override
    public int hashCode() {
        return Objects.hash(tuile, x, y);
    }

}

