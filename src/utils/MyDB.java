/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author ASUS
 */
public class MyDB {
    private final String url ="jdbc:mysql://localhost:3306/db_name";
    private final String username ="root";
    private final String password ="";
    private Connection connection ;
    
    //Singlton : pour creer une seule instance de meme classe
    /*2 etape : declarer V.statique de meme type de classe */ private static MyDB instance;
    
    /*1 etape : Constructeur privée */ public MyDB() {
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("success");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     
     /*3 etape : implémenter getter de V.statique */
   public static MyDB getInstance(){ 
       if(instance == null)
           instance = new MyDB();
       return instance;
   }

    public Connection getConnection() {
        return connection;
    }
    
}
