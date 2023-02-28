/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.OffreService;
import entities.Offre;
import java.awt.Desktop;
import java.time.LocalDate;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Administrateur
 */
public class OffreAjoutController implements Initializable {
   Connection connexion;   
    @FXML
    private Button Timage;
    @FXML
    private ImageView imgajoutincours;
    @FXML
    private Label imgpathttt;
    @FXML
    private TextField Profil;
    private ToggleGroup zd_genre;
    @FXML
    private DatePicker date_exp;
    @FXML
    private TextField entreprise;
    @FXML
    private TextField position;
    @FXML
    private DatePicker updated_at;
    @FXML
    private TextField fonctionnalites;
    @FXML
    private TextField tfLat;
    @FXML
    private TextField tfLong;
    @FXML
    private ComboBox<String> Type;
  public OffreAjoutController() {
        connexion = MyDB.getInstance().getConnection();
    } 
    //@FXML
    //private Label welcome;
   /* @FXML
    private Button A;*/
   @FXML
    private TextField description;
   // private TextField Prix;
   @FXML
    private Hyperlink prec;
  //  private DatePicker DateEvent;*/
    @FXML
    private ComboBox<Integer> Categorie;
    
      

    /**
     * Initializes the controller class.
     */
     @FXML
    private WebView webv;
    
        String lat="0";
       String lon="0";

     
    @FXML
    void klat(KeyEvent event) {
       
       lat= tfLat.getText().toString();
        map();

    }

    @FXML
    void klong(KeyEvent event) {
        System.out.println("ok2");
       lon= tfLong.getText();
            map();
    }
    
    
       @FXML
    void klat2(KeyEvent event) {
            System.out.println("heyl2");
    }

  

