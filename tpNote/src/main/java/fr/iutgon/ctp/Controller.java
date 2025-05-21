package fr.iutgon.ctp;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import fr.iutgon.ctp.modele.Partie;
import fr.iutgon.ctp.modele.Pendu;
import javafx.application.Platform;
import javafx.beans.binding.StringExpression;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class Controller implements Initializable {

	@FXML public Rectangle corde;
	@FXML public Rectangle soutien;
	@FXML public Rectangle mat;
	@FXML public Rectangle potence;
	@FXML public Circle tete;
	@FXML public Rectangle bras_droit;
	@FXML public Rectangle bras_gauche;
	@FXML public Rectangle jambe_gauche;
	@FXML public Rectangle jambe_droite;
	@FXML public Ellipse corps;
	
	@FXML public MenuItem newGameBtn;
	@FXML public MenuItem quitBtn;
	@FXML public MenuItem aboutBtn;
	@FXML public MenuItem scoresBtn;
	
	@FXML public FlowPane flowPane;
	@FXML public HBox hbox;
	
	private Pendu pendu;
	private Scores scores;
	private List<StringExpression> listeLettres;
	private List<Character> listeChar;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		pendu = new Pendu();
		scores = new Scores();
		
		pendu.perduProperty().addListener((obsVal, oldVal, newVal) -> {
			try {
				scores.addPartie(pendu.getPartie());
			}
			catch (Exception e) {
				return;
			}
			
			disableAllButtons();
			
			Alert alert = new Alert(Alert.AlertType.INFORMATION, "Vous êtes pendu !");
			alert.setTitle("Defaite");
			alert.showAndWait();
		});
	
		bindPendu();
	}
	
	public void onNewGame() {
		listeLettres = null;
		listeLettres = null;
		flowPane.getChildren().clear();
		hbox.getChildren().clear();
		
		pendu.tireNouveauMot();
		
		listeLettres = pendu.getLettres();
		for (int i = 0; i < listeLettres.size(); i++) {
			TextField textField = new TextField();
			textField.textProperty().bind(listeLettres.get(i));
			textField.setEditable(false);
			textField.setPrefWidth(64);
			textField.setFont(new Font(30));
			hbox.getChildren().add(textField);
		}
		
		createAvailableLetters();
	}
	
	private void createAvailableLetters() {
		listeChar = pendu.getDisponibles();
		for (int i = 0; i < listeChar.size(); i++) {
			ToggleButton toggleButton = new ToggleButton();
			toggleButton.setText(listeChar.get(i).toString());
			toggleButton.setPrefHeight(30);
			flowPane.getChildren().add(toggleButton);
			
			
			toggleButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (evt) -> {
				pendu.propose(toggleButton.getText().charAt(0));
				toggleButton.disableProperty().set(true);
				
				if (pendu.isGagne()) {
					disableAllButtons();
					
					Alert alert = new Alert(Alert.AlertType.INFORMATION, "Vous avez trouvez le mot !");
					alert.setTitle("Victoire");
					alert.showAndWait();
					
					scores.addPartie(pendu.getPartie());
				}
			});
		}
	}
	
	private void disableAllButtons() {
	    for (var node : flowPane.getChildren()) {
	        if (node instanceof ToggleButton) {
	            node.setDisable(true);
	        }
	    }
	}
	
	public void onScores() {
		scores.afficher();
	}
	
	public void onAbout() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION, "Réalisé par XXX");
		alert.setTitle("A propos");
		alert.showAndWait();
	}

	public void onQuit() {
		Platform.exit();
	}
	
	private void bindPendu() {
		corde.setVisible(false);
		soutien.setVisible(false);
		mat.setVisible(false);
		potence.setVisible(false);
		tete.setVisible(false);
		bras_droit.setVisible(false);
		bras_gauche.setVisible(false);
		jambe_gauche.setVisible(false);
		jambe_droite.setVisible(false);
		corps.setVisible(false);
		
		/** Bind */
		corde.visibleProperty().bind(pendu.cordeProperty());
		soutien.visibleProperty().bind(pendu.soutienProperty());
		mat.visibleProperty().bind(pendu.matProperty());
		potence.visibleProperty().bind(pendu.potenceProperty());
		tete.visibleProperty().bind(pendu.teteProperty());
		bras_droit.visibleProperty().bind(pendu.brasProperty());
		bras_gauche.visibleProperty().bind(pendu.brasProperty());
		jambe_gauche.visibleProperty().bind(pendu.jambesProperty());
		jambe_droite.visibleProperty().bind(pendu.jambesProperty());
		corps.visibleProperty().bind(pendu.corpsProperty());
	}
	
	
	/**
	 * 
	 * Getters et Setters
	 * 
	 */
	
	
	public Pendu getPendu() {
		return pendu;
	}

	public void setPendu(Pendu pendu) {
		this.pendu = pendu;
	}

	public Scores getScores() {
		return scores;
	}

	public void setScores(Scores scores) {
		this.scores = scores;
	}

	public List<StringExpression> getListeLettres() {
		return listeLettres;
	}

	public void setListeLettres(List<StringExpression> listeLettres) {
		this.listeLettres = listeLettres;
	}

	public List<Character> getListeChar() {
		return listeChar;
	}

	public void setListeChar(List<Character> listeChar) {
		this.listeChar = listeChar;
	}
}
