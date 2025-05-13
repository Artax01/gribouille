package iut.gon.gribouille.controleurs;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;

public class MenusController implements Initializable {

	@FXML public ToggleGroup second;
    @FXML public ToggleGroup premier;
    @FXML public RadioMenuItem crayon;
    @FXML public RadioMenuItem etoile;
    
    private Controleur controleur;
    
    public void setControleur(Controleur controleur) {
    	this.controleur = controleur;
    }
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	premier.selectedToggleProperty().addListener((observableValue, oldValue, newValue) -> {
    		if (newValue != null) {
    			String id = ((RadioMenuItem) newValue).getId();
    			
    			if (id.equals("etoile")) {
    				controleur.onEtoile();
    			} 
    			else if (id.equals("crayon")) {
    				controleur.onCrayon();
    			}
    		}
    	});
	}
    
    @FXML
    private void onQuitte() {
    	if (controleur.onQuitter()) {
    		Platform.exit();
    	}
    }
}
