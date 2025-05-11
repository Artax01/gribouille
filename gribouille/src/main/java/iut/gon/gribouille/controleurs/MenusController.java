package iut.gon.gribouille.controleurs;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;

public class MenusController implements Initializable {

	@FXML public ToggleGroup second;
    @FXML public ToggleGroup premier;
    
    private Controleur controleur;
    
    public void setControleur(Controleur controleur) {
    	this.controleur = controleur;
    }
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
    
    @FXML
    private void onQuitte() {
    	if (controleur.onQuitter()) {
    		Platform.exit();
    	}
    }
}