    @FXML
    void klong2(KeyEvent event) {
  System.out.println("heyl1");
    }
     
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           try {
            String req = "select * from categorie_emploi";
            Statement stm = connexion.createStatement();
            ResultSet rst = stm.executeQuery(req);
            
            while (rst.next()) {
             //   Users a = new Users(rst.getInt("id"));
                
                Integer xx = rst.getInt("id");
                Categorie.getItems().add(xx);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    Type.getItems().add("CDD");
                Type.getItems().add("CDI");
                 Type.getItems().add("Temps-partiel");
                 Type.getItems().add("Temps-plein");
                 Type.getItems().add("Alternance"); 
                 Type.getItems().add("Freelance"); 
                 
                 
         map();
    }    
    
    
      public boolean validateInputs() {
        if (entreprise.getText().isEmpty()) {
                Alert alert1 = new Alert(Alert.AlertType.WARNING);
                alert1.setTitle("Failed !");
                alert1.setContentText("Entreprise cannot be empty , try entering one !");
                alert1.setHeaderText(null);
                alert1.show();
                return false;
        } else if (description.getText().chars().allMatch( Character::isDigit)) {
                Alert alert2 = new Alert(Alert.AlertType.WARNING);
                alert2.setTitle("Failed !");
                alert2.setContentText("description cannot be a number , try entering a string ");
                alert2.setHeaderText(null);
                alert2.show();
                return false;
        } else if (Profil.getText().chars().allMatch( Character::isDigit)) {
                Alert alert2 = new Alert(Alert.AlertType.WARNING);
                alert2.setTitle("Failed !");
                alert2.setContentText("Profil cannot be a number , try entering a string ");
                alert2.setHeaderText(null);
                alert2.show();
                return false;
        } else if (description.getText().isEmpty()) {
                Alert alert2 = new Alert(Alert.AlertType.WARNING);
                alert2.setTitle("Failed !");
                alert2.setContentText("Description cannot be empty , try entering one !");
                alert2.setHeaderText(null);
                alert2.show();
                return false;
        }else if (position.getText().isEmpty()) {
                Alert alert2 = new Alert(Alert.AlertType.WARNING);
                alert2.setTitle("Failed !");
                alert2.setContentText("Position cannot be empty , try entering one !");
                alert2.setHeaderText(null);
                alert2.show();
                return false;
        }else if (position.getText().chars().allMatch( Character::isDigit)) {
                Alert alert2 = new Alert(Alert.AlertType.WARNING);
                alert2.setTitle("Failed !");
                alert2.setContentText("Position cannot be a number , try entering a string !");
                alert2.setHeaderText(null);
                alert2.show();
                return false;
        
        
        }else if (updated_at.getValue().isAfter(date_exp.getValue())) {
                Alert alert2 = new Alert(Alert.AlertType.WARNING);
                alert2.setTitle("Failed !");
                alert2.setContentText("Start Date must be inferior to End Date !");
                alert2.setHeaderText(null);
                alert2.show();
                return false;
        }else if (updated_at.getValue().isBefore(LocalDate.now())) {
                Alert alert2 = new Alert(Alert.AlertType.WARNING);
                alert2.setTitle("Failed !");
                alert2.setContentText("Start Date must be equal or superior to current date !");
                alert2.setHeaderText(null);
                alert2.show();
                return false;
        }
        return true;
    }
    
    
    /* public boolean validateInputs() {
     if (updated_at.getValue().isBefore(date_exp.getValue())){
           Alert alert2 = new Alert(Alert.AlertType.WARNING);
                alert2.setTitle("Erreur !");
                alert2.setContentText("Date Fin doit etre superieur au date debut !");
                alert2.setHeaderText(null);
                alert2.show();
                return false;
     }
     else if (updated_at.getValue().isAfter(LocalDate.now())) {
          Alert alert2 = new Alert(Alert.AlertType.WARNING);
                alert2.setTitle("Erreur !");
                alert2.setContentText("Date debut doit etre egale ou superieur au date actuel!");
                alert2.setHeaderText(null);
                alert2.show();
                return false;
     }
     return false;
     }
       */
 
    @FXML
    private void insert(ActionEvent event) throws SQLException, IOException {
        	      if (validateInputs()) {
          OffreService productService = new OffreService();
        
       /* if (Profil.getText().equals("")
                || description.getText().equals("")
                || fonctionnalites.getText().equals("")) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Remplir les champs ");
            a.setHeaderText(null);
            a.showAndWait();
        
        }  */
               
               
             /*  if (entreprise.getText().matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+")
                || description.getText().equals("")
                || position.getText().equals("")) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText(" Le champs entreprise ne doit pas contenir le symbole : "+ entreprise.getText() );
            a.setHeaderText(null);
            a.showAndWait();
        }       */
               
               
         
           java.util.Date date2
                = java.util.Date.from(this.date_exp.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                 java.sql.Date sqlDate2 = new java.sql.Date(date2.getTime());
                 
                   java.util.Date date3
                = java.util.Date.from(this.updated_at.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                 java.sql.Date sqlDate3 = new java.sql.Date(date2.getTime());

              
        Offre c = new Offre(Categorie.getValue(),Timage.getText(),description.getText(),
                
               Type.getValue(),Profil.getText(),entreprise.getText(),   position.getText(),
                
                
                sqlDate2,  sqlDate3,fonctionnalites.getText(),Integer.parseInt(tfLat.getText() ),Integer.parseInt(tfLong.getText()));
              
           
        
        productService.ajouterOffre(c);
        JOptionPane.showMessageDialog(null, "offer Added !");     
        
       
            
       
      Parent page1 = FXMLLoader.load(getClass().getResource("OffreGestion.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Liste des Offres");
        stage.setScene(scene);
        stage.show();
        
         
       
    }}
        
         /* private boolean Validchamp(TextField T) {
	return !T.getText().isEmpty() && T.getLength() > 2;
    }
       */ 
     
   
@FXML
    private void addimgcours(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
        File file = fileChooser.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            imgajoutincours.setImage(image);
            imgajoutincours.setFitWidth(200);
            imgajoutincours.setFitHeight(200);
            imgajoutincours.scaleXProperty();
            imgajoutincours.scaleYProperty();
            imgajoutincours.setSmooth(true);
            imgajoutincours.setCache(true);
            FileInputStream fin = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            for (int readNum; (readNum = fin.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
            }
            byte[] person_image = bos.toByteArray();
        } catch (IOException ex) {
            Logger.getLogger("ss");
        }
        imgpathttt.setText(file.getAbsolutePath());
    }
    
   /*  @FXML
    private void openGetLatLong(ActionEvent event) throws URISyntaxException, IOException { 
        Desktop.getDesktop().browse(new URI("https://www.latlong.net"));
    }

    @FXML
    private void openLinkGoogleMaps(ActionEvent event) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://www.google.com/maps/@36.4455434,10.9813806,10z")); 
    }*/
    
    @FXML
    private void prec(ActionEvent event) throws IOException {
         Parent page1 = FXMLLoader.load(getClass().getResource("OffreGestion.fxml"));
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    
    
    
    
    public void map()
    {
                WebEngine webEngine = webv.getEngine();
                 
                   webEngine.loadContent("<!DOCTYPE html>\n" +
"<html>\n" +
"<head>\n" +
"    <title>Google Maps Drag Marker Get Coordinates</title>\n" +
"    <script src=\"https://code.jquery.com/jquery-3.5.1.min.js\" type=\"text/javascript\"></script>\n" +
"    <script type=\"text/javascript\" src=\"https://maps.googleapis.com/maps/api/js?key=AIzaSyBvHg2E3bOHns4yCQJ4ogiFR9wllEg4Z0E\"></script>\n" +
"    <script type=\"text/javascript\">\n" +
"        function initialize() {\n" +
"            // Creating map object\n" +
"            var map = new google.maps.Map(document.getElementById('map_canvas'), {\n" +
"                zoom: 12,\n" +
"                center: new google.maps.LatLng("+lat+","+lon+"),\n" +
"                mapTypeId: google.maps.MapTypeId.ROADMAP\n" +
"            });\n" +
"            // creates a draggable marker to the given coords\n" +
"            var vMarker = new google.maps.Marker({\n" +
"                position: new google.maps.LatLng("+lat+","+ lon+"),\n" +
"                draggable: true\n" +
"            });\n" +
"            // adds a listener to the marker\n" +
"            // gets the coords when drag event ends\n" +
"            // then updates the input with the new coords\n" +
"           \n" +
"            // centers the map on markers coords\n" +
"            map.setCenter(vMarker.position);\n" +
"            // adds the marker on the map\n" +
"            vMarker.setMap(map);\n" +
"        }\n" +
"        \n" +
"    </script>\n" +
"</head>\n" +
"<body onload=\"initialize();\">\n" +
"   \n" +
"    <div id=\"map_canvas\" style=\"width: auto; height: 240px;\">\n" +
"    </div>\n" +
"</body>\n" +
"</html>");
                

    }


}
