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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
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
     * @param chB
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
   public void inscrerEleve(JComboBox chB, String nom, String prenom, String adress, Date dateNai, String lieuNai, String email, String numPhone, boolean sex, short niveau, String parentPhone, String imagePath){
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
       String queryInsert2;
       if(!chB.isVisible()){
         queryInsert2 = "insert into Classe(idEleve, idNiveau, idAnnee) values(?,?,?);";
       }else{
           queryInsert2 = "insert into Classe(idEleve, idNiveau, idAnnee, classeNb, salle, nbEleve) values(?,?,?,?,?,?);";
       }
       
       
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
           
           if(chB.isVisible()){    
               int nbClasse =Integer.parseInt(""+chB.getSelectedItem().toString().charAt(1));
              dc2.ps.setInt(4, nbClasse);
              dc2.ps.setInt(5, nbClasse);
              dc2.ps.setInt(6, selectNbEleve(year, niveau, nbClasse)+1);
           }
           
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
           
           for (int i = 1; i <= idsEleve.size()/nbEleve; i++){
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
  
   /**
    * this method return the number of classe available
    * @return 
    */
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
           System.out.println("error from classeDesponible()!");
       }
           return  (35-nbClasse);
       }
   
   /**
    * this method return the number of student in one class
    * @param annee
    * @param niveau
    * @param classeNb
    * @return 
    */
   public int selectNbEleve(String annee, short niveau, int classeNb){
        DatabaseConnection dc2 = new DatabaseConnection();
        String selectQuery = "select count(idClasse) from Classe "
                                + " where idAnnee in (select idAnnee from Annee where annee = '" + annee + "')"
                                + " and idNiveau in (select idNiveau from Niveau where niveau = " + niveau +") and classeNb = "+classeNb+";";
        int count = 0;
        try {
            dc2.stmt = dc2.conn.createStatement();
            System.out.println("create statement in selectNbEleve()");
            dc2.rs = dc2.stmt.executeQuery(selectQuery);
            System.out.println("execute query from selectNbClasse()");
                       while(dc2.rs.next()){
                           System.out.println("enter boucle while in selectNbClasse()");
                           count = dc2.rs.getInt(1);
                       }
        } catch (Exception e) {
            System.out.println("message from selectNbClasse() :"+ e);
        }
        return count;
    }
   
   /**
    * this method give us the idMatiere of the chosen matiere
    * @param matiere
    * @return 
    */
   public int selectIdMatiere(String matiere){
       int idMatiere= 1;
       String query = "select idMatiere from Matiere where matiere = '" + matiere + "';";
       DatabaseConnection dc = new DatabaseConnection();
       try {
           dc.stmt = dc.conn.createStatement();
           dc.rs= dc.stmt.executeQuery(query);
           while(dc.rs.next()){
               idMatiere = dc.rs.getInt(1);
           }
       } catch (Exception e) {
           System.out.println("error from selectIdMatiere() :" +e);
       }
       
       return idMatiere;
   }
   
   /**
    * this method allow to insert new matiere into the Matiere table in DB
    * @param matiere
    * @param MatNiv 
    */
   public void ajouterMatiere(Matière matiere, ArrayList<Integer> MatNiv){
       MessageDialog msg = new MessageDialog();
       int idMatiere = 1;
       boolean siAjouter = false;
       String query = "insert into Matiere(matiere, fondamental, coefficient) values "
                      +"(?,?,?);";
       String query2 = "insert into Matiere_Niveau(idMatiere, idNiveau) values(?,?)";
       DatabaseConnection dc = new DatabaseConnection();
       DatabaseConnection dc2 = new DatabaseConnection();
       try {
           dc.ps = dc.conn.prepareStatement(query);
           dc2.ps = dc2.conn.prepareStatement(query2);
           
           dc.ps.setString(1, matiere.getMatière());
           dc.ps.setBoolean(2, matiere.isFondamental());
           dc.ps.setInt(3, matiere.getCoeficient());
           dc.ps.executeUpdate();
           
           idMatiere = selectIdMatiere(matiere.getMatière());
           for (int i = 0; i < MatNiv.size(); i++) {
               
               dc2.ps.setInt(1, idMatiere);
               dc2.ps.setInt(2, MatNiv.get(i));
               dc2.ps.executeUpdate();
           }
           
           siAjouter = true;
           
           
       } catch (Exception e) {
           System.out.println("error from ajouterMatiere() :" + e);
           
       }
       if (siAjouter) {
           msg.messageText.setText("Matière bien ajouter !");
       } else {
           msg.messageText.setText("Matière déja exist !");
       }
        msg.setVisible(true);
   }
   
   /**
    * this method get all the information about the chosen matiere
    * @param matiere
    * @return 
    */
   public Matière searchMatiere(String matiere){ 
       DatabaseConnection dc = new DatabaseConnection();
       DatabaseConnection dc2 = new DatabaseConnection();
       Matière mtr  = new Matière();
       String query = "select idMatiere, matiere, fondamental, coefficient, idEnseignantResponsable from Matiere where matiere = '"+matiere+"';";
       String query2 = "select idNiveau from Matiere_Niveau as mn left join Matiere as m on m.idMatiere = mn.idMatiere"
                     + " where m.matiere = '"+matiere+"';";
       ArrayList<Integer> idNiveau =new  ArrayList<>();
       
       try {
           
           dc2.stmt = dc2.conn.createStatement();
           dc2.rs = dc2.stmt.executeQuery(query2);
           System.out.println("execute query2");
           while(dc2.rs.next()){
               System.out.println("Enter la boucle query2");
               idNiveau.add(dc2.rs.getInt(1));
           }
           dc.stmt = dc.conn.createStatement();
           dc.rs = dc.stmt.executeQuery(query);
           System.out.println("execute query");
           while(dc.rs.next()){
               System.out.println("Enter la boucle query");
               mtr = new Matière(dc.rs.getInt(1), dc.rs.getString(2), dc.rs.getBoolean(3), dc.rs.getInt(4),dc.rs.getInt(5), idNiveau);
           }
       } catch (Exception e) {
           System.out.println("error from searchMatiere() : " +e);
       }
       return mtr;
   }
   
   /**
    * this method allow us to update module if exist in DB
    * @param mMatiere
    * @param matiere
    * @param MatNiv 
    */
   public void modifierMatiere(String mMatiere, Matière matiere, ArrayList<Integer> MatNiv){
       int idMatiere = 0;
       MessageDialog msg = new MessageDialog();
       boolean siModifier = false;
       String updateQuery = "update Matiere set matiere = ?, fondamental = ?, coefficient = ? where matiere = '" + mMatiere +"';";
       String insertQuery =  "insert into Matiere_Niveau(idMatiere, idNiveau) values(?,?)";
       String deleteQuery = "delete from Matiere_Niveau where idMatiere = (select idMatiere from Matiere where matiere = '"+mMatiere+"');";
       
       DatabaseConnection updateDB = new DatabaseConnection();
       DatabaseConnection insertDB = new DatabaseConnection();
       DatabaseConnection deleteDB = new DatabaseConnection();
       
       try {
           updateDB.ps = updateDB.conn.prepareStatement(updateQuery);
           insertDB.ps = insertDB.conn.prepareStatement(insertQuery);
           deleteDB.ps = deleteDB.conn.prepareStatement(deleteQuery);
           
           deleteDB.ps.executeUpdate();
           
           updateDB.ps.setString(1, matiere.getMatière());
           updateDB.ps.setBoolean(2, matiere.isFondamental());
           updateDB.ps.setInt(3, matiere.getCoeficient());
           updateDB.ps.executeUpdate();
           idMatiere = selectIdMatiere(matiere.getMatière());
           for (int i = 0; i < MatNiv.size(); i++) {
               
               insertDB.ps.setInt(1, idMatiere);
               insertDB.ps.setInt(2, MatNiv.get(i));
               insertDB.ps.executeUpdate();
           }
           
           siModifier = true;
       } catch (Exception e) {
           System.out.println("error from modifierMatiere : " + e );
       }
       if (siModifier) {
           msg.messageText.setText("Matiere bien modifier");
       } else {
           msg.messageText.setText("Matiere ne pas modifier!");
       }
       msg.setVisible(true);
       
           
   }
    
   /**
    * this method allow to delete a module 'matiere' if exist in DB
    * @param matiere 
    */
   public boolean deleteMatiere(String matiere){
       boolean isDeleted = false;
       String deleteQuery = "delete from Matiere where matiere = ? ;";
       DatabaseConnection deleteDb = new DatabaseConnection();
       try {
           deleteDb.ps = deleteDb.conn.prepareStatement(deleteQuery);
           deleteDb.ps.setString(1, matiere);
           deleteDb.ps.executeUpdate();
           isDeleted = true;
       } catch (Exception e) {
           System.out.println("error from deleteMatiere() : " + e);
       }
       
       return isDeleted;
   }
   
   /**
    * the method get an arrayList of 'Matière'
    * @param niveau
    * @return ArrayList<Matière>
    */
   public ArrayList<Matière> selectMatiere(int niveau){
       ArrayList<Matière> listMatiere = new ArrayList<>();
       DatabaseConnection dc = new DatabaseConnection();
       Matière mtr  = new Matière();
       String query = null;
        if (niveau == 0) {
            query = "select m.idMatiere as id, matiere, fondamental as 'Si fondamental', coefficient, concat(p.nom,' ',p.prenom) as 'Enseignant responsable' from Matiere as m left join Matiere_Niveau as mn on mn.idMatiere = m.idMatiere left join Enseignant as en on en.idEnseignant = m.idEnseignantResponsable left join Person as p on p.idPerson = en.idEnseignant "
                  + " where mn.idNiveau = 40;";
        }else if (niveau == 1) {
            query = "select m.idMatiere as id, matiere, fondamental as 'Si fondamental', coefficient, concat(p.nom,' ',p.prenom) as 'Enseignant responsable' from Matiere as m left join Matiere_Niveau as mn on mn.idMatiere = m.idMatiere  left join Enseignant as en on en.idEnseignant = m.idEnseignantResponsable left join Person as p on p.idPerson = en.idEnseignant "
                  + " where mn.idNiveau = 41;";
        }else if (niveau == 2) {
            query = "select m.idMatiere as id, matiere, fondamental as 'Si fondamental', coefficient, concat(p.nom,' ',p.prenom) as 'Enseignant responsable' from Matiere as m left join Matiere_Niveau as mn on mn.idMatiere = m.idMatiere left join Enseignant as en on en.idEnseignant = m.idEnseignantResponsable left join Person as p on p.idPerson = en.idEnseignant  "
                  + " where mn.idNiveau = 42;";
        }else if (niveau == 3) {
            query = "select m.idMatiere as id, matiere, fondamental as 'Si fondamental', coefficient, concat(p.nom,' ',p.prenom) as 'Enseignant responsable' from Matiere as m left join Matiere_Niveau as mn on mn.idMatiere = m.idMatiere left join Enseignant as en on en.idEnseignant = m.idEnseignantResponsable left join Person as p on p.idPerson = en.idEnseignant  "
                  + " where mn.idNiveau = 43;";
        }else{
            query = "select m.idMatiere as id, matiere, fondamental as 'Si fondamental', coefficient, concat(p.nom,' ',p.prenom) as 'Enseignant responsable' from Matiere as m left join Matiere_Niveau as mn on mn.idMatiere = m.idMatiere left join Enseignant as en on en.idEnseignant = m.idEnseignantResponsable left join Person as p on p.idPerson = en.idEnseignant ;";
        }
             
       try {
           dc.stmt = dc.conn.createStatement();
           dc.rs = dc.stmt.executeQuery(query);
           while(dc.rs.next()){
               listMatiere.add(new Matière(dc.rs.getInt(1), dc.rs.getString(2), dc.rs.getBoolean(3), dc.rs.getInt(4), dc.rs.getString(5)));
           }
       } catch (Exception e) {
           System.out.println("error from searchMatiere() : " +e);
       }
       
       return listMatiere;
   }
   
   public void inscritEnseignant(String nom, String prenom, String matiere, String phoneNumber, Date dateNai, String lieuNai, String adress, int indexNiveau, String email, boolean sex, String imagePath){
       MessageDialog msg = new MessageDialog();
       boolean inscrit = false;
       String pattern = "yyyy-MM-dd";
       SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
       String date = simpleDateFormat.format(dateNai);
       
       DatabaseConnection person = new DatabaseConnection();
       DatabaseConnection classe = new DatabaseConnection();
       DatabaseConnection ens = new DatabaseConnection();
       String personQuery = " insert into Person(nom ,prenom, numPhone, dateDeNaissance, lieuDeNaissance, adress, email, sex, photos)"
                                + " values(?,?,?,?,?,?,?,?,?);";
       String classeQuery = "insert into Enseignant_Classe(idEnseignant, idClasse) values(?,?)";
       String ensQuery = "insert into Enseignant(idEnseignant, nbClasse, idMatiere) values(?,?,?);";
       int idperson;
       int idMatiere;
       int nbClasses;
       int idClasse;
       
       try{
           
           InputStream img = new FileInputStream(new File(imagePath));
           System.out.println(" path: "+ imagePath);
           
       person.ps = person.conn.prepareStatement(personQuery);
       person.ps.setString(1,nom);
       person.ps.setString(2, prenom);
       person.ps.setString(3, phoneNumber);
       person.ps.setString(4, date);
       person.ps.setString(5, lieuNai);
       person.ps.setString(6, adress);
       person.ps.setString(7, email);
       person.ps.setBoolean(8, sex);
       person.ps.setBlob(9, img);
       person.ps.executeUpdate();
       
       //insert date into Enseignant table
       idperson = idPerson(nom, prenom, dateNai);
       idMatiere = selectIdMatiere(matiere);
       nbClasses = nbClasseEns(indexNiveau);
       ens.ps = ens.conn.prepareStatement(ensQuery);
       ens.ps.setInt(1, idperson);
       ens.ps.setInt(2, nbClasses);
       ens.ps.setInt(3, idMatiere);
       ens.ps.executeUpdate();
       
       //insert date into Enseignant_Classe table
       idClasse = idClasse(indexNiveau);
       classe.ps = classe.conn.prepareStatement(classeQuery);
       classe.ps.setInt(1, idperson);
       classe.ps.setInt(2, idClasse);
       classe.ps.executeUpdate();
       
       inscrit = true;
       } catch (Exception e) {
           System.out.println("error from inscritEnseignant() : " + e);
       }
       
       if (inscrit) {
           msg.messageText.setText("Enseignant bien ajouter !");
       } else {
           msg.messageText.setText("insertion failed !");
       }
  msg.setVisible(true);
   }
   
   /**
    * this method help us to get the idClasse to use in inscritEnseignant method
    * @param niveau
    * @return 
    */
   public int idClasse(int niveau){
       String year = String.valueOf(new Date().getYear()+1900);
       int idAnnee = getIdAnnee(year);
       int idClasse = 2; // inisialization aleatoire
       int idNiveau = 40;
       switch(niveau){
            case 0: idNiveau = 40; break;
            case 1: idNiveau = 41; break;
            case 2: idNiveau = 42; break;
            case 3: idNiveau = 43; break;
        }
       
       String idClasseQuery = "select idClasse from Classe where idNiveau = " + idNiveau +" and idAnnee = "+idAnnee+" limit 1 ;"; 
       DatabaseConnection dc = new DatabaseConnection();
       try {
           dc.stmt = dc.conn.createStatement();
           dc.rs = dc.stmt.executeQuery(idClasseQuery);
           while(dc.rs.next()){
               idClasse = dc.rs.getInt(1);
           }
       } catch (Exception e) {
           System.out.println("error from inscritEnseignant  : "+e);
       }
     return idClasse;
   }
   
   /**
    * this to get the id of specific person
    * @param nom
    * @param prenom
    * @param dateNai
    * @return 
    */
   public int idPerson(String nom, String prenom, Date dateNai){
       int idPerson = 1;
       String pattern = "yyyy-MM-dd";
       SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
       String date = simpleDateFormat.format(dateNai);
       String query = "select idPerson from Person where nom = '" + nom +"' and prenom = '" + prenom +"' and dateDeNaissance = '" + date + "';";
       DatabaseConnection dc = new DatabaseConnection();
       try {
           dc.stmt = dc.conn.createStatement();
           dc.rs = dc.stmt.executeQuery(query);
           while(dc.rs.next()){
               idPerson = dc.rs.getInt(1);
           }
       } catch (Exception e) {
           System.out.println("error from idPerson()" +e);
       }
       
       return idPerson;
   }
   
   /**
    * cette methode permet de sauvgarder le nbClasses;
    * @param idNiveau
    * @return 
    */
   public int nbClasseEns(int idNiveau){
       int nbClasse= 0;
       String query = "select count(*) from Classe where idNiveau = "+ idNiveau+";";
       DatabaseConnection dc = new DatabaseConnection();
       try {
           dc.stmt = dc.conn.createStatement();
           dc.rs = dc.stmt.executeQuery(query);
           while(dc.rs.next()){
               nbClasse = dc.rs.getInt(1);
           }
       } catch (Exception e) {
           System.out.println("error from nbClasseEns() :" + e);
       }
       return nbClasse;
   }
}
