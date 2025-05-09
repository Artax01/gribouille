package iut.gon.gribouille;

import java.net.URL;
import java.util.ResourceBundle;

import iut.gon.gribouille.modele.*;
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
    private Dessin dessin;
    private Trace trace;
    
    public Controller(Dessin dessin) {
    	this.dessin = dessin;
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {		
		pane.layoutBoundsProperty().addListener((observable, oldValue, newValue) -> {
			pane.setPrefHeight(newValue.getHeight());
			pane.setPrefWidth(newValue.getWidth());
		});
		
		canvas.heightProperty().bind(pane.heightProperty());
		canvas.widthProperty().bind(pane.widthProperty());
		
		canvas.heightProperty().addListener((observableValue, oldValue, newValue) -> {
			canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getHeight(), canvas.getWidth());
			for (Figure f : dessin.getFigures()) {
				for (int i = 1; i < f.getPoints().size(); i++) {
					double x0 = f.getPoints().get(i-1).getX();
					double y0 = f.getPoints().get(i-1).getY();
					double x1 = f.getPoints().get(i).getX();
					double y1 = f.getPoints().get(i).getY();
					
					canvas.getGraphicsContext2D().strokeLine(x0, y0, x1, y1);
				}
			}
		});
		
		canvas.widthProperty().addListener((observableValue, oldValue, newValue) -> {
			canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getHeight(), canvas.getWidth());
			for (Figure f : dessin.getFigures()) {
				for (int i = 1; i < f.getPoints().size(); i++) {
					double x0 = f.getPoints().get(i-1).getX();
					double y0 = f.getPoints().get(i-1).getY();
					double x1 = f.getPoints().get(i).getX();
					double y1 = f.getPoints().get(i).getY();
					
					canvas.getGraphicsContext2D().strokeLine(x0, y0, x1, y1);
				}
			}
		});
	}
	
	public void onMousePressed(MouseEvent evt) {
    	prevX = evt.getX();
    	prevY = evt.getY();
    	trace = new Trace(1, "black", prevX, prevY);
    	dessin.addFigure(trace);
    }
    
    public void onMouseDragged(MouseEvent evt) {
    	canvas.getGraphicsContext2D().strokeLine(prevX, prevY, evt.getX(), evt.getY());
    	Point point = new Point(prevX, prevY);
    	trace.addPoint(point);
    	prevX = evt.getX();
    	prevY = evt.getY();
    }
}