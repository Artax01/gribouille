package iut.gon.gribouille;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Dialogues {
	public static boolean confirmation() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
		alert.setTitle("Fermeture de l'application");
		alert.setHeaderText("Voulez-vous vraiment fermer l'application ?");
		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == ButtonType.NO) {
			return false;
		}
		return true;
	}
}
