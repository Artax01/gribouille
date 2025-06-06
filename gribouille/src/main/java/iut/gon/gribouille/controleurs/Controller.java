package iut.gon.gribouille.controleurs;

import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import iut.gon.gribouille.Dialogues;
import iut.gon.gribouille.modele.*;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import javafx.embed.swing.SwingFXUtils;

public class Controller implements Initializable {

	public final Dessin dessin = new Dessin();
	public Figure figureCourante;
	public Outil outilCourant = new OutilCrayon(this);
	public final SimpleDoubleProperty prevX = new SimpleDoubleProperty();
	public final SimpleDoubleProperty prevY = new SimpleDoubleProperty();
	public final SimpleIntegerProperty epaisseur = new SimpleIntegerProperty(1);
	public final SimpleObjectProperty<Color> couleur = new SimpleObjectProperty<Color>(Color.BLACK);

	@FXML public MenusController menusController;
	@FXML public DessinController dessinController;
	@FXML public StatutController statutController;
	@FXML public CouleursController couleursController;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		menusController.setControleur(this);
		dessinController.setControleur(this);
		statutController.setControleur(this);
		couleursController.setControleur(this);
		
		statutController.xCoordinate.textProperty().bind(prevX.asString("%.0f"));
		statutController.yCoordinate.textProperty().bind(prevY.asString("%.0f"));
		statutController.epaisseur.textProperty().bind(epaisseur.asString());
		statutController.couleur.textProperty().bind(couleur.asString());
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
    		gc.setLineWidth(f.getEpaisseur());
    		gc.setStroke(Color.valueOf(f.getCouleur()));
    		for (int i = 1; i < f.getPoints().size(); i++) {
    			if (f instanceof Trace) {
    				double x0 = f.getPoints().get(i-1).getX();
        			double y0 = f.getPoints().get(i-1).getY();
        			double x1 = f.getPoints().get(i).getX();
        			double y1 = f.getPoints().get(i).getY();
        			gc.strokeLine(x0, y0, x1, y1);
    			}
    			else if (f instanceof Etoile) {
    				double x0 = f.getPoints().get(0).getX();
        			double y0 = f.getPoints().get(0).getY();
        			double x1 = f.getPoints().get(i).getX();
        			double y1 = f.getPoints().get(i).getY();
        			gc.strokeLine(x0, y0, x1, y1);
    			}
    		}
    	}
    }
	
	public void setEpaisseur(int epaisseur) {
		this.epaisseur.set(epaisseur);
		dessinController.setEpaisseur(epaisseur);
	}
	
	public void setCouleur(Color couleur) {
		this.couleur.set(couleur);
		dessinController.setCouleur(couleur);
	}
	
	public void updateEpaisseur() {
		figureCourante = figureCourante.changeEpaisseur(epaisseur.get());
		dessin.addFigure(figureCourante);
	}
	
	public void updateCouleur() {
		figureCourante = figureCourante.changeCouleur(couleur.get().toString());
		dessin.addFigure(figureCourante);
	}
	
	public void updateForme() {
		if (outilCourant != null) {
			outilCourant.makeForme(prevX.get(), prevY.get());
		}
	}
	
	public void onKeyPressed(String key) {
		switch(key.toLowerCase()) {
			case ")":
				if (epaisseur.get() > 1) this.setEpaisseur(epaisseur.get() - 1);
				updateEpaisseur();
				break;
			case "=":
				if (epaisseur.get() + 1 <= 9) this.setEpaisseur(epaisseur.get() + 1);
				updateEpaisseur();
				break;
			case "c":
				onCrayon();
				updateForme();
				break;
			case "e":
				onEtoile();
				updateForme();
				break;
			case "&":
				setCouleur(Color.RED);
				updateCouleur();
				break;
			case "é":
				setCouleur(Color.LIME);
				updateCouleur();
				break;
			case "\"":
				setCouleur(Color.BLUE);
				updateCouleur();
				break;
			case "'":
				setCouleur(Color.CYAN);
				updateCouleur();
				break;
			case "(":
				setCouleur(Color.PINK);
				updateCouleur();
				break;
			case "-":
				setCouleur(Color.YELLOW);
				updateCouleur();
				break;
			case "è":
				setCouleur(Color.BLACK);
				updateCouleur();
				break;
			case "_":
				setCouleur(Color.WHITE);
				updateCouleur();
				break;
			default:
				break;
		}
	}
	
	public void onCharge() {
		FileChooser selecteur = new FileChooser();
		selecteur.setTitle("Charger un dessin");
		selecteur.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers gribouille", "*.grb"));
		File fichier = selecteur.showOpenDialog(Window.getWindows().get(0));
		if (fichier != null) {
			dessin.charge(fichier.getAbsolutePath());
			dessin.setNomDuFichier(fichier.getName());
			dessinController.efface();
			dessine();
		}
	}
	
	public void onSauvegarde() {
		FileChooser selecteur = new FileChooser();
		selecteur.setTitle("Enregistrer sous le dessin");
		selecteur.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers gribouille", "*.grb"));
		File fichier = selecteur.showSaveDialog(Window.getWindows().get(0));
		if (fichier != null) {
			String nomFichier = fichier.getName();
	        if (!nomFichier.endsWith(".grb")) {
	            fichier = new File(fichier.getParent(), nomFichier + ".grb");
	        }
			
			dessin.sauveSous(fichier.getAbsolutePath());
			dessin.setNomDuFichier(fichier.getName());
		}
	}
	
	public void onExporte() {
		FileChooser selecteur = new FileChooser();
		selecteur.setTitle("Exporter le dessin");
		selecteur.getExtensionFilters().add(new FileChooser.ExtensionFilter("Format PNG", "*.png"));
        File fichier = selecteur.showSaveDialog(Window.getWindows().get(0));
        if (fichier != null) {
        	
        	String nomFichier = fichier.getName();
	        if (!nomFichier.endsWith(".png")) {
	            fichier = new File(fichier.getParent(), nomFichier + ".png");
	        }
        	
            WritableImage image = dessinController.canvas.snapshot(new SnapshotParameters(), null);
            
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", fichier);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Problème lors de l'exportation", ButtonType.YES);
                alert.setTitle("Problème lors de l'exportation");
                alert.showAndWait();
            }
        }
	}
	
	public void onEffacerTout() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
		alert.setHeaderText("Cette action est irreversible !");
		alert.setContentText("Voulez-vous vraiment effacer tout le dessin ?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.YES) {
	        dessin.getFigures().clear();
			dessinController.efface();
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
