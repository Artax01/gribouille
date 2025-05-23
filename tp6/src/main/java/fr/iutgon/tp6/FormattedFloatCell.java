package fr.iutgon.tp6;

import java.text.DecimalFormat;

import javafx.css.PseudoClass;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;

public class FormattedFloatCell<T> extends TableCell<T, Number> {
	
	private static final DecimalFormat FORMAT = new DecimalFormat("0.00");
	private static final PseudoClass NEGATIF_CSS = PseudoClass.getPseudoClass("negatif");
	
	public FormattedFloatCell() {
		super.setAlignment(Pos.CENTER_RIGHT);
	}
	
	@Override
	public void updateItem(Number item, boolean empty) {
		
		if (empty || item == null) {
			super.setGraphic(null);
			super.setText(null);
			pseudoClassStateChanged(NEGATIF_CSS, false);
		} else {
			double value = item.doubleValue();
			super.setText(FORMAT.format(value));
			pseudoClassStateChanged(NEGATIF_CSS, value < 0);
		}
		
	}
}
