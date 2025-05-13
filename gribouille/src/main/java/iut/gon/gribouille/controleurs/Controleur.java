package iut.gon.gribouille.controleurs;

import java.net.URL;
import java.util.ResourceBundle;

import iut.gon.gribouille.Dialogues;
import iut.gon.gribouille.modele.*;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.WindowEvent;

public class Controleur implements Initializable {
	public final Dessin dessin = new Dessin();
	public Figure figureCourante;
	public Outil outilCourant = new OutilCrayon(this);
	public final SimpleDoubleProperty precX = new SimpleDoubleProperty();
	public final SimpleDoubleProperty precY = new SimpleDoubleProperty();
	public final SimpleIntegerProperty epaisseur = new SimpleIntegerProperty(1);
	public final SimpleObjectProperty<Color> couleur = new SimpleObjectProperty<Color>(Color.BLACK);
	
	@FXML private MenusController menusController;
	@FXML private DessinController dessinController;
	@FXML private StatutController statutController;
	@FXML private CouleursController couleursController;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		menusController.setControleur(this);
		dessinController.setControleur(this);
		statutController.setControleur(this);
		couleursController.setControleur(this);
		
		statutController.xCoordinate.textProperty().bind(precX.asString("%.0f"));
		statutController.yCoordinate.textProperty().bind(precY.asString("%.0f"));
		statutController.epaisseur.textProperty().bind(epaisseur.asString());
		statutController.couleur.textProperty().bind(couleur.asString());
		
		dessinController.canvas.heightProperty().addListener((observableValue, oldValue, newValue) -> dessine());
		dessinController.canvas.widthProperty().addListener((observableValue, oldValue, newValue) -> dessine());
	}
    
    public void onMousePress(double x, double y) {
    	precX.set(x);
    	precY.set(y);
    	figureCourante = new Trace(epaisseur.get(), couleur.get().toString(), x, y);
    	dessin.addFigure(figureCourante);
	}
	
	public void onMouseMove(double x, double y) {
		precX.set(x);
    	precY.set(y);
	}
	
	public void onMouseDrag(double x, double y) {
		dessinController.trace(precX.get(), precY.get(), x, y);
		figureCourante.addPoint(new Point(x, y));
		precX.set(x);
		precY.set(y);
	}
	
	public void onCrayon() {
		outilCourant = new OutilCrayon(this);
		statutController.outil.setText("Crayon");
	};
	
	public void onEtoile() {
		outilCourant = new OutilEtoile(this);
		statutController.outil.setText("Etoile");
	};
	
	public void dessine() {
    	GraphicsContext gc = dessinController.canvas.getGraphicsContext2D();
    	gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
    	for (Figure f : dessin.getFigures()) {
    		for (int i = 1; i < f.getPoints().size(); i++) {
    			double x0 = f.getPoints().get(i-1).getX();
    			double y0 = f.getPoints().get(i-1).getY();
    			double x1 = f.getPoints().get(i).getX();
    			double y1 = f.getPoints().get(i).getY();
    			gc.strokeLine(x0, y0, x1, y1);
    		}
    	}
    }
	
	public boolean onQuitter() {
		if (Dialogues.confirmation()) {
			return true;
		}
		return false;
	}
	
	public void onCloseRequest(WindowEvent evt) {
		if (!onQuitter()) {
			evt.consume();
		}
	}
}
