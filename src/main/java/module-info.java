module sde.group.iii.inventorysystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    exports sde.group.iii.inventorysystem.main;
    exports sde.group.iii.inventorysystem.controller;
    exports sde.group.iii.inventorysystem.model;
    exports sde.group.iii.inventorysystem.dao;
    exports sde.group.iii.inventorysystem.service;

    opens sde.group.iii.inventorysystem.controller to javafx.fxml;
}
