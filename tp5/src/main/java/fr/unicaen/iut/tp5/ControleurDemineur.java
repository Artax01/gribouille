package fr.unicaen.iut.tp5;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.TextAlignment;

public class ControleurDemineur implements Initializable {

	@FXML private ToggleGroup diff;
	@FXML private TextField nbInconnues;
	@FXML private TextField nbMarques;
	@FXML private GridPane grid;
	
	private ModeleDemineur modele;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		diff.selectedToggleProperty().addListener((observableValue, oldValue, newValue) -> {
			if (newValue != null) {
				initGrille(newValue.getUserData().toString());
			}
		});
	}
	
	public void initGrille(String difficulty) {
		grid.getChildren().removeIf((node) -> node instanceof Label);
		grid.getRowConstraints().clear();
		grid.getColumnConstraints().clear();
		
		int[] donnees = ModeleDemineur.parseUserData(difficulty);
		modele = new ModeleDemineur(donnees[0], donnees[1], donnees[2]);
		nbInconnues.textProperty().bind(modele.nbInconnuesProperty().asString());
		nbMarques.textProperty().bind(modele.nbMarquesProperty().asString());
		
		for (int i = 0; i < donnees[0]; i++) {
			grid.getRowConstraints().add(new RowConstraints(32));
		}
		
		for (int j = 0; j < donnees[1]; j++) {
			grid.getColumnConstraints().add(new ColumnConstraints(32));
		}
		
		for (int i = 0; i < donnees[0]; i++) {
			for (int j = 0; j < donnees[1]; j++) {
				Label label = new Label();
				label.setPrefSize(31, 31);
				label.setBackground(BackgroundType.INCONNUE.getBackground());
				label.setTextAlignment(TextAlignment.CENTER);
				label.textProperty().bind(modele.texteProperty(i, j));
				
				int x = i;
				int y = j;
				
				label.addEventHandler(MouseEvent.MOUSE_CLICKED, (evt) -> {
					if (evt.getButton() == MouseButton.PRIMARY) {
						modele.revele(x, y);
					}
					else if (evt.getButton() == MouseButton.SECONDARY) {
						modele.marque(x, y);
					}
					label.setBackground(BackgroundType.getBackgroundOnText(modele.getText(x, y)));
					
					if (modele.getText(x, y).equals("X")) {
						Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez-vous relancer une partie avec la même difficuté ?", ButtonType.YES, ButtonType.NO);
						alert.setTitle("Rejouer ?");
						alert.setHeaderText("Vous avez perdu !");
						Optional<ButtonType> result = alert.showAndWait();
						
						if (result.get() == ButtonType.NO) {
							alert.close();
						} else {
							initGrille(difficulty);
						}
					}

				});
				
				grid.add(label, j, i);
			}
		}
	}
	
	public void onQuitter() {
		Platform.exit();
	}

}
