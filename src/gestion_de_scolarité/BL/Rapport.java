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
public class Rapport {
    
    private int idRapport;
    private Enseignant auteur;
    private Eleve eleve;
    private String sujet;
    private String Detail;
    private Date date;
    private boolean avecConseil;

    public Rapport() {
    }

    public Rapport(int idRapport, String sujet, String Detail, Date date, boolean avecConseil) {
        this.idRapport = idRapport;
        this.sujet = sujet;
        this.Detail = Detail;
        this.date = date;
        this.avecConseil = avecConseil;
    }

    public int getIdRapport() {
        return idRapport;
    }

    public void setIdRapport(int idRapport) {
        this.idRapport = idRapport;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String Detail) {
        this.Detail = Detail;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isAvecConseil() {
        return avecConseil;
    }

    public void setAvecConseil(boolean avecConseil) {
        this.avecConseil = avecConseil;
    }
    
    
}
