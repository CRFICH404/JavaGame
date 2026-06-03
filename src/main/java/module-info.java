module com.cvut.fit.biopj.portniagin.semestralka {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires annotations;
    requires org.apache.poi.ooxml;
    requires java.desktop;
    requires javafx.swing;

    opens com.cvut.fit.biopj.portniagin.semestralka to javafx.fxml;
    exports com.cvut.fit.biopj.portniagin.semestralka;
    exports com.cvut.fit.biopj.portniagin.semestralka.controllers;
    opens com.cvut.fit.biopj.portniagin.semestralka.controllers to javafx.fxml;
    exports com.cvut.fit.biopj.portniagin.semestralka.player;
    opens com.cvut.fit.biopj.portniagin.semestralka.player to javafx.fxml;
    exports com.cvut.fit.biopj.portniagin.semestralka.statusEffects;
    opens com.cvut.fit.biopj.portniagin.semestralka.statusEffects to javafx.fxml;
    exports com.cvut.fit.biopj.portniagin.semestralka.session;
    opens com.cvut.fit.biopj.portniagin.semestralka.session to javafx.fxml;
    exports com.cvut.fit.biopj.portniagin.semestralka.application;
    opens com.cvut.fit.biopj.portniagin.semestralka.application to javafx.fxml;
    exports com.cvut.fit.biopj.portniagin.semestralka.events;
    opens com.cvut.fit.biopj.portniagin.semestralka.events to javafx.fxml;
    exports com.cvut.fit.biopj.portniagin.semestralka.items;
    opens com.cvut.fit.biopj.portniagin.semestralka.items to javafx.fxml;
    exports com.cvut.fit.biopj.portniagin.semestralka.enums;
    exports com.cvut.fit.biopj.portniagin.semestralka.eventListeners;
}