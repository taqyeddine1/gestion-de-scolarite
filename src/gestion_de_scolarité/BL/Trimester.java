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
public class Trimester {
    private int idTrimester;
    private String trimester;

    public Trimester() {
    }

    public Trimester(int idTrimester, String trimester) {
        this.idTrimester = idTrimester;
        this.trimester = trimester;
    }

    public int getIdTrimester() {
        return idTrimester;
    }

    public void setIdTrimester(int idTrimester) {
        this.idTrimester = idTrimester;
    }

    public String getTrimester() {
        return trimester;
    }

    public void setTrimester(String trimester) {
        this.trimester = trimester;
    }
     
    
}
