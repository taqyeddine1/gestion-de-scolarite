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
public class Eleve_Niveau_Annee {
    
    private Eleve eleve;
    private Niveau niveau;
    private Annee annee;

    public Eleve_Niveau_Annee() {
    }

    public Eleve_Niveau_Annee(Eleve eleve, Niveau niveau, Annee annee) {
        this.eleve = eleve;
        this.niveau = niveau;
        this.annee = annee;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public Annee getAnnee() {
        return annee;
    }

    public void setAnnee(Annee annee) {
        this.annee = annee;
    }
     
}
