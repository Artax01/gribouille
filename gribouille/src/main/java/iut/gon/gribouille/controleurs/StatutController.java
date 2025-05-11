package iut.gon.gribouille.controleurs;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class StatutController implements Initializable {

	@FXML public Label xCoordinate;
    @FXML public Label yCoordinate;
    @FXML public Label epaisseur;
    @FXML public Label couleur;
    
    private Controleur controleur;
    
    public void setControleur(Controleur controleur) {
    	this.controleur = controleur;
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
}
