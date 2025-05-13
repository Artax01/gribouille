package fr.unicaen.iut.tp5;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class ControleurDemineur implements Initializable {

	@FXML private ToggleGroup diff;
	@FXML private TextField nbInconnues;
	@FXML private TextField nbMarques;
	@FXML private GridPane grid;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ModeleDemineur modele = new ModeleDemineur(0,0,0);
		nbInconnues.textProperty().bind(modele.nbInconnuesProperty().asString());
		nbMarques.textProperty().bind(modele.nbMarquesProperty().asString());
		
		diff.selectedToggleProperty().addListener((observableValue, oldValue, newValue) -> {
			initGrille(modele);
		});
	}
	
	public void initGrille(ModeleDemineur modele) {
		grid.getRowConstraints().clear();
		grid.getColumnConstraints().clear();
		
		int[] donnees = ModeleDemineur.parseUserData(diff.getSelectedToggle().getUserData().toString());
		
		modele.setTaille(donnees[0], donnees[1], donnees[2]);
		
		for (int i = 0; i < donnees[1]; i++) {
			grid.getRowConstraints().add(new RowConstraints(32));
		}
		for (int j = 0; j < donnees[0]; j++) {
			grid.getColumnConstraints().add(new ColumnConstraints(32));
		}
	}

}
