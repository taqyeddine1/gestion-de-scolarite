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
public class Niveau {
    
    private int idNiveau;
    private short niveau;

    public Niveau() {
    }

    public Niveau(int idNiveau, short niveau) {
        this.idNiveau = idNiveau;
        this.niveau = niveau;
    }

    public int getIdNiveau() {
        return idNiveau;
    }

    public void setIdNiveau(int idNiveau) {
        this.idNiveau = idNiveau;
    }

    public short getNiveau() {
        return niveau;
    }

    public void setNiveau(short niveau) {
        this.niveau = niveau;
    }
    
    
    
}
