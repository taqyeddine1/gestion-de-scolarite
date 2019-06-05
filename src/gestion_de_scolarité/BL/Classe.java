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
public class Classe {
    
    private int idClasse;
    private String classe;
    private String salle;
    private int nbEleve;
    private Enseignant enseignantResponsable;
    private Eleve eleve;
    private Niveau niveau;
    private Annee annee;

    public Classe() {
    }

    public Classe(int idClasse, String classe, String salle, int nbEleve, Enseignant enseignantResponsable, Eleve eleve, Niveau niveau, Annee annee) {
        this.idClasse = idClasse;
        this.classe = classe;
        this.salle = salle;
        this.nbEleve = nbEleve;
        this.enseignantResponsable = enseignantResponsable;
        this.eleve = eleve;
        this.niveau = niveau;
        this.annee = annee;
    }

    public int getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(int idClasse) {
        this.idClasse = idClasse;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    public int getNbEleve() {
        return nbEleve;
    }

    public void setNbEleve(int nbEleve) {
        this.nbEleve = nbEleve;
    }

    public Enseignant getEnseignantResponsable() {
        return enseignantResponsable;
    }

    public void setEnseignantResponsable(Enseignant enseignantResponsable) {
        this.enseignantResponsable = enseignantResponsable;
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
