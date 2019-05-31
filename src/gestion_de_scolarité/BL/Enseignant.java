/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_de_scolarité.BL;

import gestion_de_scolarité.DAL.DatabaseConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author slimane
 */
public class Enseignant extends Person{
    
    private int nbClasse;
    private Matière matière;
    private ArrayList<Classe> classes;
    private String username;
    private String password;

    public Enseignant() {
    }
    
    public Enseignant(int nbClasse, Matière matière, ArrayList<Classe> classes, String username, String password, int idPerson, String nom, String prenom, String address, Date dateDeNaissance, String lieuDeNaissance, String email, String phone, boolean sex) {
        super(idPerson, nom, prenom, address, dateDeNaissance, lieuDeNaissance, email, phone, sex);
        this.nbClasse = nbClasse;
        this.matière = matière;
        this.classes = classes;
        this.username = username;
        this.password = password;
    }

    public int getNbClasse() {
        return nbClasse;
    }

    public void setNbClasse(int nbClasse) {
        this.nbClasse = nbClasse;
    }

    public Matière getMatière() {
        return matière;
    }

    public void setMatière(Matière matière) {
        this.matière = matière;
    }

    public ArrayList<Classe> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<Classe> classes) {
        this.classes = classes;
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
    
    public boolean login(String username, String password){
       DatabaseConnection dc = new DatabaseConnection();
       boolean access = false;
       try{ 
            String sql = "select username, uPassword from Enseignant "
                    + "where username = '" + username + "' and uPassword = '" + password +"';";
            dc.stmt= dc.conn.createStatement();   
            dc.rs= dc.stmt.executeQuery(sql);
            
            while(dc.rs.next()){
                access = true;
            }
            
         }catch(SQLException e){
          System.out.print("error in login() Adminstrateur's Class .");
         }
       
       return access;
   }
    
    
}
