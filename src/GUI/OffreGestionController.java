/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.OffreService;
import entities.Offre;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Administrateur
 */
public class OffreGestionController implements Initializable {
 public static Offre connectedOffre;
    @FXML
    private TableColumn<?, ?> categorie_emploi_id;
    @FXML
    private TableColumn<?, ?> description;
    @FXML
    private TableColumn<?, ?> type;
    @FXML
    private TableColumn<?, ?> profil_souhaite;
    @FXML
    private TableColumn<?, ?> position;
    @FXML
    private TableColumn<?, ?> date_exp;
    @FXML
    private TableColumn<?, ?> updated_at;
    @FXML
    private TableColumn<?, ?> fonctionnalites;
    @FXML
    private TableColumn<?, ?> entreprise;
     @FXML
    private TableColumn<?, ?> Latitude;
     @FXML
    private TableColumn<?, ?> longitude;
     
    @FXML
    private Button supp;
    @FXML
    private Button Ajouter;
    @FXML
    private Label imgpathttt;
    @FXML
    private Button affichsalle;
    @FXML
    private Label labelid;
    @FXML
    private TextField inputRech;
    @FXML
    private TableView<Offre> tableview;
 public ObservableList<Offre> list;
    @FXML
    private Button modif;
    @FXML
    private Hyperlink gcategorie;
    @FXML
    private Hyperlink Retour;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        OffreService pss = new OffreService();
        ArrayList<Offre> c = new ArrayList<>();
        try {
            c = (ArrayList<Offre>) pss.AfficherAllOffre();
        } catch (SQLException ex) {
        }
        
        ObservableList<Offre> obs2 = FXCollections.observableArrayList(c);
        tableview.setItems(obs2);
        
        
 categorie_emploi_id.setCellValueFactory(new PropertyValueFactory<>("categorie_emploi_id"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        profil_souhaite.setCellValueFactory(new PropertyValueFactory<>("profil_souhaite"));
         position.setCellValueFactory(new PropertyValueFactory<>("position"));
        date_exp.setCellValueFactory(new PropertyValueFactory<>("date_exp"));
    updated_at.setCellValueFactory(new PropertyValueFactory<>("updated_at"));
    fonctionnalites.setCellValueFactory(new PropertyValueFactory<>("fonctionnalites"));
    entreprise.setCellValueFactory(new PropertyValueFactory<>("entreprise"));
    Latitude.setCellValueFactory(new PropertyValueFactory<>("Latitude"));
    longitude.setCellValueFactory(new PropertyValueFactory<>("longitude"));
            try {
            list = FXCollections.observableArrayList(
                    pss.AfficherAllOffre()
            );       
        
   // recherche      
  FilteredList<Offre> filteredData = new FilteredList<>(list, e -> true);
            inputRech.setOnKeyReleased(e -> {
                inputRech.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super Offre>) Offres -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lower = newValue.toLowerCase();
                        if (Offres.getType().toLowerCase().contains(lower)) {
                            return true;
                            } else if (String.valueOf(Offres.getProfil_souhaite()).contains(lower)) {
		    return true;
		} else {
                       return false;     
                        }
                    });
                });
                SortedList<Offre> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(tableview.comparatorProperty());
                tableview.setItems(sortedData);
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }   
    
    /*
      public void RechercheAV() {
	// Wrap the ObservableList in a FilteredList (initially display all data).
	FilteredList<Offre> filteredData = new FilteredList<>(list, b -> true);

	// 2. Set the filter Predicate whenever the filter changes.
	recherche.textProperty().addListener((observable, oldValue, newValue) -> {
	    filteredData.setPredicate(Offres -> {
		// If filter text is empty, display all persons.

		if (newValue == null || newValue.isEmpty()) {
		    return true;
		}

		// Compare first name and last name of every person with filter text.
		String lowerCaseFilter = newValue.toLowerCase();

		if (Offres.getProfil_souhaite().toLowerCase().contains(lowerCaseFilter)) {
		    return true; // Filter matches first name.
		} else if (String.valueOf(Offres.getDescription()).contains(lowerCaseFilter)) {
		    return true;
		} else {
		    return false; // Does not match.
		}
	    });
	});

	// 3. Wrap the FilteredList in a SortedList. 
	SortedList<Offre> sortedData = new SortedList<>(filteredData);

	// 4. Bind the SortedList comparator to the TableView comparator.
	// 	  Otherwise, sorting the TableView would have no effect.
	sortedData.comparatorProperty().bind(tableview.comparatorProperty());

	// 5. Add sorted (and filtered) data to the table.
	tableview.setItems(sortedData);
    }
    */
    
  public void resetTableData() throws SQLDataException, SQLException {
      OffreService cs = new OffreService();
      List<Offre> listevents = new ArrayList<>();
        listevents = cs.AfficherAllOffre();
        ObservableList<Offre> data = FXCollections.observableArrayList(listevents);
        tableview.setItems(data);
    }   
    @FXML
    private void supp(ActionEvent event) throws SQLException {
       if (event.getSource() == supp) { 
        Alert alert = new Alert(AlertType.CONFIRMATION, "vous etes sur de supprimer l'offre "  + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
alert.showAndWait();

if (alert.getResult() == ButtonType.YES) {
    //do stuff$
      Offre e = new Offre();
            e.setId(tableview.getSelectionModel().getSelectedItem().getId());  
          OffreService cs = new OffreService();
            cs.SupprimerOffre(e);
            resetTableData();
             
}
        
             
            
        
        }
       JOptionPane.showMessageDialog(null, "offre suprimée !"); 
        
    }

    @FXML
    private void Modif(ActionEvent event) throws IOException {
        
     
        
        OffreService ps = new OffreService();
        Offre c = new Offre(tableview.getSelectionModel().getSelectedItem().getId(),
                
                tableview.getSelectionModel().getSelectedItem().getCategorie_emploi_id(),
                
                tableview.getSelectionModel().getSelectedItem().getImage(),
                 tableview.getSelectionModel().getSelectedItem().getDescription(),
                tableview.getSelectionModel().getSelectedItem().getType(),
                tableview.getSelectionModel().getSelectedItem().getProfil_souhaite(),
                        tableview.getSelectionModel().getSelectedItem().getEntreprise(),
                                tableview.getSelectionModel().getSelectedItem().getPosition(),
                                            tableview.getSelectionModel().getSelectedItem().getDate_exp(),
                
                     tableview.getSelectionModel().getSelectedItem().getUpdated_at(),
                      tableview.getSelectionModel().getSelectedItem().getFonctionnalites(),
                tableview.getSelectionModel().getSelectedItem().getLatitude(),
                tableview.getSelectionModel().getSelectedItem().getLongitude()
                );
        OffreGestionController.connectedOffre = c;
        
             Parent page1 = FXMLLoader.load(getClass().getResource("ModifierOffre.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();  
        
        
    }

    @FXML
    private void Ajouter(ActionEvent event) throws IOException {
                Parent page1 = FXMLLoader.load(getClass().getResource("OffreAjout.fxml"));
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
        
    }



    @FXML
    private void gcategorie(ActionEvent event) throws IOException {
              Parent page1 = FXMLLoader.load(getClass().getResource("CategorieGestion.fxml"));
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
        
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
         Parent page1 = FXMLLoader.load(getClass().getResource("Gestion.fxml"));
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
}
