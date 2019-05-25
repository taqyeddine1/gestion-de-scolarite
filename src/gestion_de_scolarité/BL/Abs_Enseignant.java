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
public class Abs_Enseignant {
    
    private Enseignant enseignant;
    private Seance seance;
    private Date date;
    private boolean siJustifier;

    public Abs_Enseignant() {
    }

    public Abs_Enseignant(Enseignant enseignant, Seance seance, Date date, boolean siJustifier) {
        this.enseignant = enseignant;
        this.seance = seance;
        this.date = date;
        this.siJustifier = siJustifier;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    public Seance getSeance() {
        return seance;
    }

    public void setSeance(Seance seance) {
        this.seance = seance;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isSiJustifier() {
        return siJustifier;
    }

    public void setSiJustifier(boolean siJustifier) {
        this.siJustifier = siJustifier;
    }
    
    
    
}
