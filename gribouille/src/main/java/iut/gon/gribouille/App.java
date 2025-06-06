package iut.gon.gribouille;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
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
    private static Controller controller;

    @Override
    public void start(Stage stage) throws IOException {
    	controller = new Controller();
        scene = new Scene(loadFXML("CadreGribouille"), 800, 480);
        
        stage.titleProperty().bind(Bindings.concat(
        		"Gribouille (", controller.dessin.nomDuFichierProperty(), ")",
        		Bindings.when(controller.dessin.estModifieProperty()).then("*").otherwise("")
        ));
        stage.setScene(scene);
        
        stage.getScene().setOnKeyPressed(evt -> {
        	controller.onKeyPressed(evt.getText());
        });
        
        stage.setOnCloseRequest((evt) -> {
        	controller.onCloseRequest(evt);
        });
        
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        fxmlLoader.setController(controller);
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}