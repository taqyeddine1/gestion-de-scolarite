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
public class Eleve extends Person{
    
    float moyenneGeneral;

    public Eleve() {
    }

    public Eleve(float moyenneGeneral, int idPerson, String nom, String prenom, String address, Date dateDeNaissance, String lieuDeNaissance, String email, String phone, boolean sex) {
        super(idPerson, nom, prenom, address, dateDeNaissance, lieuDeNaissance, email, phone, sex);
        this.moyenneGeneral = moyenneGeneral;
    }

    public float getMoyenneGeneral() {
        return moyenneGeneral;
    }

    public void setMoyenneGeneral(float moyenneGeneral) {
        this.moyenneGeneral = moyenneGeneral;
    }
    
    
    
}
