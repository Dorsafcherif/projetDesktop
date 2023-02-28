/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Administrateur
 */
public class AfficherOffreDetailController implements Initializable {

    @FXML
    private Label type;
    @FXML
    private Label entreprise;
    @FXML
    private Label position;
    @FXML
    private Label fonctionnalites;
    @FXML
    private Label description1;
    @FXML
    private Label date_exp;
    @FXML
    private Label updated_at;
    @FXML
    private ImageView imgoffre;
      @FXML
    private ImageView imgqrcode;
    
     String lat;
     String lon;
     String idu="8";
     int nidu=-1;
     
     int countreating=0;
            
        @FXML
    private Label nbreating;
    
     int aff=0;

      @FXML
    private WebView infopage;
     
          @FXML
    void infobtn(ActionEvent event) {
        
        
         map();
        if (aff==0){
        infopage.setVisible(true);
        aff=1;
        }
        else{
              aff=0;
             infopage.setVisible(false);
        }
         System.out.println("hh)");
    }
  
    @FXML
    private Label notereating;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          infopage.setVisible(false);
        
        
         description1.setText(AfficherOffreFrontController.connectedOffre2.getDescription());
         
          entreprise.setText( AfficherOffreFrontController.connectedOffre2.getEntreprise());
                      
            position.setText( AfficherOffreFrontController.connectedOffre2.getPosition());
             fonctionnalites.setText( AfficherOffreFrontController.connectedOffre2.getFonctionnalites());
              
                            type.setText( AfficherOffreFrontController.connectedOffre2.getType());

        
         
 DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
           String strDate = dateFormat.format(AfficherOffreFrontController.connectedOffre2.getDate_exp());  
           String strDate2 = dateFormat.format(AfficherOffreFrontController.connectedOffre2.getUpdated_at());    
            date_exp.setText(strDate);
            
            updated_at.setText(strDate);
            
            lat=String.valueOf( AfficherOffreFrontController.connectedOffre2.getLatitude());
             lon=String.valueOf( AfficherOffreFrontController.connectedOffre2.getLongitude());
        // imgoffre.set
        
        
        //qr
        
        
        
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String myWeb = "http://127.0.0.1/offre/"+AfficherOffreFrontController.connectedOffre2.getId();
        int width = 300;
        int height = 300;
        String fileType = "png";
        
        BufferedImage bufferedImage = null;
        try {
            BitMatrix byteMatrix = qrCodeWriter.encode(myWeb, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();
            
            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);
            
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            
            System.out.println("Success...");
            
        } catch (WriterException ex) {
            Logger.getLogger(AfficherOffreDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ImageView qrView = new ImageView();
        imgqrcode.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
        
        ///endqr
        
        
        

        calcul();
        nbreating.setText(String.valueOf(countreating));
        
        
    }    
    
        public void calcul(){
            double note=0;
        	Connection connection = MyDB.getInstance().getConnection();
    	String query = "SELECT * FROM rating where idoffre="+AfficherOffreFrontController.connectedOffre2.getId();
    	Statement st;
    	   ResultSet rs;
    	
    	try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			
			while(rs.next()) {
                            countreating++;
                            System.out.println(rs.getString("id"));
                            System.out.println(rs.getString("idoffre"));
                            System.out.println(rs.getString("idu"));
                            System.out.println(rs.getString("note"));
                             if (rs.getString("note").equals("1"))
                                 note++;
                             if (rs.getString("idu").equals(idu)){
                                     nidu=1;
                                    
                             }
                            
                        }
                        if (countreating>0)
                            notereating.setText( String.format("%.2f", ( (note/countreating ) * 100 ) )+" %");
                        
		} catch (Exception e) {
			e.printStackTrace();
		}
                   
        
    }
    
        
    
    
     public void map()
    {
                WebEngine webEngine = infopage.getEngine();
                 
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
    
    
        @FXML
    void btdislike(ActionEvent event) {
        if (nidu==-1){
            System.out.println("dislike");
            Connection connection =  MyDB.getInstance().getConnection();
            String query ="INSERT INTO `rating` (`id`, `idoffre`, `idu`, `note`) VALUES (NULL, '"+AfficherOffreFrontController.connectedOffre2.getId()+"', '"+idu+"', '0')";
    	//String query = "INSERT INTO `commentaire` (`id`, `utilisateur_id`, `restaurant_id`, `contenu`, `date`, `note`) VALUES (NULL, 1, 2, '"+idc.getText().toString()+"', NULL, '"+notte.toString()+"')";
    	executeQuery(query);
        nidu=0;
        }else
            System.out.println("deja");

    }

    @FXML
    void btlike(ActionEvent event) {
         if (nidu==-1){
       System.out.println("like");
            Connection connection =  MyDB.getInstance().getConnection();
                        String query ="INSERT INTO `rating` (`id`, `idoffre`, `idu`, `note`) VALUES (NULL, '"+AfficherOffreFrontController.connectedOffre2.getId()+"', '"+idu+"', '0')";
    	//String query = "INSERT INTO `commentaire` (`id`, `utilisateur_id`, `restaurant_id`, `contenu`, `date`, `note`) VALUES (NULL, 1, 2, '"+idc.getText().toString()+"', NULL, '"+notte.toString()+"')";
    	executeQuery(query);
         nidu=0;
         }else System.out.println("deja");
    }
     
       public void executeQuery(String query) {
    	Connection conn =  MyDB.getInstance().getConnection();
    	    Statement st;
    	try {
			st = conn.createStatement();
			st.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
