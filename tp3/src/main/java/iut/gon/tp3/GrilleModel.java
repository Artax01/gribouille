package iut.gon.tp3;

import javafx.beans.property.SimpleStringProperty;

public class GrilleModel {
	private SimpleStringProperty[][] tableau = new SimpleStringProperty[3][3];
	
	public GrilleModel() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++)   {
				tableau[i][j] = new SimpleStringProperty("");
			}
		}
	}
	
	public SimpleStringProperty getCase(int lg, int col) {
		return tableau[lg][col];
	}
	
	public void setCase(int lg, int col, String texte) {
		tableau[lg][col].setValue(texte);
	}
}