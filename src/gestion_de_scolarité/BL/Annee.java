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
public class Annee {
    
    private int idAnnee;
    private String Annee;

    public Annee() {
    }

    public Annee(int idAnnee, String Annee) {
        this.idAnnee = idAnnee;
        this.Annee = Annee;
    }

    public int getIdAnnee() {
        return idAnnee;
    }

    public void setIdAnnee(int idAnnee) {
        this.idAnnee = idAnnee;
    }

    public String getAnnee() {
        return Annee;
    }

    public void setAnnee(String Annee) {
        this.Annee = Annee;
    }
    
    
    
}
