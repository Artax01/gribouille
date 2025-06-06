module iut.gon.gribouille {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires java.desktop;
	requires javafx.swing;

    opens iut.gon.gribouille to javafx.fxml;
    exports iut.gon.gribouille;
    
    opens iut.gon.gribouille.controleurs to javafx.fxml;
    exports iut.gon.gribouille.controleurs;
}
