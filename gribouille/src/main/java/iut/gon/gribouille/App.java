package iut.gon.gribouille;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import iut.gon.gribouille.controleurs.Controller;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Controller controleur;

    @Override
    public void start(Stage stage) throws IOException {
    	controleur = new Controller();
    	controleur.dessin.setNomDuFichier("Gribouille");
    	
        scene = new Scene(loadFXML("CadreGribouille"), 800, 480);
        stage.setTitle(controleur.dessin.getNomDuFichier());
        stage.setScene(scene);
        stage.show();
        
        stage.setOnCloseRequest((evt) -> {
        	controleur.onCloseRequest(evt);
        });
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        fxmlLoader.setController(controleur);
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}