package iut.gon.gribouille.controleurs;

import iut.gon.gribouille.modele.Trace;

public class OutilCrayon extends Outil {
	
	public OutilCrayon(Controller c) {
		super(c);
	}
	
	public void onMousePress(double x, double y) {
		controller.figureCourante = new Trace(controller.epaisseur.get(), controller.couleur.get().toString(), x, y);
		controller.dessin.addFigure(controller.figureCourante);
		controller.prevX.set(x);
    	controller.prevY.set(y);
	}
	
	public void onMouseDrag(double x, double y) {
		if (controller.figureCourante != null) {
			controller.figureCourante.addPoint(x, y);
		}
		
		controller.dessinController.trace(controller.prevX.getValue(), controller.prevY.getValue(), x, y);
		controller.prevX.set(x);
		controller.prevY.set(y);
	}

}
