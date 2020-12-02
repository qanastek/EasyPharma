package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.models.Pays;
import application.models.PharmacieFranchisée;
import application.models.PharmacieIndépendante;
import application.models.Abstracts.Pharmacie;
import application.models.UI.PharmacieCell;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class MainController implements Initializable {
	
    @FXML
    private Text content;

    ObservableList<Pharmacie> data = FXCollections.observableArrayList(
		new PharmacieIndépendante("Pharmacie1",100,"fs45f45sd6fs", new Pays("France",60000000,595269465.0)),
		new PharmacieFranchisée("Pharmacie1",100,"fs45f45sd6fs", new Pays("France",60000000,595269465.0))
    );
    
    @FXML
    private ListView<Pharmacie> sideMenu = new ListView<Pharmacie>(data);

	public void initialize(URL location, ResourceBundle resources) {
		
		System.out.println("Salut!");
	
//		sideMenu.setCellFactory(new Callback<ListView<Pharmacie>, ListCell<Pharmacie>>() {
//	        public ListCell<Pharmacie> call(ListView<Pharmacie> listView) {
//	            return new PharmacieCell();
//	        }
//	    });
		
		sideMenu.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		sideMenu.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Pharmacie>() {
 
            public void changed(ObservableValue<? extends Pharmacie> observable, Pharmacie oldValue, Pharmacie newValue) {
            	content.setText("OLD: " + oldValue + ",  NEW: " + newValue);
            }
        });
	}

    @FXML 
    private void handleButton1() {
    	content.setText("saluce hombre!");
        System.out.println("saluce hombre!");
    }

    @FXML 
    private void ajoutePharmacie() {

        System.out.println("saluce add!");
        
		data.add(
			new PharmacieIndépendante(
				"Pharmacie1",
				100,
				"fs45f45sd6fs",
				new Pays(
					"France",
					60000000,
					595269465.0
				)
			)
		);
		
		for (Pharmacie pharmacie : data) {
			System.out.println(pharmacie.getType());
		}
    }	
}
