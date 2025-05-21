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
    @FXML public Label outil;
    @FXML public Label couleur;

    private Controller controller; 

    public void setControleur(Controller c) {
    	this.controller = c;
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO
	}

}
