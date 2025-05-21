package fr.iutgon.ctp;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
	
	private static Scene scene;
	private static Controller controller;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		controller = new Controller();
		scene = new Scene(loadFXML("potence"), 800, 480);
		
		primaryStage.setTitle("Mon Pendu !");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		primaryStage.setOnCloseRequest((evt) -> {
			controller.onQuit();
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
    launch(args);
  }
}
