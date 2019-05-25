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
public class Abs_Eleve {
    
    private Eleve eleve;
    private Seance seance;
    private Date date;
    private boolean siJustifier;

    public Abs_Eleve() {
    }

    public Abs_Eleve(Eleve eleve, Seance seance, Date date, boolean siJustifier) {
        this.eleve = eleve;
        this.seance = seance;
        this.date = date;
        this.siJustifier = siJustifier;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
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
