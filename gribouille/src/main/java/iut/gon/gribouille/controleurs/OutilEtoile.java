package iut.gon.gribouille.controleurs;

import iut.gon.gribouille.modele.Etoile;

public class OutilEtoile extends Outil {
	
	private double x1;
	private double y1;

	public OutilEtoile(Controller c) {
		super(c);
	}

	public void onMousePress(double x, double y) {
        controller.figureCourante = new Etoile(1, "black", x, y);
        controller.dessin.addFigure(controller.figureCourante);
        x1 = x;
        y1 = y;
    }

    public void onMouseDrag(double x, double y) {
        if (controller.figureCourante != null) {
        	controller.figureCourante.addPoint(x, y);
        }

        controller.dessinController.trace(x1, y1, x, y);
        controller.prevX.set(x);
        controller.prevY.set(y);
    }
}
