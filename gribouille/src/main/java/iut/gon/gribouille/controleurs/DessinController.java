package iut.gon.gribouille.controleurs;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;

public class DessinController implements Initializable {

	@FXML public Pane pane;
	@FXML public Canvas canvas;
	
	private Controller controller;

    public void setControleur(Controller c) {
    	this.controller = c;
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		pane.layoutBoundsProperty().addListener((observable, oldValue, newValue) -> {
			pane.setPrefHeight(newValue.getHeight());
			pane.setPrefWidth(newValue.getWidth());
		});

		canvas.heightProperty().bind(pane.heightProperty());
		canvas.widthProperty().bind(pane.widthProperty());
		
		canvas.heightProperty().addListener((observableValue, oldValue, newValue) -> controller.dessine());
		canvas.widthProperty().addListener((observableValue, oldValue, newValue) -> controller.dessine());
	}

	public void efface() {
		canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}

	public void trace(double x1, double y1, double x2, double y2) {
		canvas.getGraphicsContext2D().strokeLine(x1, y1, x2, y2);
	}
	
	public void setEpaisseur(int epaisseur) {
		controller.setEpaisseur(epaisseur);
		canvas.getGraphicsContext2D().setLineWidth(epaisseur);
	}
	
	public void setCouleur(Color couleur) {
		controller.setCouleur(couleur);
		canvas.getGraphicsContext2D().setStroke(couleur);
	}

	@FXML
	private void onMousePress(MouseEvent evt) {
		controller.outilCourant.onMousePress(evt.getX(), evt.getY());
	}

	@FXML
	private void onMouseMove(MouseEvent evt) {
		controller.prevX.set(evt.getX());
		controller.prevY.set(evt.getY());
	}

	@FXML
	private void onMouseDrag(MouseEvent evt) {
		controller.outilCourant.onMouseDrag(evt.getX(), evt.getY());
	}

}
