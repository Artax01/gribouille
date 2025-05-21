package iut.gon.gribouille.controleurs;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.shape.Rectangle;

public class CouleursController implements Initializable {

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
 
    public void setControleur(Controller c) {
    	this.controller = c;
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}

}
