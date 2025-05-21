package fr.iutgon.ctp;

import fr.iutgon.ctp.modele.Partie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Scores {

	private ObservableList<Partie> obsList = FXCollections.observableArrayList();
	
	public void addPartie(Partie p) {
		obsList.add(p);
	}
	
	public void afficher() {
		TableView<Partie> tableView = new TableView<Partie>();
		TableColumn<Partie, String> motATrouver = new TableColumn<Partie, String>("mot a trouver");
		TableColumn<Partie, Number> nombreErreurs = new TableColumn<Partie, Number>("nombre erreurs");
		
		motATrouver.setCellValueFactory(param -> {
			return param.getValue().motProperty();
		});
		nombreErreurs.setCellValueFactory(param -> {
			return param.getValue().erreursProperty();
		});
		
		tableView.getColumns().add(motATrouver);
		tableView.getColumns().add(nombreErreurs);
		tableView.setItems(obsList);
		
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Scores");
		alert.getDialogPane().setContent(tableView);
		alert.showAndWait();
	}
	
}
