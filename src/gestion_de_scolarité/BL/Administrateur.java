/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_de_scolarité.BL;

import gestion_de_scolarité.DAL.DatabaseConnection;
import gestion_de_scolarité.PL.AdminAccount.AdminDashboard;
import gestion_de_scolarité.PL.MessageDialog;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
    
    
   /**
    * this method to get administrator users login into the system
    * @param username
    * @param password
    * @return 
    */
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
        System.out.println("create dc and list object");
        
         try{
             
            
            dc.stmt= dc.conn.createStatement();
            System.out.println("create the statement");
            dc.rs= dc.stmt.executeQuery(query);
            System.out.println("execute the query");
            while(dc.rs.next()){
                System.out.println("inisialize the bufferedImage");
                
                    byte[] img = dc.rs.getBytes(10); 
                    System.out.println("got the image as byte");
                    
                
                java.sql.Date dbSqlDate = dc.rs.getDate(5); // this two line to convert sqlDate to javaDate
                java.util.Date dbSqlDateConverted = new java.util.Date(dbSqlDate.getTime()); 
                list.add(new Person(dc.rs.getInt(1),dc.rs.getString(2),dc.rs.getString(3),dc.rs.getString(4), dc.rs.getDate(5), dc.rs.getString(6), dc.rs.getString(7), dc.rs.getString(8),dc.rs.getBoolean(9), img));
                System.out.println("getPerson  added successfully");
            }
         }catch(SQLException e){
          System.out.println("message error from getPerson:" + e);
         }
         return list;
       
   }
   
   /**
    * this method help us to get the years available in the annee table
    * @param annee
    * @return 
    */
   public int getIdAnnee(String annee){
       
       int id=0;
       
       String query = "select idAnnee from Annee where annee = '" + annee +"';";
       
       DatabaseConnection dc = new DatabaseConnection();
       try{
             
            
            dc.stmt= dc.conn.createStatement();
            System.out.println("create the statement in getIdAnnee()");
            dc.rs= dc.stmt.executeQuery(query);
            System.out.println("execute the query in getIdAnnee()");
            while(dc.rs.next()){
                id = dc.rs.getInt(1);
            }
         }catch(SQLException e){
          System.out.println("message error from getIdAnnee():" + e);
         }
       
       return id;
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
   public void inscrerEleve(String nom, String prenom, String adress, Date dateNai, String lieuNai, String email, String numPhone, boolean sex, short niveau, String parentPhone, String imagePath){
       DatabaseConnection dc = new DatabaseConnection();
       DatabaseConnection dc2 = new DatabaseConnection();
       DatabaseConnection dc3 = new DatabaseConnection();
       DatabaseConnection eleve = new DatabaseConnection();
       ArrayList<Person> persons = new ArrayList<>();
       int idAnnee=0;
       
       //this 3 line to reformat the date in turn it to string to insert it in DB from java as DB
       String pattern = "yyyy-MM-dd";
       SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
       String date = simpleDateFormat.format(dateNai);
       String currentDate = simpleDateFormat.format(new Date());
       String year = String.valueOf(new Date().getYear()+1900);
       
       
       String queryInsert = "insert into Person(nom, prenom, adress, dateDeNaissance, lieuDeNaissance, sex, email, numPhone, photos)"
               + "  values(?,?,?,?,?,?,?,?,?);";
       String queryInsertA = "insert ignore into Annee(annee) values(?);";
       String queryInsert2 = "insert into Classe(idEleve, idNiveau, idAnnee) values(?,?,?);";
       
       String queryInsert3 = "insert into Eleve(idEleve, parentPhone) values(?,?);";
       
       try {
           InputStream img = new FileInputStream(new File(imagePath));
           System.out.println(" path: "+ imagePath);
           
          dc.ps = dc.conn.prepareStatement(queryInsert);
          dc2.ps = dc2.conn.prepareStatement(queryInsert2);
          dc3.ps = dc3.conn.prepareStatement(queryInsertA);
          
          
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
          dc.ps.setBlob(9, img);
          System.out.println("photo bien ajouter!");
          dc.ps.setString(4, date);
          System.out.println("date bien ajouter!");
                    
          dc3.ps.setString(1, year);
          System.out.println("Annee dans Annee bien ajouter!");
          
          dc.ps.executeUpdate();
          System.out.println("dc.ps est execute !");
          dc3.ps.executeUpdate();
          System.out.println("execute dc3 bien ajouter!");
          //call function that allows us get the id of the specifique person;
           persons = getPerson();
       for (int i = 0; i < persons.size(); i++) {
           Person get = persons.get(i);
           System.out.println("nom "+i + " is:"+ get.getNom());
           System.out.println("prenom "+i + " is:"+ get.getPrenom());
           System.out.println("birthday"+i+ " is:"+get.getDateDeNaissance());
           
       }
          //get the id of the person to insert it into the Eleve table
          boolean idExist = false;
          int idEleve = 0;
          int i = 0;
          while(i < persons.size() && idExist == false){
              String date2 = simpleDateFormat.format(persons.get(i).getDateDeNaissance());
              System.out.println("nom :"+ nom + " prenom :"+ prenom + " birthday :"+ date + " birthday : " +persons.get(i).getDateDeNaissance());
              if((persons.get(i).getNom()).equals(nom) && (persons.get(i).getPrenom()).equals(prenom) && date.equals(date2)){
                  idExist = true;
                  idEleve = persons.get(i).getIdPerson();
                  System.out.println("ideleve from i wish :" + idEleve);
                  System.out.println("idExist :" + idExist);
              }
              i++;
          }
           if (idExist == true) {
               eleve.ps = dc.conn.prepareStatement(queryInsert3);
               eleve.ps.setInt(1, idEleve);
          System.out.println("idEleve bien ajouter!");
          
          eleve.ps.setString(2, parentPhone);
          System.out.println("parentPhone bien ajouter!");
          
          eleve.ps.executeUpdate();
          System.out.println("ps execute successfully!");
           }else{
               MessageDialog msg = new MessageDialog();
               msg.messageText.setText("idExist = false and idEleve = " + idEleve );
               msg.setVisible(true);
           }
           
           System.out.println("new Date()" + new Date());
           System.out.println("the current day : "+currentDate);
           System.out.println("current année : " + year);
           idAnnee = getIdAnnee(currentDate);
           System.out.println("getIdAnnee bien executer");
           dc2.ps.setInt(1, idEleve);
           System.out.println("idEleve  bien ajouter dans Classe");
           dc2.ps.setInt(2, niveau);
           System.out.println("idNiveau  bien ajouter dans Classe");
           dc2.ps.setInt(3, idAnnee);
           System.out.println("idAnnee  bien ajouter dans Classe");
           
           dc2.ps.executeUpdate();
          System.out.println("dc2 bien executer");
          
          System.out.println("insert eleve successfully !");
       } catch (Exception e) {
           System.out.println("message error:  from insertEleve : "+e);
       }
      
       
       
       
   }
   
   /**
    * this method get the id of the  student list as result set to put it in a table
     * @param annee
    * @param niveau
     * @param order
    * @return 
    */
   public ArrayList<Integer> getIdEleve(String annee, int niveau, String order){
       
        
        ArrayList<Integer> idList = new ArrayList<>();
        
        String sqlCommand = (order.equals("Aleatoire"))? "rand()" : "nom";
        System.out.println("sqlCommand :" +sqlCommand);
        String query2 = "select c.idEleve "
                        + "from Annee as a right join Classe as c on a.idAnnee = c.idAnnee left join Eleve as e on c.idEleve = e.idEleve left join Person as p on e.idEleve = p.idPerson join Niveau as n on n.idNiveau = c.idNiveau "
                        + "where n.niveau = " + niveau + " and ClasseNb is null and Left(a.annee,4) = '" + annee +"' order by "+ sqlCommand +";";
        DatabaseConnection dc = new DatabaseConnection();
        try {
            dc.stmt= dc.conn.createStatement();
            System.out.println("create the statement in getEleve");
            dc.rs= dc.stmt.executeQuery(query2);
            System.out.println("execute query in getEleve");
            while(dc.rs.next()){
                System.out.println("enter to while boucle in getIdEleve");
                 idList.add(dc.rs.getInt(1));
                 System.out.println(" From getEleve() nom: " + dc.rs.getInt(1));
               }
        } catch (SQLException ex) {
            System.out.println("message from : "+ex);
        }
       return idList;  
   }
   
   /**
    * this method allow us to diviser les eleves inscrer on groupe 
    * @param annee
    * @param niveau
    * @param nbClasse
    * @param order
    * @param nbEleve
    * @param nbClasseMax 
    */
   public void diviser(String annee, int niveau, int nbClasse,String order, int nbEleve, int nbClasseMax){
       
       DatabaseConnection dc = new DatabaseConnection();
       DatabaseConnection dc2 = new DatabaseConnection();
       ArrayList<Integer> idsEleve = new ArrayList<>();
       idsEleve = getIdEleve(annee, niveau , order);
       System.out.println(" From diviser niveau = " + niveau + " ****** order = " + order );
       System.out.println("call the getIdEleve successfully!");
       
       
       if (nbClasse!=0) {
               System.out.println("entre la condition general!");
               int nEleve = 0;
               nEleve = idsEleve.size()/nbClasse;
               
           for (int i = 1; i <= nbClasse; i++) {
               
               System.out.println("enter la boucle for " + i);
               System.out.println("le nomber de les éleves: " + idsEleve.size());
               int j =0, k=0;
               //if the value of (nEleve mod nbClasse ) less number of classe we are gonna add 1 to nEleve
               while (k<nEleve) { 
                   String query =  "update Classe set classeNb = ? , salle = ? "
                                + " where idAnnee in (select idAnnee from Annee where Left(annee, 4) = '" + annee + "') and idNiveau in (select idNiveau from Niveau "
                                +    " where niveau = " + niveau +") and idEleve = "+idsEleve.get(0)+";";
                 String selectQuery = "select count(idClasse) from Classe "
                                + " where idAnnee in (select idAnnee from Annee where Left(annee, 4) = '" + annee + "')"
                                + " and idNiveau in (select idNiveau from Niveau where niveau = " + niveau +") and classeNb = "+i+";";
                 String query2=  "update Classe set nbEleve = ? where classeNb = "+ i + " and salle = " + i + ";";
                   try {
                       int count = 0;
                       dc.ps = dc.conn.prepareStatement(query);
                       dc2.stmt = dc2.conn.createStatement();
                       dc.ps.setInt(1, i); // classe number
                       dc.ps.setInt(2, i); // la salle
                       dc.ps.executeUpdate();
                       dc2.rs = dc2.stmt.executeQuery(selectQuery);
                       while(dc2.rs.next()){
                           count = dc2.rs.getInt(1);
                       }
                       dc2.ps = dc2.conn.prepareStatement(query2);          
                       dc2.ps.setInt(1, count ); // eleve number
                       dc2.ps.executeUpdate();
                   } catch (SQLException ex) {
                       System.out.println("message error from diviser "+ ex);
                   }
                   
                   idsEleve.remove(0);
                   k++;
               }
           }
           if (!idsEleve.isEmpty()) {
               int l = 1;
               while (!idsEleve.isEmpty()) {                   
                   String query =  "update Classe set classeNb = ? , salle = ? "
                                + " where idAnnee in (select idAnnee from Annee where Left(annee, 4) = '" + annee + "') and idNiveau in (select idNiveau from Niveau "
                                +    " where niveau = " + niveau +") and idEleve = "+idsEleve.get(0)+";";
                 String selectQuery = "select count(idClasse) from Classe "
                                + " where idAnnee in (select idAnnee from Annee where Left(annee, 4) = '" + annee + "')"
                                + " and idNiveau in (select idNiveau from Niveau where niveau = " + niveau +") and classeNb = "+l+";";
                 String query2=  "update Classe set nbEleve = ? where classeNb = "+ l + " and salle = " + l + ";";
                   try {
                       int count = 0;
                       dc.ps = dc.conn.prepareStatement(query);
                       dc2.stmt = dc2.conn.createStatement();
                       dc.ps.setInt(1, l); // classe number
                       dc.ps.setInt(2, l); // la salle
                       dc.ps.executeUpdate();
                       dc2.rs = dc2.stmt.executeQuery(selectQuery);
                       while(dc2.rs.next()){
                           count = dc2.rs.getInt(1);
                       }
                       dc2.ps = dc2.conn.prepareStatement(query2);          
                       dc2.ps.setInt(1, count ); // eleve number
                       dc2.ps.executeUpdate();
                   } catch (SQLException ex) {
                       System.out.println("message error from diviser_ClasseNb"+ ex);
                   }
                   
                   idsEleve.remove(0);
                   l++;
                   if(l == nbClasse){
                       l = 1;
                   }
               }
           }
       } else {
            int s = 0;
            int nClasses = 0;
            nClasses = idsEleve.size()/nbEleve;
         if(nbClasseMax>idsEleve.size()/nbEleve){
           
           for (int i = 1; i <= idsEleve.size()/nbEleve; i++) {
               int j =0, k=0;
               while (k < nbEleve) {                   
                 String query =  "update Classe set classeNb = ? , salle = ? "
                                + " where idAnnee in (select idAnnee from Annee where Left(annee, 4) = '" + annee + "') and idNiveau in (select idNiveau from Niveau "
                                +    " where niveau = " + niveau +") and idEleve = "+idsEleve.get(0)+";";
                 String selectQuery = "select count(idClasse) from Classe "
                                + " where idAnnee in (select idAnnee from Annee where Left(annee, 4) = '" + annee + "')"
                                + " and idNiveau in (select idNiveau from Niveau where niveau = " + niveau +") and classeNb = "+i+";";
                 String query2=  "update Classe set nbEleve = ? where classeNb = "+ i + " and salle = " + i + ";";
                   try {
                       int count = 0;
                       dc.ps = dc.conn.prepareStatement(query);
                       dc2.stmt = dc2.conn.createStatement();
                       dc.ps.setInt(1, i); // classe number
                       dc.ps.setInt(2, i); // la salle
                       dc.ps.executeUpdate();
                       dc2.rs = dc2.stmt.executeQuery(selectQuery);
                       while(dc2.rs.next()){
                           count = dc2.rs.getInt(1);
                       }
                       dc2.ps = dc2.conn.prepareStatement(query2);          
                       dc2.ps.setInt(1, count ); // eleve number
                       dc2.ps.executeUpdate();
                   } catch (SQLException ex) {
                       System.out.println("message error from diviser_ eleve per classe "+ ex);
                   }
                  
                   idsEleve.remove(0);
                   k++;
               }
               s = i;
           }
         }else{
             JOptionPane.showMessageDialog(null, "");
             MessageDialog msg = new MessageDialog();
               msg.messageText.setText("you don't have much classes!");
               msg.setVisible(true);
         }
        
         if (!idsEleve.isEmpty()) {
               int l = 1;
               while (!idsEleve.isEmpty()) {                   
                   String query =  "update Classe set classeNb = ? , salle = ? "
                                + " where idAnnee in (select idAnnee from Annee where Left(annee, 4) = '" + annee + "') and idNiveau in (select idNiveau from Niveau "
                                +    " where niveau = " + niveau +") and idEleve = "+idsEleve.get(0)+";";
                 String selectQuery = "select count(idClasse) from Classe "
                                + " where idAnnee in (select idAnnee from Annee where Left(annee, 4) = '" + annee + "')"
                                + " and idNiveau in (select idNiveau from Niveau where niveau = " + niveau +") and classeNb = "+(s+1)+";";
                 String query2=  "update Classe set nbEleve = ? where classeNb = "+ (s+1) + " and salle = " + (s+1) + ";";
                   try {
                       int count = 0;
                       dc.ps = dc.conn.prepareStatement(query);
                       dc2.stmt = dc2.conn.createStatement();
                       dc.ps.setInt(1, s+1); // classe number
                       dc.ps.setInt(2, s+1); // la salle
                       dc.ps.executeUpdate();
                       dc2.rs = dc2.stmt.executeQuery(selectQuery);
                       while(dc2.rs.next()){
                           count = dc2.rs.getInt(1);
                       }
                       dc2.ps = dc2.conn.prepareStatement(query2);          
                       dc2.ps.setInt(1, count ); // eleve number
                       dc2.ps.executeUpdate();
                   } catch (SQLException ex) {
                       System.out.println("message error from diviser "+ ex);
                   }
                   
                   idsEleve.remove(0);
                   l++;
                   if(l == nClasses){
                       l = 1;
                   }
               }
           }
         
       }
       
   }
  
   public int classeDesponible(){
           String query = "select count(*) from Classe where classeNb is not null";
           DatabaseConnection dc = new DatabaseConnection();
           int nbClasse = 0;
           try {
           
               dc.stmt = dc.conn.createStatement();
               dc.rs = dc.stmt.executeQuery(query);
               while(dc.rs.next()){
                   nbClasse = dc.rs.getInt(1);
               }
       } catch (Exception e) {
       }
           return  (35-nbClasse);
       }
}
