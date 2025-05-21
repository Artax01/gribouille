package fr.unicaen.iut.tp5;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public enum BackgroundType {
	INCONNUE(new Background(new BackgroundFill(Color.AQUA, new CornerRadii(0.20, true), null))),
	LIBRE(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null))),
	ECHEC(new Background(new BackgroundFill(Color.RED, null, null))),
	MARQUEE(new Background(new BackgroundFill(Color.LEMONCHIFFON, null, null)));
	
	private final Background background;
	
	BackgroundType(Background bg) {
		this.background = bg;
	}
	
	public Background getBackground() {
		return background;
	}
	
	public static Background getBackgroundOnText(String text) {
		if (text != null) {
			switch (text) {
				case "?": return INCONNUE.getBackground();
				case "X": return ECHEC.getBackground();
				case "P": return MARQUEE.getBackground();
				default: return LIBRE.getBackground();
			}
		}
		return null;
	}
}
