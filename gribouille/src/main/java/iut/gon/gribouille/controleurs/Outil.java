package iut.gon.gribouille.controleurs;

import iut.gon.gribouille.modele.Figure;

public abstract class Outil {
	
	protected Figure figureCourante;
	protected Controleur controleur;
	
	public Outil(Controleur c) {
		controleur = c;
	}
	
	public void onMousePress() {};

	public void onMouseDrag() {};
}
