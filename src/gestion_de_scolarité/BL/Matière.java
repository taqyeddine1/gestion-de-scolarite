/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_de_scolarité.BL;

import java.util.ArrayList;

/**
 *
 * @author slimane
 */
public class Matière {
 
    private int idMatière;
    private String matière;
    private boolean fondamental;
    private int coeficient;
    private int ensResponsable;
    
    private ArrayList<Integer> niveau;

    public Matière() {
    }

    public Matière(int idMatière,String matière, boolean fondamental, int coeficient, ArrayList<Integer> niveau) {
        this.idMatière = idMatière;
        this.matière = matière;
        this.fondamental = fondamental;
        this.coeficient = coeficient;
        this.niveau = niveau;
    }

    
    
    public Matière(String matière, boolean fondamental, int coeficient) {
        this.matière = matière;
        this.fondamental = fondamental;
        this.coeficient = coeficient;
    }

    
    public Matière(int idMatière, String matière, boolean fondamental, int coeficient, int ensResp, ArrayList<Integer> niveau) {
        this.idMatière = idMatière;
        this.matière = matière;
        this.niveau = niveau;
        this.fondamental = fondamental;
        this.coeficient = coeficient;
        this.ensResponsable = ensResp;
    }

    public Matière(int idMatière, String matière, boolean fondamental, int coeficient) {
        this.idMatière = idMatière;
        this.matière = matière;
        this.fondamental = fondamental;
        this.coeficient = coeficient;
    }

    public ArrayList<Integer> getNiveau() {
        return niveau;
    }

    public void setNiveau(ArrayList<Integer> niveau) {
        this.niveau = niveau;
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

    public int getEnsResponsable() {
        return ensResponsable;
    }

    public void setEnsResponsable(int ensResponsable) {
        this.ensResponsable = ensResponsable;
    }
    
    
    
}
