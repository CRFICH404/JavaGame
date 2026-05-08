module com.cvut.fit.biopj.portniagin.semestralka {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires annotations;

    opens com.cvut.fit.biopj.portniagin.semestralka to javafx.fxml;
    exports com.cvut.fit.biopj.portniagin.semestralka;
    exports com.cvut.fit.biopj.portniagin.semestralka.controllers;
    opens com.cvut.fit.biopj.portniagin.semestralka.controllers to javafx.fxml;
}