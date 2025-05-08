package iut.gon.gribouille;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private double prevX;
    private double prevY;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("CadreGribouille"), 640, 480);
        stage.setTitle("Gribouille");
        stage.setScene(scene);
        stage.show();
        
        stage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, (event) -> {
        	if (!Dialogues.confirmation()) {
        		event.consume();
        	}
        });
        
        Canvas dessin = (Canvas) scene.lookup("Canvas");
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}