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
import entities.Offre;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
/**
 *
 * @author ASUS
 */
public class OffreService /*implements OService<Offre>*/ {
    Connection connexion;   
  public OffreService() {
        connexion = MyDB.getInstance().getConnection();
    }
 

   
   
   
    public void modifierOffre(Offre e) throws SQLException, NoSuchAlgorithmException {
       
        String req = "UPDATE offre_emploi SET "
                + " categorie_emploi_id ='"+e.getCategorie_emploi_id()+"'"
                + ", image='"+e.getImage()+"'"
                + ", description='"+e.getDescription()+"'"
             + ", type='"+e.getType()+"'"
                + ", profil_souhaite='"+e.getProfil_souhaite()+"'"
                + ", entreprise='"+e.getEntreprise()+"'"
                + ", position='"+e.getPosition()+"'"
                + ", latitude='"+e.getLatitude()+"'"
                + ", longitude='"+e.getLongitude()+"'"
                + ", date_exp='" + (java.sql.Date) (Date) e.getDate_exp()+"'"
                + ", updated_at='"+ (java.sql.Date) (Date) e.getUpdated_at()+"'"
                + ", fonctionnalites='"+e.getFonctionnalites()+"' where id  = "+e.getId()+"";
        Statement stm = connexion.createStatement(); 
//statment : requet statique (pas de parametres a passer) affichage + boucle 3al kol 

        stm.executeUpdate(req);
    } 
  
    
  
          public void ajouterOffre(Offre e) throws SQLException {
           

        String req = "INSERT INTO `offre_emploi` (`categorie_emploi_id`,`image`,`description`,`type`,`profil_souhaite`,`entreprise`,`position`,`date_exp`,`updated_at`,`fonctionnalites`,`latitude`,`longitude`) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = connexion.prepareStatement(req); //preparestatement :securisé , requet dynamique (passer des paremetres) insertion , supression , boucle ken instruction li5ra 
        ps.setInt(1, e.getCategorie_emploi_id());
        ps.setString(2, e.getImage());
       
        ps.setString(3, e.getDescription());
           ps.setString(4, e.getType());
          ps.setString(5, e.getProfil_souhaite());
           ps.setString(6, e.getEntreprise());
            ps.setString(7, e.getPosition());
             ps.setDate(8,(java.sql.Date) (Date) e.getDate_exp());
              ps.setDate(9, (java.sql.Date) (Date) e.getUpdated_at());
               ps.setString(10, e.getFonctionnalites());
               ps.setInt(11, e.getLatitude());
               ps.setInt(12, e.getLongitude());

        ps.executeUpdate();
    }


     public List<Offre> AfficherAllOffre() throws SQLException {
        List<Offre> Offres = new ArrayList<>();
        String req = "select * from offre_emploi order by date_exp  ";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            Offre e = new Offre(rst.getInt("id")
                    , rst.getInt("categorie_emploi_id")
                    , rst.getString("image")
                    , rst.getString("description")
                     , rst.getString("type")
                     , rst.getString("profil_souhaite")
                     , rst.getString("entreprise")
                     , rst.getString("position")
                     , rst.getDate("date_exp")
                     , rst.getDate("updated_at")
                     , rst.getString("fonctionnalites")
                    , rst.getInt("latitude")
                    , rst.getInt("longitude")
                   );
            Offres.add(e);
        }
        return Offres;
    }
     
   
  
     public void SupprimerOffre(Offre e) throws SQLException {

        String req = "DELETE FROM offre_emploi WHERE id =?";
        try {
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setInt(1, e.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
    }
  
}
