/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.CategorieService;
import entities.Categorie;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Administrateur
 */
public class CategorieGestionController implements Initializable {

    @FXML
    private TextField inputtitre;
    @FXML
    private TextField inputRech;
    @FXML
    private TableView<Categorie> tableview;
    @FXML
    private TableColumn<?, ?> titre;
    @FXML
    private Button supp;
    @FXML
    private Button supp1;
    @FXML
    private Button Ajouter;
    @FXML
    private Label imgpathttt;
    @FXML
    private Button affichsalle;
    @FXML
    private Label labelid;
    @FXML
    private Button Confirmer;
    @FXML
    private Label nbvue;
    @FXML
    private Label datenow;
    @FXML
    private Label datee;
 public ObservableList<Categorie> list;
    @FXML
    private Hyperlink Retour;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
        CategorieService pss = new CategorieService();
        ArrayList<Categorie> c = new ArrayList<>();
        try {
            c = (ArrayList<Categorie>) pss.AfficherCategorie();
        } catch (SQLException ex) {
        }
        
        ObservableList<Categorie> obs2 = FXCollections.observableArrayList(c);
        tableview.setItems(obs2);
        
        
           
        
        
 titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
 
    
            try {
            list = FXCollections.observableArrayList(
                    pss.AfficherCategorie()
            );        
        
        
   FilteredList<Categorie> filteredData = new FilteredList<>(list, e -> true);
            inputRech.setOnKeyReleased(e -> {
                inputRech.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super Categorie>) Categories -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lower = newValue.toLowerCase();
                        if (Categories.getTitre().toLowerCase().contains(lower)) {
                            return true;
                        }

                        return false;
                    });
                });
                SortedList<Categorie> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(tableview.comparatorProperty());
                tableview.setItems(sortedData);
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }    
  public void resetTableData() throws SQLDataException, SQLException {
      CategorieService cs = new CategorieService();
      List<Categorie> listevents = new ArrayList<>();
        listevents = cs.AfficherCategorie();
        ObservableList<Categorie> data = FXCollections.observableArrayList(listevents);
        tableview.setItems(data);
    }    
    @FXML
    private void supp(ActionEvent event) throws SQLException, SQLException {
              if (event.getSource() == supp) {
            Categorie e = new Categorie();
            e.setId(tableview.getSelectionModel().getSelectedItem().getId());  
          CategorieService cs = new CategorieService();
            cs.SupprimerCategorie(e);
            resetTableData();  
        
        }
        
        
    }

    @FXML
    private void Modif(ActionEvent event) {
          CategorieService ps = new CategorieService();
          
   
        Categorie c = new Categorie(tableview.getSelectionModel().getSelectedItem().getId(),
                tableview.getSelectionModel().getSelectedItem().getTitre()
               
                                 
                );
          
           
            labelid.setText(Integer.toString(tableview.getSelectionModel().getSelectedItem().getId()));
         
            
            inputtitre.setText(tableview.getSelectionModel().getSelectedItem().getTitre());

           
               
         
           Confirmer.setVisible(true);  
        
        
    }

    @FXML
    private void Ajouter(ActionEvent event) {
         CategorieService productService = new CategorieService();
  
        if ( inputtitre.getText().equals("") 
              ) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Please fill all fields ");
            a.setHeaderText(null);
            a.showAndWait();
        } else if (inputtitre.getText().matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+")
               ) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Une erreur s’est produite. Veuillez réessayer. ");
            a.setHeaderText(null);
            a.showAndWait();
        }
     
            
        else{
            
      
            
        
            Categorie c = new Categorie(inputtitre.getText()  );
                  
        
                try {
            productService.ajouterCategorie(c);
             resetTableData();
        } catch (SQLException ex) {
         
        }
        
     ;
         } 
        
        
    }

    @FXML
    private void affichsalle(ActionEvent event) {
    }

    @FXML
    private void ConfirmerModif(ActionEvent event) throws NoSuchAlgorithmException {
           CategorieService productService = new CategorieService();
  
        if ( inputtitre.getText().equals("") 
              ) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Please fill all fields ");
            a.setHeaderText(null);
            a.showAndWait();
        } else if (inputtitre.getText().matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+")
               ) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Une erreur s’est produite. Veuillez réessayer. ");
            a.setHeaderText(null);
            a.showAndWait();
        }
     
            
        else{
            
      
            
        
            Categorie c = new Categorie(Integer.parseInt(labelid.getText()),inputtitre.getText()  );
                  
        
                try {
            productService.modifierCategorie(c);
             resetTableData();
        } catch (SQLException ex) {
         
        }
        
     ;
         } 
        
        
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
          Parent page1 = FXMLLoader.load(getClass().getResource("OffreGestion.fxml"));
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show(); 
        
        
        
    }
    
    
    
}
