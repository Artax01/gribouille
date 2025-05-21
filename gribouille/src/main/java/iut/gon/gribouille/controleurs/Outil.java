package iut.gon.gribouille.controleurs;

import iut.gon.gribouille.modele.Figure;

public abstract class Outil {
	
	protected Figure figureCourante;
	protected Controller controller;
	
	public Outil(Controller c) {
		controller = c;
	}
	
	public void onMousePress(double x, double y) {};

	public void onMouseDrag(double x, double y) {};
}
