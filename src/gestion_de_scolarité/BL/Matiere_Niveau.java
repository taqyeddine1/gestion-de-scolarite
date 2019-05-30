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
public class Matiere_Niveau {
    
    private Matière matiere;
    private Niveau niveau;

    public Matiere_Niveau() {
    }

    public Matiere_Niveau(Matière matiere, Niveau niveau) {
        this.matiere = matiere;
        this.niveau = niveau;
    }

    public Matière getMatiere() {
        return matiere;
    }

    public void setMatiere(Matière matiere) {
        this.matiere = matiere;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }
    
    
    
}
