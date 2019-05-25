/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_de_scolarité.PL.EnseignantAccount.LesAnnances;

import java.util.Date;

/**
 *
 * @author slimane
 */
public class AnnanceModèl {
    
 private Date date;   
 private String status;
 private String categorie;
 private String titre;
 private String detail;

    public AnnanceModèl(Date date, String status, String categorie, String titre, String detail) {
        this.date = date;
        this.status = status;
        this.categorie = categorie;
        this.titre = titre;
        this.detail = detail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
