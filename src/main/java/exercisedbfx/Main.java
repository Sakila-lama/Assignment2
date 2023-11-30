package exercisedbfx;

import exercisedbfx.service.SceneChanger;
import javafx.application.Application;
import javafx.stage.Stage;

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
