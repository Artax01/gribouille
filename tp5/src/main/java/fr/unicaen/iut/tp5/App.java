package fr.unicaen.iut.tp5;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
	
	private static Scene scene;
	private static ControleurDemineur controleur;
	
	@Override
	public void start(Stage stage) throws IOException {
		controleur = new ControleurDemineur();
		scene = new Scene(loadFXML("tp5"), 800, 480);
		stage.setScene(scene);
		stage.show();
	}
	
	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		fxmlLoader.setController(controleur);
		return fxmlLoader.load();
	}
	
	public static void main (String[] args) {
		launch();
	}
}
