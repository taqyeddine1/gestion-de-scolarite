/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_de_scolarité.BL;

import gestion_de_scolarité.DAL.DatabaseConnection;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
/**
 *
 * @author slimane
 */
public class Administrateur extends Person{
    
    private String username;
    private String password;

    public Administrateur() {
    }

    public Administrateur(String username, String password, int idPerson, String nom, String prenom, String address, Date dateDeNaissance, String lieuDeNaissance, String email, String phone, boolean sex, Icon photos) {
        super(idPerson, nom, prenom, address, dateDeNaissance, lieuDeNaissance, email, phone, sex, photos);
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
    
    
   
   public boolean login(String username, String password){
       DatabaseConnection dc = new DatabaseConnection();
       boolean access = false;
       try{ 
            String sql = "select username, uPassword from Administrateur "
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
   
   /**
    * this method return a array list of person object from the database
    * @return 
    */
   public ArrayList<Person> getPerson(){
       
       String query = "Select * from Person;";
       
       DatabaseConnection dc = new DatabaseConnection();
        ArrayList<Person> list= new ArrayList<>();
        
         try{
             
            
            dc.stmt= dc.conn.createStatement();
            dc.rs= dc.stmt.executeQuery(query);
            while(dc.rs.next()){
                BufferedImage im = null;
                try {
                    im = ImageIO.read(dc.rs.getBinaryStream(10));
                } catch (IOException ex) {
                    Logger.getLogger(Administrateur.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                java.sql.Date dbSqlDate = dc.rs.getDate(5); // this two line to convert sqlDate to javaDate
                java.util.Date dbSqlDateConverted = new java.util.Date(dbSqlDate.getTime()); 
                list.add(new Person(dc.rs.getInt(1),dc.rs.getString(2),dc.rs.getString(3),dc.rs.getString(4), dc.rs.getDate(5), dc.rs.getString(6), dc.rs.getString(7), dc.rs.getString(8),dc.rs.getBoolean(9),new ImageIcon(im)));
                System.out.println("getPerson successfully");
            }
         }catch(SQLException e){
          System.out.println("message error :" + e);
         }
         return list;
       
   }
   
   /**
    * this method used to insert new student into the database
    * @param nom
    * @param prenom
    * @param adress
    * @param dateNai
    * @param lieuNai
    * @param email
    * @param numPhone
    * @param sex
    * @param niveau 
    * @param parentPhone 
    */
   public void inscrerEleve(String nom, String prenom, String adress, Date dateNai, String lieuNai, String email, String numPhone, boolean sex, short niveau, String parentPhone){
       DatabaseConnection dc = new DatabaseConnection();
       DatabaseConnection dc2 = new DatabaseConnection();
       DatabaseConnection eleve = new DatabaseConnection();
       ArrayList<Person> persons = new ArrayList<>();
       
       //this 3 line to reformat the date in turn it to string to insert it in DB from java as DB
       String pattern = "yyyy-MM-dd";
       SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
       String date = simpleDateFormat.format(dateNai);
       
       
       String queryInsert = "insert into Person(nom, prenom, adress, dateDeNaissance, lieuDeNaissance, sex, email, numPhone)"
               + "  values(?,?,?,?,?,?,?,?);";
       String queryInsert2 = "insert into Niveau(niveau) values (?);";
       
       String queryInsert3 = "insert into Eleve(idEleve, parentPhone) values(?,?);";
       
       try {
          dc.ps = dc.conn.prepareStatement(queryInsert);
          dc2.ps = dc2.conn.prepareStatement(queryInsert2);
          System.out.println("begin insertion :");
          dc.ps.setString(1, nom);
          System.out.println("nom bien ajouter!");
          dc.ps.setString(2, prenom);
          System.out.println("prenom bien ajouter!");
          dc.ps.setString(3, adress);
          System.out.println("adress bien ajouter!");
          
          dc.ps.setString(5, lieuNai);
          System.out.println("lieu bien ajouter!");
          dc.ps.setBoolean(6, sex);
          System.out.println("email bien ajouter!");
          dc.ps.setString(7, email);
          System.out.println("phone bien ajouter!");
          dc.ps.setString(8, numPhone);
          System.out.println("gender bien ajouter!");
          dc.ps.setString(4, date);
          System.out.println("date bien ajouter!");
          
          dc2.ps.setShort(1, niveau);
          System.out.println("niveau bien ajouter!");
          
          dc.ps.executeUpdate();
          dc2.ps.executeUpdate();
          
          //get the id of the person to insert it into the Eleve table
          boolean idExist = false;
          int idEleve = 0;
          int i = 0;
          while(i<persons.size() && idExist == false){
              if(persons.get(i).getNom().equals(nom) && persons.get(i).getPrenom().equals(prenom) && persons.get(i).getDateDeNaissance().equals(dateNai)){
                  idExist = true;
                  idEleve = persons.get(i).getIdPerson();
              }
              i++;
          }
          
          eleve.ps.setInt(1, idEleve);
          System.out.println("idEleve bien ajouter!");
          
          eleve.ps.setString(2, parentPhone);
          System.out.println("parentPhone bien ajouter!");
          
          eleve.ps.executeUpdate();
          
          System.out.println("insert eleve successfully !");
       } catch (Exception e) {
       }
      
       
       
       
   }
    
}
