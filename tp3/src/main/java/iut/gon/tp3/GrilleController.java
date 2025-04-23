package iut.gon.tp3;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class GrilleController implements Initializable {
	@FXML
	private GridPane grille;
	private GrilleModel	modele;
	
	public GrilleController(GrilleModel modele) {
		this.modele = modele;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		grille.setStyle("-fx-background-color: seashell");
		for (int lg = 0; lg < 3; lg++) {
			for (int col = 0; col < 3; col++) {
				int ligne = lg;
				int colonne = col; 
				
				modele.setCase(lg, col, String.format("L%dC%d", lg, col));
				Label label = new Label(modele.getCase(lg, col));
				
				label.setMaxWidth(1000);
				label.setMaxHeight(1000);
				label.setAlignment(Pos.CENTER);
				
				label.setOnMouseClicked(event -> {
					label.setText("bonjour");
					modele.setCase(ligne, colonne, "bonjour");
				});
				
				grille.add(label, col, lg);
			}
		}

	}
	
}
