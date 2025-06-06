package iut.gon.gribouille.controleurs;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;

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

    @FXML
    private void onQuitte() {
    	if (controller.onQuitter()) {
    		Platform.exit();
    	}
    }

}
