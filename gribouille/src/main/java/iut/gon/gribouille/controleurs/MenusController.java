package iut.gon.gribouille.controleurs;

import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import javafx.stage.Window;

public class MenusController implements Initializable {

	@FXML public ToggleGroup epaisseurGroup;
	@FXML public ToggleGroup outilGroup;
	@FXML public RadioMenuItem crayon;
    @FXML public RadioMenuItem etoile;
    
    private Controller controller;

    public void setControleur(Controller c) {
    	this.controller = c;
    }

    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	outilGroup.selectedToggleProperty().addListener((observableValue, oldValue, newValue) -> {
    		if (newValue != null) {
    			String id = ((RadioMenuItem) newValue).getId();
    			
    			if (id.equals("etoile")) {
    				controller.onEtoile();
    			}
    			else if (id.equals("crayon")) {
    				controller.onCrayon();
    			}
    		}
    	});
    	
    	epaisseurGroup.selectedToggleProperty().addListener((observableValue, oldValue, newValue) -> {
    		if (newValue != null) {
    			controller.setEpaisseur(Integer.parseInt(((RadioMenuItem) newValue).getText()));
    		}
    	});
    	
    	
	}
    
    public void onCharge() {
    	controller.onCharge();
    }
    
    public void onSauvegarde() {
    	controller.onSauvegarde();
    }
    
    public void onExporte() {
    	controller.onExporte();
    }
    
    public void onEffacerTout() {
    	controller.onEffacerTout();
    }
    
    public void onAPropos() {
    	Alert alert = new Alert(Alert.AlertType.INFORMATION, "", ButtonType.OK);
    	alert.setTitle("A Propos");
		alert.setHeaderText("Logiciel Gribouille: Réalisé par XXX");
		alert.setContentText("Le projet Gribouille est un logiciel de dessin écrit en Java/JavaFX.");
		alert.showAndWait();
    }

    @FXML
    private void onQuitte() {
    	if (controller.onQuitter()) {
    		Platform.exit();
    	}
    }

}
