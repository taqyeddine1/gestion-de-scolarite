/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_de_scolarité.DAL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author slimane
 */
public class DatabaseConnection {
    
   public Connection conn=null;
   public Statement stmt=null;
   public ResultSet rs=null;
   public PreparedStatement ps= null;
   public CallableStatement callable= null;

    public DatabaseConnection() {
        getConnection();
    }
   
    public void getConnection(){
      try{
        Class.forName("com.mysql.jdbc.Driver");
        String path = "jdbc:mysql://localhost/GestionDeScolarite";
        String path2 = "jdbc:mysql://localhost:3306/GestionDeScolarite?autoReconnect=true&useSSL=false";
        conn= DriverManager.getConnection(path2,"root","bismi llah&");        
      }catch(Exception e){
       System.out.println("DAL getConnection failed !!!");
      }
      
    }
    
    public void closeConnection(){
        try{
          rs.close();
          conn.close();
          stmt.close();
          ps.close();
          callable.close();
        }catch(SQLException e){
        
        }
      }
}
