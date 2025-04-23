package iut.gon.tp3;

public class GrilleModel {
	private String[][] tableau = new String[3][3];
	
	public String getCase(int lg, int col) {
		return tableau[lg][col];
	}
	
	public void setCase(int lg, int col, String texte) {
		tableau[lg][col] = texte;
	}
}