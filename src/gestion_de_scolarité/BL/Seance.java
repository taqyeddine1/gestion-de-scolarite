/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_de_scolarité.BL;

/**
 *
 * @author slimane
 */
public class Seance {
    
    private int idSeance;
    private int numSeance;
    private Matière matiere;

    public Seance() {
    }

    public Seance(int idSeance, int numSeance, Matière matiere) {
        this.idSeance = idSeance;
        this.numSeance = numSeance;
        this.matiere = matiere;
    }

    public int getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(int idSeance) {
        this.idSeance = idSeance;
    }

    public int getNumSeance() {
        return numSeance;
    }

    public void setNumSeance(int numSeance) {
        this.numSeance = numSeance;
    }

    public Matière getMatiere() {
        return matiere;
    }

    public void setMatiere(Matière matiere) {
        this.matiere = matiere;
    }
    
    
    
    
}
