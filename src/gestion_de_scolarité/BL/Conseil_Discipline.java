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
public class Conseil_Discipline {
    
    private int idConseil;
    private Rapport rapport;
    private Date date;
    private Directeur directeur;
    private Enseignant enseignantResponsable;

    public Conseil_Discipline() {
    }

    public Conseil_Discipline(int idConseil, Rapport rapport, Date date, Directeur directeur, Enseignant enseignantResponsable) {
        this.idConseil = idConseil;
        this.rapport = rapport;
        this.date = date;
        this.directeur = directeur;
        this.enseignantResponsable = enseignantResponsable;
    }

    public int getIdConseil() {
        return idConseil;
    }

    public void setIdConseil(int idConseil) {
        this.idConseil = idConseil;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Directeur getDirecteur() {
        return directeur;
    }

    public void setDirecteur(Directeur directeur) {
        this.directeur = directeur;
    }

    public Enseignant getEnseignantResponsable() {
        return enseignantResponsable;
    }

    public void setEnseignantResponsable(Enseignant enseignantResponsable) {
        this.enseignantResponsable = enseignantResponsable;
    }

    public Rapport getRapport() {
        return rapport;
    }

    public void setRapport(Rapport rapport) {
        this.rapport = rapport;
    }
    
     
    
    
    
}
