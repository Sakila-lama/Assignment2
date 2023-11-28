module com.example.exercisedbfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;

    exports exercisedbfx;
    opens exercisedbfx to com.google.gson, javafx.fxml;
    exports exercisedbfx.controller;
    opens exercisedbfx.controller to com.google.gson, javafx.fxml;
    exports exercisedbfx.model;
    opens exercisedbfx.model to com.google.gson, javafx.fxml;
    exports exercisedbfx.service;
    opens exercisedbfx.service to com.google.gson, javafx.fxml;
}
