package iut.gon.gribouille.controleurs;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CouleursController implements Initializable {

	@FXML public VBox vbox;
	@FXML public ColorPicker colorPicker;
	@FXML public Rectangle rect_red;
    @FXML public Rectangle rect_lime;
    @FXML public Rectangle rect_blue;
    @FXML public Rectangle rect_cyan;
    @FXML public Rectangle rect_pink;
    @FXML public Rectangle rect_yellow;
    @FXML public Rectangle rect_black;
    @FXML public Rectangle rect_white;
    
    private Controller controller;
    private Rectangle dernierRect;
 
    public void setControleur(Controller c) {
    	this.controller = c;
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		vbox.setOnMouseClicked(event -> {
			if (event.getTarget() instanceof Rectangle) {
				Rectangle selectRect = (Rectangle) event.getTarget();
				
				if (dernierRect != null && dernierRect != selectRect) {
					dernierRect.setArcWidth(5);
					dernierRect.setArcHeight(5);
					dernierRect.setStrokeWidth(1);
				}
				
				selectRect.setArcWidth(10);
				selectRect.setArcHeight(10);
				selectRect.setStrokeWidth(5);
				dernierRect = selectRect;
				controller.setCouleur((Color) selectRect.getFill());
			}
		});
			
	}

}






