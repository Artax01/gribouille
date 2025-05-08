package iut.gon.gribouille;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Controller implements Initializable {
	
    @FXML private ToggleGroup second;
    @FXML private ToggleGroup premier;
    @FXML private Pane pane;
    @FXML private Canvas canvas;
    @FXML private ColorPicker colorPicker;
    @FXML private Rectangle rect_red;
    @FXML private Rectangle rect_lime;
    @FXML private Rectangle rect_blue;
    @FXML private Rectangle rect_cyan;
    @FXML private Rectangle rect_pink;
    @FXML private Rectangle rect_yellow;
    @FXML private Rectangle rect_black;
    @FXML private Rectangle rect_white;
    @FXML private Label xCoordinate;
    @FXML private Label yCoordinate;
    @FXML private Label epaisseur;
    
    private double prevX;
    private double prevY;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		canvas.heightProperty().bind(pane.heightProperty());
		canvas.widthProperty().bind(pane.widthProperty());
	}
	
	public void onMousePressed(MouseEvent evt) {
    	prevX = evt.getX();
    	prevY = evt.getY();
    }
    
    public void onMouseDragged(MouseEvent evt) {
    	canvas.getGraphicsContext2D().strokeLine(prevX, prevY, evt.getX(), evt.getY());
    	prevX = evt.getX();
    	prevY = evt.getY();
    }
}