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
public class Matière {
 
    private int idMatière;
    private String matière;
    private boolean fondamental;
    private int coeficient;
    private Enseignant ensResponsable;

    public Matière() {
    }

    
    public Matière(int idMatière, String matière, boolean fondamental, int coeficient, Enseignant ensResp) {
        this.idMatière = idMatière;
        this.matière = matière;
        this.fondamental = fondamental;
        this.coeficient = coeficient;
        this.ensResponsable = ensResp;
    }

    public int getIdMatière() {
        return idMatière;
    }

    public void setIdMatière(int idMatière) {
        this.idMatière = idMatière;
    }

    public String getMatière() {
        return matière;
    }

    public void setMatière(String matière) {
        this.matière = matière;
    }

    public boolean isFondamental() {
        return fondamental;
    }

    public void setFondamental(boolean fondamental) {
        this.fondamental = fondamental;
    }

    public int getCoeficient() {
        return coeficient;
    }

    public void setCoeficient(int coeficient) {
        this.coeficient = coeficient;
    }

    public Enseignant getEnsResponsable() {
        return ensResponsable;
    }

    public void setEnsResponsable(Enseignant ensResponsable) {
        this.ensResponsable = ensResponsable;
    }
    
    
    
}
