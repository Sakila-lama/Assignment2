package exercisedbfx;

import exercisedbfx.service.SceneChanger;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class contains main method - entry point of the application
 */
public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        // load a chart view as the first view
        SceneChanger.changeScenes(stage, SceneChanger.GENERAL_VIEW, "Exercise Explorer");
    }
}
