package iut.gon.gribouille;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

import iut.gon.gribouille.modele.Dessin;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Controller controller;

    @Override
    public void start(Stage stage) throws IOException {
    	Dessin dessin = new Dessin();
    	controller = new Controller(dessin);
    	
        scene = new Scene(loadFXML("CadreGribouille"), 640, 480);
        stage.setTitle("Gribouille");
        stage.setScene(scene);
        stage.show();
        
        stage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, (event) -> {
        	if (!Dialogues.confirmation()) {
        		event.consume();
        	}
        });
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