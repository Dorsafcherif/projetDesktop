/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;


/**
 *
 * @author ASUS
 */
public class Offre {
   private int id ;
   private int categorie_emploi_id ;
   private String image;
   private String description;
   private String type;
   private String profil_souhaite;
   private String entreprise;
   private String position;
   private Date date_exp;
   private Date updated_at;
   private String fonctionnalites;
   private int latitude;

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }
   private int longitude;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategorie_emploi_id() {
        return categorie_emploi_id;
    }

    public void setCategorie_emploi_id(int categorie_emploi_id) {
        this.categorie_emploi_id = categorie_emploi_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProfil_souhaite() {
        return profil_souhaite;
    }

    public void setProfil_souhaite(String profil_souhaite) {
        this.profil_souhaite = profil_souhaite;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getDate_exp() {
        return date_exp;
    }

    public void setDate_exp(Date date_exp) {
        this.date_exp = date_exp;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public String getFonctionnalites() {
        return fonctionnalites;
    }

    public void setFonctionnalites(String fonctionnalites) {
        this.fonctionnalites = fonctionnalites;
    }

    public Offre() {
    }

    public Offre(int categorie_emploi_id, String image, String description, String type, String profil_souhaite, String entreprise, String position, Date date_exp, Date updated_at, String fonctionnalites, int latitude, int longitude) {
        this.categorie_emploi_id = categorie_emploi_id;
        this.image = image;
        this.description = description;
        this.type = type;
        this.profil_souhaite = profil_souhaite;
        this.entreprise = entreprise;
        this.position = position;
        this.date_exp = date_exp;
        this.updated_at = updated_at;
        this.fonctionnalites = fonctionnalites;
        this.latitude = latitude ;
        this.longitude = longitude ;
    }

    public Offre(int id, int categorie_emploi_id, String image, String description, String type, String profil_souhaite, String entreprise, String position, Date date_exp, Date updated_at, String fonctionnalites,int latitude, int longitude) {
        this.id = id;
        this.categorie_emploi_id = categorie_emploi_id;
        this.image = image;
        this.description = description;
        this.type = type;
        this.profil_souhaite = profil_souhaite;
        this.entreprise = entreprise;
        this.position = position;
        this.date_exp = date_exp;
        this.updated_at = updated_at;
        this.fonctionnalites = fonctionnalites;
        this.latitude = latitude ;
        this.longitude = longitude ;
    }

    @Override
    public String toString() {
        return "Offre{" + "id=" + id + ", categorie_emploi_id=" + categorie_emploi_id + ", image=" + image + ", description=" + description + ", type=" + type + ", profil_souhaite=" + profil_souhaite + ", entreprise=" + entreprise + ", position=" + position + ", date_exp=" + date_exp + ", updated_at=" + updated_at + ", fonctionnalites=" + fonctionnalites + ", latitude=" + latitude + ", longitude=" + longitude + '}';
    }

   
    
    
    
  
}
