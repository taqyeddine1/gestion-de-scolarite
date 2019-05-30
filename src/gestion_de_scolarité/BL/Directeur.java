/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_de_scolarité.BL;

import java.util.Date;

/**
 *
 * @author slimane
 */
public class Directeur extends Person{
    
    private String username;
    private String password;

    public Directeur() {
    }

    public Directeur(String username, String password, int idPerson, String nom, String prenom, String address, Date dateDeNaissance, String lieuDeNaissance, String email, String phone, boolean sex) {
        super(idPerson, nom, prenom, address, dateDeNaissance, lieuDeNaissance, email, phone, sex);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
    
}
