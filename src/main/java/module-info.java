module kg.megacom.kassaapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens kg.megacom.kassaapp to javafx.fxml;
    exports kg.megacom.kassaapp;
    exports kg.megacom.kassaapp.controllers;
    exports kg.megacom.kassaapp.models;
    exports kg.megacom.kassaapp.services;
    exports kg.megacom.kassaapp.db;

    opens kg.megacom.kassaapp.controllers to javafx.fxml;
    opens kg.megacom.kassaapp.models;
    opens kg.megacom.kassaapp.services;
    opens kg.megacom.kassaapp.db;
    exports kg.megacom.kassaapp.services.impl;
    opens kg.megacom.kassaapp.services.impl;
    exports kg.megacom.kassaapp.db.impl;
    opens kg.megacom.kassaapp.db.impl;
}