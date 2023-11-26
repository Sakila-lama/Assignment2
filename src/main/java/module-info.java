module com.example.exercisedbfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;

    exports exercisedbfx;
    opens exercisedbfx to javafx.fxml;
    exports exercisedbfx.controller;
    opens exercisedbfx.controller to javafx.fxml;
    opens exercisedbfx.model to com.google.gson;
}
