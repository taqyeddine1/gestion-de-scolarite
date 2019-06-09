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
    
    private int idMatiere;
    private int idNiveau;
    
    public Matiere_Niveau() {
    }

    public Matiere_Niveau(int idMatiere, int idNiveau) {
        this.idMatiere = idMatiere;
        this.idNiveau = idNiveau;
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

    public int getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(int idMatiere) {
        this.idMatiere = idMatiere;
    }

    public int getIdNiveau() {
        return idNiveau;
    }

    public void setIdNiveau(int idNiveau) {
        this.idNiveau = idNiveau;
    }
    
    
    
}
