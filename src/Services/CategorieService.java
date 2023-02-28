/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyDB;
import entities.Categorie;
import entities.Offre;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;

/**
 *
 * @author ASUS
 */
public class CategorieService  /*implements IService<Categorie>*/  {
    Connection connexion;

    public CategorieService() {
        connexion = MyDB.getInstance().getConnection();
    }
     public void modifierCategorie(Categorie e) throws SQLException, NoSuchAlgorithmException {
       
        String req = "UPDATE  categorie_emploi SET "
             
                + " titre='"+e.getTitre()+"' where id  = "+e.getId()+"";
        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    } 
  
    
  
          public void ajouterCategorie(Categorie e) throws SQLException {
           

        String req = "INSERT INTO `categorie_emploi` (`titre`) "
                + "VALUES (?)";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setString(1, e.getTitre());
       

        ps.executeUpdate();
    }


     public List<Categorie> AfficherCategorie() throws SQLException {
        List<Categorie> Categories = new ArrayList<>();
        String req = "select * from categorie_emploi   ";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            Categorie e = new Categorie(rst.getInt("id")
                    , rst.getString("titre")
                   
                   );
            Categories.add(e);
        }
        return Categories;
    }
     
   
  
     public void SupprimerCategorie(Categorie e) throws SQLException {

        String req = "DELETE FROM categorie_emploi WHERE id =?";
        try {
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setInt(1, e.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
    }
     
 

    
    
}
