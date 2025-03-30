module iut.gon.test2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens iut.gon.test2 to javafx.fxml;
    exports iut.gon.test2;
}
